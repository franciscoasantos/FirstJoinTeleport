package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinServerListener implements Listener {

	private PluginConfig config;

	JoinServerListener(PluginConfig config) {
		this.config = config;
	}

	@EventHandler
	public void teleportPlayer(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			if (config.isRANDOM_TP()) {
				try {
					TeleportLocation tp = new TeleportLocation(Bukkit.getWorld(config.getWORLD_NAME()), config.getMIN_RANGE(), config.getMAX_RANGE());
					tp.randomTeleportPlayer(event.getPlayer());
				} catch (NullPointerException ex) {
					Bukkit.getConsoleSender().sendMessage("§4[config]Algo deu errado, verifique as configuracoes.");
				}
			} else {
				try {
					TeleportLocation tp = new TeleportLocation(config.getWORLD_NAME(), config.getFIX_X(), config.getFIX_Y(), config.getFIX_Z());
					tp.fixedTeleportPlayer(event.getPlayer());
				} catch (NullPointerException ex) {
					Bukkit.getConsoleSender().sendMessage("§4[FJT]Algo deu errado, verifique as configuracoes.");
				}
			}
		}
	}
}