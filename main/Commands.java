 package main;
 
 import commands.Leash;
 import commands.Unleash;
 import org.bukkit.command.PluginCommand;
 import org.bukkit.plugin.java.JavaPlugin;
 
 public class Commands
 {
   public static void register()
   {
     Plugin.plugin.getCommand("leash").setExecutor(new Leash());
     Plugin.plugin.getCommand("unleash").setExecutor(new Unleash());
   }
 }
