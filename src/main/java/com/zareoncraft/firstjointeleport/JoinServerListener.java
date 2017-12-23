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
	private String world;

	JoinServerListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void teleportPlayer(PlayerJoinEvent event) {

		this.getConfigs();

		//if (!event.getPlayer().hasPlayedBefore()) {
		if (randomTp) {
			Bukkit.getConsoleSender().sendMessage("§4Random TP Ativado.");

			Teleport tp = new Teleport(Bukkit.getWorld(this.world), range);

			tp.randomTeleportPlayer(event.getPlayer());

		} else {
			try {
				Bukkit.getConsoleSender().sendMessage("§4TP Fixo Ativado.");

				Teleport tp = new Teleport(this.world, this.x, this.y, this.z);

				tp.fixedTeleportPlayer(event.getPlayer());

				//event.getPlayer().teleport(getFixedLocation());
			} catch (NullPointerException ex) {
				Bukkit.getConsoleSender().sendMessage("§5O mundo declarado em world.name no config.yml não foi encontrado.\n" + ex.getMessage());
			}
		}
		//}
	}

	private void getConfigs() {
		world = plugin.getConfig().getString("world.name");
		plugin.getLogger().info("World:" + world);
		randomTp = plugin.getConfig().getBoolean("random_teleport");
		plugin.getLogger().info("random tp:" + randomTp);
		range = plugin.getConfig().getInt("random_teleport.range");
		plugin.getLogger().info("range:" + range);
		x = plugin.getConfig().getInt("world.x");
		plugin.getLogger().info("X:" + x);
		y = plugin.getConfig().getInt("world.y");
		plugin.getLogger().info("Y:" + y);
		z = plugin.getConfig().getInt("world.z");
		plugin.getLogger().info("Z:" + z);
	}
}