/*   1:    */ package GUIs;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Arrays;
/*   5:    */ import java.util.Date;
/*   6:    */ import main.Message;
/*   7:    */ import main.Pair;
/*   8:    */ import org.bukkit.Bukkit;
/*   9:    */ import org.bukkit.Material;
/*  10:    */ import org.bukkit.OfflinePlayer;
/*  11:    */ import org.bukkit.Sound;
/*  12:    */ import org.bukkit.entity.Player;
/*  13:    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*  14:    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*  15:    */ import org.bukkit.inventory.Inventory;
/*  16:    */ 
/*  17:    */ public class Request
/*  18:    */   extends Leash
/*  19:    */ {
/*  20:    */   private Pair pair;
/*  21:    */   
/*  22:    */   public Request(Player player, Pair pair)
/*  23:    */   {
/*  24: 22 */     super(player);
/*  25:    */     
/*  26: 24 */     this.pair = pair;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void open()
/*  30:    */   {
/*  31: 30 */     this.player.playSound(this.player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
/*  32:    */     
/*  33: 32 */     this.inventory = Bukkit.createInventory(null, 9, "Leash request");
/*  34:    */     
/*  35: 34 */     super.open();
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void render()
/*  39:    */   {
/*  40: 39 */     this.inventory.setItem(0, item(Material.WOOL, 1, (short)5, "§aAccept", Arrays.asList(new String[0])));
/*  41: 40 */     this.inventory.setItem(3, playerHead(Bukkit.getOfflinePlayer(this.pair.dominantID), true));
/*  42: 41 */     this.inventory.setItem(4, edit(mode(this.pair.mode), "§fMode: ", Arrays.asList(new String[0])));
/*  43: 42 */     this.inventory.setItem(5, playerHead(Bukkit.getOfflinePlayer(this.pair.submissiveID), false));
/*  44: 43 */     this.inventory.setItem(8, item(Material.WOOL, 1, (short)14, "§cDeny", Arrays.asList(new String[0])));
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void onInventoryClick(InventoryClickEvent event)
/*  48:    */   {
/*  49: 48 */     event.setCancelled(true);
/*  50: 50 */     if ((event.getRawSlot() == 0) || (event.getRawSlot() == 8))
/*  51:    */     {
/*  52: 52 */       Player requester = null;
/*  53: 53 */       Player requestee = null;
/*  54: 55 */       if (this.pair.requesterDominant)
/*  55:    */       {
/*  56: 57 */         requester = Bukkit.getPlayer(this.pair.dominantID);
/*  57: 58 */         requestee = Bukkit.getPlayer(this.pair.submissiveID);
/*  58:    */       }
/*  59:    */       else
/*  60:    */       {
/*  61: 62 */         requester = Bukkit.getPlayer(this.pair.submissiveID);
/*  62: 63 */         requestee = Bukkit.getPlayer(this.pair.dominantID);
/*  63:    */       }
/*  64: 66 */       if (event.getRawSlot() == 0)
/*  65:    */       {
/*  66: 68 */         OfflinePlayer submissive = Bukkit.getOfflinePlayer(this.pair.submissiveID);
/*  67: 70 */         if (Pair.find(submissive) == null)
/*  68:    */         {
/*  69: 72 */           this.pair.accepted = true;
/*  70: 73 */           this.pair.time = new Date().getTime();
/*  71: 74 */           this.pair.update();
/*  72: 75 */           Pair.save();
/*  73:    */           
/*  74: 77 */           this.player.closeInventory();
/*  75: 78 */           this.player.updateInventory();
/*  76: 80 */           if (requester != null) {
/*  77: 82 */             Message.send(requester, "request.accept.requester", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(this.pair.requesterDominant ? this.pair.submissiveID : this.pair.dominantID).getName() }));
/*  78:    */           }
/*  79: 85 */           if (requestee != null) {
/*  80: 87 */             Message.send(requestee, "request.accept.requestee", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(this.pair.requesterDominant ? this.pair.dominantID : this.pair.submissiveID).getName() }));
/*  81:    */           }
/*  82: 90 */           if (this.pair.dominant != null)
/*  83:    */           {
/*  84: 92 */             Message.send(this.pair.dominant, "info.dominant", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(this.pair.submissiveID).getName() }));
/*  85: 93 */             this.pair.dominant.playSound(this.pair.dominant.getLocation(), Sound.ENTITY_LEASHKNOT_PLACE, 1.0F, 1.0F);
/*  86:    */           }
/*  87: 96 */           if (this.pair.submissive != null)
/*  88:    */           {
/*  89: 98 */             Message.send(this.pair.submissive, "info.submissive", Arrays.asList(new String[0]));
/*  90: 99 */             this.pair.submissive.playSound(this.pair.submissive.getLocation(), Sound.ENTITY_LEASHKNOT_PLACE, 1.0F, 1.0F);
/*  91:    */           }
/*  92:    */         }
/*  93:    */         else
/*  94:    */         {
/*  95:104 */           Message.send(this.player, "errors.alreadyleashed", Arrays.asList(new String[] { submissive.getName() }));
/*  96:    */         }
/*  97:    */       }
/*  98:107 */       else if (event.getRawSlot() == 8)
/*  99:    */       {
/* 100:109 */         Pair.pairs.remove(this.pair);
/* 101:111 */         if (requester != null) {
/* 102:113 */           Message.send(requester, "request.deny.requester", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(this.pair.requesterDominant ? this.pair.submissiveID : this.pair.dominantID).getName() }));
/* 103:    */         }
/* 104:116 */         if (requestee != null) {
/* 105:118 */           Message.send(requestee, "request.deny.requestee", Arrays.asList(new String[] { Bukkit.getOfflinePlayer(this.pair.requesterDominant ? this.pair.dominantID : this.pair.submissiveID).getName() }));
/* 106:    */         }
/* 107:    */       }
/* 108:122 */       Pair.save();
/* 109:    */       
/* 110:124 */       this.player.closeInventory();
/* 111:125 */       this.player.updateInventory();
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void onInventoryClose(InventoryCloseEvent event) {}
/* 116:    */ }


/* Location:           C:\Users\hlutverk3\Downloads\PlayerLeash.jar
 * Qualified Name:     GUIs.Request
 * JD-Core Version:    0.7.0.1
 */