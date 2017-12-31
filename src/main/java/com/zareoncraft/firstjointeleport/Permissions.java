package com.zareoncraft.firstjointeleport;

import org.bukkit.command.CommandSender;

public enum Permissions {

	RANDOM_TELEPORTE("FirstJoinTeleport.RandomTP");

	private final String permission;

	Permissions(String permission) {
		this.permission = permission;
	}

	public static boolean has(CommandSender sender, Permissions permission) {
		return has(sender, permission.permission);
	}

	public static boolean has(CommandSender sender, String node) {
		return sender.hasPermission(node) || sender.hasPermission(node.toLowerCase());
	}
}
