package com.leontg77.vengefulspirits;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.vengefulspirits.cmds.VSCommand;

/**
 * Main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
	private Plugin plugin;

	@Override
	public void onDisable() {
		// print a message to the console saying it has been disabled.
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " has been disabled.");
		
	}
	
	@Override
	public void onEnable() {
		// print a message to the console saying it has been enabled.
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " v" + file.getVersion() + " has been enabled.");
		getLogger().info("Plugin is made by LeonTG77.");
		
		// register the /vs command.
		getCommand("vs").setExecutor(new VSCommand());
		
		// set the plugin field to this class.
		plugin = this;
	}
}
