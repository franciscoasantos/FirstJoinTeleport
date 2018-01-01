package com.zareoncraft.firstjointeleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum Messages {

	ACCESS_DENIED("messages.access_denied"),
	COMMAND_USE("messages.command_use"),
	CONFIG_ERROR("messages.config_error"),
	PLAYER_NOT_FOUND("messages.player_not_found"),
	PLAYER_TELEPORTED("messages.player_teleported"),
	PLUGIN_YML_FOUND("messages.plugin_yml_found"),
	PLUGIN_YML_NOT_FOUND("messages.plugin_yml_not_found"),
	RANDOM_TELEPORT_MESSAGE("messages.random_teleport_message"),
	WELCOME_MESSAGE("messages.welcome_message");

	private final String message;

	Messages(String message) {
		this.message = message;
	}

	public static String getMessage(Messages message) {

		return FirstJoinTeleport.plugin.getConfig().getString(message.message);
	}

	public static String getPlayerTeleportedMessage(Player player, Location loc) {
		String messageText = getMessage(PLAYER_TELEPORTED);

		messageText = messageText.replace("{{player}}", player.getName());
		messageText = messageText.replace("{{loc_x}}", String.valueOf(loc.getX()));
		messageText = messageText.replace("{{loc_y}}", String.valueOf(loc.getY()));
		messageText = messageText.replace("{{loc_z}}", String.valueOf(loc.getZ()));

		return messageText;
	}
}
