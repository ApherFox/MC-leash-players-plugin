package net.apherfox.rpchat;

import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

// import net.apherfox.rpchat.<module>

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;

public class RPChat extends JavaPlugin {

	private File messagesFile = new File(this.getDataFolder(), "messages.yml");
	private FileConfiguration messages;

	private File settingsFile = new File(this.getDataFolder(), "config.yml");
	private FileConfiguration settings;

	private boolean mc18 = this.getServer().getVersion().contains("1.8");

	private Metrics metrics;

	@Override
	public FileConfiguration getConfig() {
		return settings;
	}

	public FileConfiguration getMessages() {
		return messages;
	}

	public Boolean isAboveMC18() {
		return !mc18;
	}

	public FileConfiguration getSettings() {
		return settings;
	}

	@Override
	public void reloadConfig() {

		messages = YamlConfiguration.loadConfiguration(messagesFile);
		settings = YamlConfiguration. loadConfiguration(settingsFile);

		addMessageDefaults();
		addSettingsDefaults();
	}

	@Override
	public void onEnable() {

	if (!isAboveMC18()) {
		getLogger().info("[RPChat] Minecraft versions before 1.9 are not supported!");
		getServer().getPluginManager().disablePlugin(this);
		return;
	}

	createConfigs();
	reloadConfigs();

	PluginManager pm = getServer().getPluginManager();
	//pm.registerEvents(new <EVENT>(this), (this);

	getCommand("rpchat").setExecutor(new Executor(this));

	boolean checkUpdates = getSettings().getBoolean("check-updates");
	if (checkUpdates) {
		new Thread(() -> new Updater(getDescription()).checkCurrentVersion()).start();
	}

	if (getSettings().getBoolean("allow-metrics")) {
		metrics = new Metrics(this);
		getLogger().info("Metrics successfully initialized!");

	} else {
		getLogger().warning("Metrics disabled");
	}
}

private int addMessage(String node, String message) {
	if (messages.get(node) == null) {
		messages.set(node, message);
		return 1;
	}
	return 0;
}

private void addMessageDefaults() {
