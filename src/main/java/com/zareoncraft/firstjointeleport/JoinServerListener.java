package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

class JoinServerListener implements Listener {

	private final PluginConfig pluginConfig;

	JoinServerListener(PluginConfig config) {
		this.pluginConfig = config;
	}

	@EventHandler
	public void teleportPlayer(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			if (pluginConfig.isRandomTp()) {
				try {
					TeleportLocation tp = new TeleportLocation(pluginConfig);
					tp.randomTeleportPlayer(event.getPlayer());
				} catch (NullPointerException ex) {
					Bukkit.getConsoleSender().sendMessage("§4[FJT]Algo deu errado, verifique as configuracoes.");
				}
			} else {
				try {
					TeleportLocation tp = new TeleportLocation(pluginConfig);
					tp.fixedTeleportPlayer(event.getPlayer());
				} catch (NullPointerException ex) {
					Bukkit.getConsoleSender().sendMessage("§4[FJT]Algo deu errado, verifique as configuracoes.");
				}
			}
		}
	}
}