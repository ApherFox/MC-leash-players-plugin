/*   1:    */ package GUIs;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Arrays;
/*   5:    */ import java.util.Date;
/*   6:    */ import java.util.UUID;
/*   7:    */ import main.Message;
/*   8:    */ import main.Pair;
/*   9:    */ import main.Plugin;
/*  10:    */ import net.minecraft.server.v1_12_R1.EntityPlayer;
/*  11:    */ import net.minecraft.server.v1_12_R1.IChatBaseComponent;
/*  12:    */ import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
/*  13:    */ import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
/*  14:    */ import net.minecraft.server.v1_12_R1.PlayerConnection;
/*  15:    */ import org.bukkit.Bukkit;
/*  16:    */ import org.bukkit.Material;
/*  17:    */ import org.bukkit.OfflinePlayer;
/*  18:    */ import org.bukkit.Sound;
/*  19:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  20:    */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*  21:    */ import org.bukkit.entity.Player;
/*  22:    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*  23:    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*  24:    */ import org.bukkit.inventory.Inventory;
/*  25:    */ 
/*  26:    */ public class Send
/*  27:    */   extends Leash
/*  28:    */ {
/*  29:    */   public Player dominant;
/*  30:    */   public Player submissive;
/*  31:    */   public String mode;
/*  32:    */   
/*  33:    */   public Send(Player player, Player dominant, Player submissive, String mode)
/*  34:    */   {
/*  35: 30 */     super(player);
/*  36:    */     
/*  37: 32 */     this.dominant = dominant;
/*  38: 33 */     this.submissive = submissive;
/*  39: 34 */     this.mode = mode;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void render()
/*  43:    */   {
/*  44: 39 */     this.inventory.setItem(0, item(Material.WOOL, 1, (short)14, "§cCancel", Arrays.asList(new String[0])));
/*  45: 40 */     this.inventory.setItem(3, edit(playerHead(this.dominant, true), "", Arrays.asList(new String[] { "§fClick to swap roles" })));
/*  46: 41 */     this.inventory.setItem(4, edit(super.mode(this.mode), "§fMode: ", Arrays.asList(new String[] { "", "§fClick to change mode" })));
/*  47: 42 */     this.inventory.setItem(5, edit(playerHead(this.submissive, false), "", Arrays.asList(new String[] { "§fClick to swap roles" })));
/*  48: 43 */     this.inventory.setItem(8, item(Material.WOOL, 1, (short)11, "§9Send", this.player.hasPermission("playerleash.forceleash") ? Arrays.asList(new String[] { "§fClick to send leash request", "§fShift + Click to force leash" }) : Arrays.asList(new String[0])));
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void open()
/*  52:    */   {
/*  53: 48 */     this.inventory = Bukkit.createInventory(null, 9, "Send leash request");
/*  54:    */     
/*  55: 50 */     super.open();
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void onInventoryClick(InventoryClickEvent event)
/*  59:    */   {
/*  60: 55 */     event.setCancelled(true);
/*  61: 57 */     switch (event.getRawSlot())
/*  62:    */     {
/*  63:    */     case 8: 
/*  64: 60 */       send((this.player.hasPermission("playerleash.forceleash")) && (event.isShiftClick()));
/*  65: 61 */       this.player.closeInventory();
/*  66: 62 */       this.player.updateInventory();
/*  67: 63 */       break;
/*  68:    */     case 0: 
/*  69: 65 */       this.player.closeInventory();
/*  70: 66 */       this.player.updateInventory();
/*  71: 67 */       break;
/*  72:    */     case 3: 
/*  73:    */     case 5: 
/*  74: 70 */       Player lastDominant = this.dominant;
/*  75: 71 */       this.dominant = this.submissive;
/*  76: 72 */       this.submissive = lastDominant;
/*  77: 73 */       render();
/*  78: 74 */       break;
/*  79:    */     case 4: 
/*  80: 76 */       Mode mode = new Mode(this.player, this);
/*  81: 77 */       mode.open();
/*  82:    */     }
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void onInventoryClose(InventoryCloseEvent event) {}
/*  86:    */   
/*  87:    */   private void send(boolean force)
/*  88:    */   {
/*  89: 87 */     Pair pair = new Pair();
/*  90: 88 */     Player other = null;
/*  91: 89 */     boolean requesterDominant = false;
/*  92: 91 */     if (this.player.equals(this.dominant))
/*  93:    */     {
/*  94: 93 */       other = this.submissive;
/*  95: 94 */       requesterDominant = true;
/*  96:    */     }
/*  97:    */     else
/*  98:    */     {
/*  99: 98 */       other = this.dominant;
/* 100: 99 */       requesterDominant = false;
/* 101:    */     }
/* 102:102 */     pair.id = UUID.randomUUID();
/* 103:103 */     pair.dominantID = this.dominant.getUniqueId();
/* 104:104 */     pair.submissiveID = this.submissive.getUniqueId();
/* 105:105 */     pair.mode = this.mode;
/* 106:106 */     pair.time = new Date().getTime();
/* 107:107 */     pair.requesterDominant = requesterDominant;
/* 108:108 */     pair.accepted = force;
/* 109:    */     
/* 110:110 */     pair.update();
/* 111:111 */     Pair.pairs.add(pair);
/* 112:112 */     Pair.save();
/* 113:114 */     if (!force)
/* 114:    */     {
/* 115:116 */       String timeout = Integer.toString((int)Math.ceil(Plugin.config.getInt("timeout") / 1000 / 60));
/* 116:    */       
/* 117:118 */       Message.send(this.player, "request.send.requester", Arrays.asList(new String[] { other.getName(), timeout }));
/* 118:120 */       if (other.isOnline())
/* 119:    */       {
/* 120:122 */         ArrayList<String> messages = null;
/* 121:124 */         if (requesterDominant) {
/* 122:126 */           messages = Message.get("request.send.requestee.submissive", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(pair.dominantID).getName(), timeout }));
/* 123:    */         } else {
/* 124:130 */           messages = Message.get("request.send.requestee.dominant", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(pair.submissiveID).getName(), timeout }));
/* 125:    */         }
/* 126:133 */         for (int i = 0; i < messages.size(); i++)
/* 127:    */         {
/* 128:135 */           String message = (String)messages.get(i);
/* 129:137 */           if (i == 0)
/* 130:    */           {
/* 131:139 */             IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a("{ \"text\":\"" + message + " \", \"extra\": [ { \"text\": \"" + (String)Message.get("button.text", Arrays.asList(new String[0])).get(0) + "\", \"hoverEvent\": { \"action\": \"show_text\", \"value\": \"" + (String)Message.get("button.hover", Arrays.asList(new String[0])).get(0) + "\" }, \"clickEvent\": { \"action\": \"run_command\", \"value\": \"/leash " + pair.id.toString() + "\" } } ] }");
/* 132:140 */             PacketPlayOutChat packet = new PacketPlayOutChat(component);
/* 133:141 */             ((CraftPlayer)other).getHandle().playerConnection.sendPacket(packet);
/* 134:    */           }
/* 135:    */           else
/* 136:    */           {
/* 137:145 */             other.sendMessage(message);
/* 138:    */           }
/* 139:    */         }
/* 140:149 */         other.playSound(other.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
/* 141:    */       }
/* 142:    */     }
/* 143:    */   }
/* 144:    */ }


/* Location:           C:\Users\hlutverk3\Downloads\PlayerLeash.jar
 * Qualified Name:     GUIs.Send
 * JD-Core Version:    0.7.0.1
 */