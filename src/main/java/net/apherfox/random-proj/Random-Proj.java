package net.apherfox.random-proj;

import org.bukkit.plugin.java.Javaplugin;

public final class random-proj extends JavaPlugin {
	@Override
	public void onEnabled() {
		getLogger().info("onEnable has been invoked!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
}
