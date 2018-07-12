/*  1:   */ package GUIs;
/*  2:   */ 
/*  3:   */ import org.bukkit.Bukkit;
/*  4:   */ import org.bukkit.entity.Player;
/*  5:   */ import org.bukkit.event.inventory.InventoryClickEvent;
/*  6:   */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*  7:   */ import org.bukkit.inventory.Inventory;
/*  8:   */ 
/*  9:   */ public class Mode
/* 10:   */   extends Leash
/* 11:   */ {
/* 12:   */   private Send send;
/* 13:11 */   private boolean ignore = false;
/* 14:   */   
/* 15:   */   public Mode(Player player, Send send)
/* 16:   */   {
/* 17:14 */     super(player);
/* 18:   */     
/* 19:16 */     this.send = send;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void render()
/* 23:   */   {
/* 24:21 */     this.inventory.setItem(0, mode("softcore"));
/* 25:22 */     this.inventory.setItem(2, mode("15"));
/* 26:23 */     this.inventory.setItem(3, mode("30"));
/* 27:24 */     this.inventory.setItem(4, mode("1"));
/* 28:25 */     this.inventory.setItem(5, mode("3"));
/* 29:26 */     this.inventory.setItem(6, mode("6"));
/* 30:27 */     this.inventory.setItem(8, mode("hardcore"));
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void open()
/* 34:   */   {
/* 35:32 */     this.inventory = Bukkit.createInventory(null, 9, "Select mode");
/* 36:   */     
/* 37:34 */     super.open();
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void onInventoryClick(InventoryClickEvent event)
/* 41:   */   {
/* 42:39 */     event.setCancelled(true);
/* 43:   */     
/* 44:41 */     String mode = null;
/* 45:43 */     switch (event.getRawSlot())
/* 46:   */     {
/* 47:   */     case 0: 
/* 48:46 */       mode = "softcore";
/* 49:47 */       break;
/* 50:   */     case 2: 
/* 51:49 */       mode = "15";
/* 52:50 */       break;
/* 53:   */     case 3: 
/* 54:52 */       mode = "30";
/* 55:53 */       break;
/* 56:   */     case 4: 
/* 57:55 */       mode = "1";
/* 58:56 */       break;
/* 59:   */     case 5: 
/* 60:58 */       mode = "3";
/* 61:59 */       break;
/* 62:   */     case 6: 
/* 63:61 */       mode = "6";
/* 64:62 */       break;
/* 65:   */     case 8: 
/* 66:64 */       mode = "hardcore";
/* 67:   */     }
/* 68:68 */     if (mode != null)
/* 69:   */     {
/* 70:70 */       this.ignore = true;
/* 71:   */       
/* 72:72 */       Send send = new Send(this.player, this.send.dominant, this.send.submissive, mode);
/* 73:73 */       send.open();
/* 74:   */     }
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void onInventoryClose(InventoryCloseEvent event)
/* 78:   */   {
/* 79:79 */     if (!this.ignore)
/* 80:   */     {
/* 81:81 */       this.ignore = true;
/* 82:   */       
/* 83:83 */       Send send = new Send(this.player, this.send.dominant, this.send.submissive, this.send.mode);
/* 84:84 */       send.open();
/* 85:   */     }
/* 86:   */   }
/* 87:   */ }


/* Location:           C:\Users\hlutverk3\Downloads\PlayerLeash.jar
 * Qualified Name:     GUIs.Mode
 * JD-Core Version:    0.7.0.1
 */