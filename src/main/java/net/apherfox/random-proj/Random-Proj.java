package net.apherfox.random-proj;

import org.bukkit.plugin.java.JavaPlugin;

public class EmptySpigotPlugin extends JavaPlugin {

    public void onEnable() {

        getLogger().info("Enabled Random-Proj");

        getCommand("hello").setExecutor(new HelloCommand());
        getCommand("tp").setExecutor(new TeleportCommand());

    }

    @Override
    public void onDisable() {

        getLogger().info("Disabled Random-Proj");
    }

}
