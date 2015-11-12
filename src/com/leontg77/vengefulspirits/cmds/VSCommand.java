package com.leontg77.vengefulspirits.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import com.leontg77.vengefulspirits.listeners.DeathListener;

/**
 * VS command.
 * <p> 
 * Command used to enable or disable the scenario.
 * 
 * @author LeonTG77
 */
public class VSCommand implements CommandExecutor {
	private static final String PERMISSION = "vengefulspirits.manage";
	private static final String PREFIX = "§6VengefulSpirits §8» §f";
	
	private boolean enabled = false;
	
	private DeathListener listener = new DeathListener();
	
	private Plugin plugin;
	
	public VSCommand(Plugin plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// check if the have permission, if not send them a message and return.
		if (!sender.hasPermission(PERMISSION)) {
			sender.sendMessage(PREFIX + ChatColor.RED + "You don't have access to this.");
			return true;
		}
		
		// check if they typed anything else than the command itself, if not send usage and return.
		if (args.length == 0) {
			sender.sendMessage(PREFIX + "Usage: /vs <enable|disable>");
			return true;
		}
		
		// check if they typed /egg enable, if so do the command.
		if (args[0].equalsIgnoreCase("enable")) {
			// check if the scenario is enabled, if not tell them so and return.
			if (enabled) {
				sender.sendMessage(PREFIX + "VengefulSpirits is already enabled.");
				return true;
			}
			
			// send them a message and set enabled to be true
			sender.sendMessage(PREFIX + "VengefulSpirits has been enabled.");
			enabled = true;
			
			// register the eventhandles for the scenario.
			PluginManager manager = Bukkit.getPluginManager();
			manager.registerEvents(listener, plugin);
			return true;
		}

		// check if they typed /egg enable, if so do the command.
		if (args[0].equalsIgnoreCase("disable")) {
			// check if the scenario wasn't enabled, if not tell them so and return.
			if (!enabled) {
				sender.sendMessage(PREFIX + "VengefulSpirits is not enabled.");
				return true;
			}

			// send them a message and set enabled to be false
			sender.sendMessage(PREFIX + "VengefulSpirits has been disabled.");
			enabled = false;
			
			// unregister the eventhandles for the scenario.
			HandlerList.unregisterAll(listener);
			return true;
		}
		
		// they didn't type enable or disable, send usage.
		sender.sendMessage(PREFIX + "Usage: /vs <enable|disable>");
		return true;
	}
}
