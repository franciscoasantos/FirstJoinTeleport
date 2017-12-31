package com.zareoncraft.firstjointeleport.commands;

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
				sender.sendMessage(ChatColor.DARK_RED + "Você não pode usar este comando.");
				return true;
			}

			if (cmd.getName().equalsIgnoreCase("randomtp")) {
				TeleportLocation tp = new TeleportLocation(pluginConfig);
				tp.randomTeleportPlayer((Player) sender);
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
						pluginConfig.getPlugin().getLogger().info("Jogador " + player.getName() + " teleportado para X: " + loc.getX() + " Y: " + loc.getY() + " Z: " + loc.getZ());
						return true;
					} else {
						pluginConfig.getPlugin().getLogger().info("Jogador nao encontrado.");
						return true;
					}
				} else {
					pluginConfig.getPlugin().getLogger().info("Use /randomtp [player] para teleportar um jogador.");
					return true;
				}
			}
		}
		return true;
	}
}
