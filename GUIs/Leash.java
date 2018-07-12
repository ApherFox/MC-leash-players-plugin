/*  1:   */ 
/*  2:   */ 
/*  3:   */ java.util.ArrayList
/*  4:   */ java.util.Arrays
/*  5:   */ java.util.List
/*  6:   */ main.GUI
/*  7:   */ org.bukkit.Material
/*  8:   */ org.bukkit.OfflinePlayer
/*  9:   */ org.bukkit.SkullType
/* 10:   */ org.bukkit.entity.Player
/* 11:   */ org.bukkit.inventory.ItemStack
/* 12:   */ org.bukkit.inventory.meta.ItemMeta
/* 13:   */ org.bukkit.inventory.meta.SkullMeta
/* 14:   */ 
/* 15:   */ Leash
/* 16:   */   
/* 17:   */ 
/* 18:   */   Leash
/* 19:   */   
/* 20:20 */     
/* 21:   */   
/* 22:   */   
/* 23:   */   playerHead, 
/* 24:   */   
/* 25:26 */      = SKULL_ITEM, 1, PLAYERordinal()
/* 26:   */     
/* 27:28 */      = getItemMeta()
/* 28:29 */     setOwnergetName()
/* 29:31 */      ( {
/* 30:33 */       setDisplayName"§cDominant: §r"player.equals(owner) ? "§oYou" : owner.getName()));
/* 31:   */     } else {
/* 32:37 */       meta.setDisplayName("§9Submissive: §r" + (this.player.equals(owner) ? "§oYou" : owner.getName()));
/* 33:   */     }
/* 34:40 */     skull.setItemMeta(meta);
/* 35:   */     
/* 36:42 */     return skull;
/* 37:   */   }
/* 38:   */   
/* 39:   */   protected ItemStack mode(String mode)
/* 40:   */   {
/* 41:47 */     ItemStack item = null;
/* 42:   */     String str;
/* 43:49 */     switch ((str = mode).hashCode())
/* 44:   */     {
/* 45:   */     case 49: 
/* 46:49 */       if (str.equals("1")) {}
/* 47:   */       break;
/* 48:   */     case 51: 
/* 49:49 */       if (str.equals("3")) {}
/* 50:   */       break;
/* 51:   */     case 54: 
/* 52:49 */       if (str.equals("6")) {}
/* 53:   */       break;
/* 54:   */     case 1572: 
/* 55:49 */       if (str.equals("15")) {
/* 56:   */         break;
/* 57:   */       }
/* 58:   */       break;
/* 59:   */     case 1629: 
/* 60:49 */       if (str.equals("30")) {}
/* 61:   */       break;
/* 62:   */     case 116327178: 
/* 63:49 */       if (str.equals("hardcore")) {}
/* 64:   */     case 1318747849: 
/* 65:49 */       if ((goto 346) && (str.equals("softcore")))
/* 66:   */       {
/* 67:52 */         item = super.item(Material.LEASH, 1, (short)0, "§aSoftcore", Arrays.asList(new String[] { "§fThe submissive will be able to unleash themselves at any time" }));
/* 68:53 */         return item;
/* 69:   */         
/* 70:55 */         item = super.item(Material.ENDER_PEARL, 1, (short)0, "§e15 Minutes", Arrays.asList(new String[] { "§fThe submissive won't be able to unleash themselves until 15 minutes have passed" }));
/* 71:56 */         return item;
/* 72:   */         
/* 73:58 */         item = super.item(Material.ENDER_PEARL, 1, (short)0, "§e30 Minutes", Arrays.asList(new String[] { "§fThe submissive won't be able to unleash themselves until 30 minutes have passed" }));
/* 74:59 */         return item;
/* 75:   */         
/* 76:61 */         item = super.item(Material.ENDER_PEARL, 1, (short)0, "§e1 Hour", Arrays.asList(new String[] { "§fThe submissive won't be able to funleash themselves until 1 hour has passed" }));
/* 77:62 */         return item;
/* 78:   */         
/* 79:64 */         item = super.item(Material.ENDER_PEARL, 1, (short)0, "§63 Hours", Arrays.asList(new String[] { "§fThe submissive won't be able to unleash themselves until 3 hours have passed" }));
/* 80:65 */         return item;
/* 81:   */         
/* 82:67 */         item = super.item(Material.ENDER_PEARL, 1, (short)0, "§66 Hours", Arrays.asList(new String[] { "§fThe submissive won't be able to unleash themselves until 6 hours have passed" }));
/* 83:68 */         return item;
/* 84:   */         
/* 85:70 */         item = super.item(Material.EYE_OF_ENDER, 1, (short)0, "§cHardcore", Arrays.asList(new String[] { "§fThe submissive won't be able to unleash themselves" }));
/* 86:   */       }
/* 87:   */       break;
/* 88:   */     }
/* 89:74 */     return item;
/* 90:   */   }
/* 91:   */   
/* 92:   */   protected ItemStack edit(ItemStack item, String prefix, List<String> lore)
/* 93:   */   {
/* 94:79 */     ItemMeta meta = item.getItemMeta();
/* 95:80 */     meta.setDisplayName(prefix + meta.getDisplayName());
/* 96:82 */     if (meta.getLore() != null)
/* 97:   */     {
/* 98:84 */       ArrayList<String> loreCopy = new ArrayList(meta.getLore());
/* 99:85 */       loreCopy.addAll(lore);
/* :0:86 */       meta.setLore(loreCopy);
/* :1:   */     }
/* :2:   */     else
/* :3:   */     {
/* :4:90 */       meta.setLore(lore);
/* :5:   */     }
/* :6:93 */     item.setItemMeta(meta);
/* :7:   */     
/* :8:95 */     return item;
/* :9:   */   }
/* ;0:   */ }


/* Location:           C:\Users\hlutverk3\Downloads\PlayerLeash.jar
 * Qualified Name:     GUIs.Leash
 * JD-Core Version:    0.7.0.1
 */