package main;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin
  extends JavaPlugin
{
  public static JavaPlugin plugin;
  private static File file;
  public static YamlConfiguration config;
  
  public void onEnable()
  {
    plugin = this;
    file = new File(getDataFolder(), "config.yml");
    config = YamlConfiguration.loadConfiguration(file);
    
    config.addDefault("timeout", Integer.valueOf(60000));
    if (!file.exists())
    {
      config.options().copyDefaults(true);
      try
      {
        config.save(file);
      }
      catch (IOException localIOException) {}
    }
    Commands.register();
    Events.register();
    Message.init();
    Pair.init();
  }
  
  public void onDisable()
  {
    for (Player player : ) {
      for (GUI gui : GUI.guis) {
        if (player.getOpenInventory().getTitle() == gui.inventory.getTitle())
        {
          player.closeInventory();
          break;
        }
      }
    }
  }
}
