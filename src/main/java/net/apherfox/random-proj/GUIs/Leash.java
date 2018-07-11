package GUIs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.GUI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public abstract class Leash
  extends GUI
{
  public Leash(Player player)
  {
    super(player);
  }
  
  protected ItemStack playerHead(OfflinePlayer owner, boolean dominant)
  {
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
    
    SkullMeta meta = (SkullMeta)skull.getItemMeta();
    meta.setOwner(owner.getName());
    if (dominant) {
      meta.setDisplayName("�cDominant: �r" + (this.player.equals(owner) ? "�oYou" : owner.getName()));
    } else {
      meta.setDisplayName("�9Submissive: �r" + (this.player.equals(owner) ? "�oYou" : owner.getName()));
    }
    skull.setItemMeta(meta);
    
    return skull;
  }
  
  protected ItemStack mode(String mode)
  {
    ItemStack item = null;
    String str;
    switch ((str = mode).hashCode())
    {
    case 49: 
      if (str.equals("1")) {}
      break;
    case 51: 
      if (str.equals("3")) {}
      break;
    case 54: 
      if (str.equals("6")) {}
      break;
    case 1572: 
      if (str.equals("15")) {
        break;
      }
      break;
    case 1629: 
      if (str.equals("30")) {}
      break;
    case 116327178: 
      if (str.equals("hardcore")) {}
    case 1318747849: 
      if ((goto 346) && (str.equals("softcore")))
      {
        item = super.item(Material.LEASH, 1, (short)0, "�aSoftcore", Arrays.asList(new String[] { "�fThe submissive will be able to unleash themselves at any time" }));
        return item;
        
        item = super.item(Material.ENDER_PEARL, 1, (short)0, "�e15 Minutes", Arrays.asList(new String[] { "�fThe submissive won't be able to unleash themselves until 15 minutes have passed" }));
        return item;
        
        item = super.item(Material.ENDER_PEARL, 1, (short)0, "�e30 Minutes", Arrays.asList(new String[] { "�fThe submissive won't be able to unleash themselves until 30 minutes have passed" }));
        return item;
        
        item = super.item(Material.ENDER_PEARL, 1, (short)0, "�e1 Hour", Arrays.asList(new String[] { "�fThe submissive won't be able to funleash themselves until 1 hour has passed" }));
        return item;
        
        item = super.item(Material.ENDER_PEARL, 1, (short)0, "�63 Hours", Arrays.asList(new String[] { "�fThe submissive won't be able to unleash themselves until 3 hours have passed" }));
        return item;
        
        item = super.item(Material.ENDER_PEARL, 1, (short)0, "�66 Hours", Arrays.asList(new String[] { "�fThe submissive won't be able to unleash themselves until 6 hours have passed" }));
        return item;
        
        item = super.item(Material.EYE_OF_ENDER, 1, (short)0, "�cHardcore", Arrays.asList(new String[] { "�fThe submissive won't be able to unleash themselves" }));
      }
      break;
    }
    return item;
  }
  
  protected ItemStack edit(ItemStack item, String prefix, List<String> lore)
  {
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(prefix + meta.getDisplayName());
    if (meta.getLore() != null)
    {
      ArrayList<String> loreCopy = new ArrayList(meta.getLore());
      loreCopy.addAll(lore);
      meta.setLore(loreCopy);
    }
    else
    {
      meta.setLore(lore);
    }
    item.setItemMeta(meta);
    
    return item;
  }
}
