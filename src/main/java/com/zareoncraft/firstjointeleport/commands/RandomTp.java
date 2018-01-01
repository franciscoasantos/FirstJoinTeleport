package com.zareoncraft.firstjointeleport.commands;

import com.zareoncraft.firstjointeleport.Messages;
import com.zareoncraft.firstjointeleport.Permissions;
import com.zareoncraft.firstjointeleport.PluginConfig;
import com.zareoncraft.firstjointeleport.TeleportLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RandomTp implements CommandExecutor {

	private PluginConfig pluginConfig;

	public RandomTp(PluginConfig pluginConfig) {
		this.pluginConfig = pluginConfig;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			if (!Permissions.has(sender, Permissions.RANDOM_TELEPORTE)) {
				sender.sendMessage(ChatColor.DARK_RED + Messages.getMessage(Messages.ACCESS_DENIED));
				return true;
			}

			if (cmd.getName().equalsIgnoreCase("randomtp")) {
				TeleportLocation tp = new TeleportLocation(pluginConfig);
				tp.randomTeleportPlayer((Player) sender);
				sender.sendMessage(ChatColor.GREEN + Messages.getMessage(Messages.RANDOM_TELEPORT_MESSAGE));
				return true;
			}

		} else if (sender instanceof ConsoleCommandSender) {
			if (cmd.getName().equalsIgnoreCase("randomtp")) {
				if (args.length > 0) {

					Player player = Bukkit.getPlayer(args[0]);
					TeleportLocation tp = new TeleportLocation(pluginConfig);

					if (player != null) {
						Location loc;
						loc = tp.randomTeleportPlayer(player);
						pluginConfig.getPlugin().getLogger().info(Messages.getPlayerTeleportedMessage(player, loc));
						player.sendMessage(ChatColor.GREEN + Messages.getMessage(Messages.RANDOM_TELEPORT_MESSAGE));
						return true;
					} else {
						pluginConfig.getPlugin().getLogger().info(Messages.getMessage(Messages.PLAYER_NOT_FOUND));
						return true;
					}
				} else {
					pluginConfig.getPlugin().getLogger().info(Messages.getMessage(Messages.COMMAND_USE));
					return true;
				}
			}
		}
		return false;
	}
}
