package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinServerListener implements Listener {

	private JavaPlugin plugin;
	private boolean randomTp;
	private int x, y, z, range;
	private String worldStrig;

	JoinServerListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void teleportPlayer(PlayerJoinEvent event) {

		this.getConfigs();

		//if (!event.getPlayer().hasPlayedBefore()) {
		if (randomTp) {
			try {
				Bukkit.getConsoleSender().sendMessage("§4Random TP Ativado.");
				TeleportLocation tp = new TeleportLocation(Bukkit.getWorld(this.worldStrig), range);
				tp.randomTeleportPlayer(event.getPlayer());
			} catch (NullPointerException ex) {
				Bukkit.getConsoleSender().sendMessage("§4[FJT]Algo deu errado, verifique as configuracoes.");
			}
		} else {
			try {
				Bukkit.getConsoleSender().sendMessage("§4TP Fixo Ativado.");
				TeleportLocation tp = new TeleportLocation(this.worldStrig, this.x, this.y, this.z);
				tp.fixedTeleportPlayer(event.getPlayer());
			} catch (NullPointerException ex) {
				Bukkit.getConsoleSender().sendMessage("§4[FJT]Algo deu errado, verifique as configuracoes.");
			}
		}
		//}
	}

	private void getConfigs() {
		worldStrig = plugin.getConfig().getString("spawn_world.name");
		randomTp = plugin.getConfig().getBoolean("random_teleport.random");
		range = plugin.getConfig().getInt("random_teleport.range");
		x = plugin.getConfig().getInt("spawn_world.x");
		y = plugin.getConfig().getInt("spawn_world.y");
		z = plugin.getConfig().getInt("spawn_world.z");

//		Bukkit.getConsoleSender().sendMessage("World: " + worldStrig);
//		Bukkit.getConsoleSender().sendMessage("random: " + randomTp);
//		Bukkit.getConsoleSender().sendMessage("range: " + range);
//		Bukkit.getConsoleSender().sendMessage("x: " + x);
//		Bukkit.getConsoleSender().sendMessage("y: " + y);
//		Bukkit.getConsoleSender().sendMessage("z: " + z);
	}
}