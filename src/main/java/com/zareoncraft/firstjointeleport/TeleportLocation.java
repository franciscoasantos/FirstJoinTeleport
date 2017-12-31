package com.zareoncraft.firstjointeleport;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TeleportLocation {
	private final PluginConfig pluginConfig;
	private final World world;

	public TeleportLocation(PluginConfig pluginConfig) {
		this.pluginConfig = pluginConfig;
		this.world = pluginConfig.getWorld();
	}

	public Location randomTeleportPlayer(Player player) {
		Location location = this.getRandomLocation();
		this.loadChunk(location);
		player.teleport(location);
		sendMessage(player);
		return location;
	}

	void fixedTeleportPlayer(Player player) {
		Location location = this.getFixedLocation();
		this.loadChunk(location);
		player.teleport(location);
	}

	private Location getRandomLocation() {
		Block block;
		Location location;
		Location locationCheck;

		do {
			double x = randomNumber();
			double z = randomNumber();
			int y = this.world.getHighestBlockYAt((int) x, (int) z);

			location = new Location(this.world, x, y, z);
			locationCheck = new Location(this.world, x, y - 1, z);

			block = locationCheck.getBlock();

			Bukkit.getConsoleSender().sendMessage("§4Localizacao gerada: ");
			Bukkit.getConsoleSender().sendMessage("§4X: " + block.getX() + " Y: " + block.getY() + " Z: " + block.getZ() + " "
					+ block.getType().toString());
		} while ((block.isLiquid()) || (block.getType() == Material.CACTUS));

		return location;
	}

	private Location getFixedLocation() {
		double x = pluginConfig.getFixedX() + 0.5;
		double y = pluginConfig.getFixedY();
		double z = pluginConfig.getFixedZ() + 0.5;

		return new Location(world, x, y, z);
	}

	private void loadChunk(Location location) {
		Chunk chunk = this.world.getChunkAt(location);
		if (!chunk.isLoaded()) { //verifica se o chunck está carregado, se não carrega-o antes do teleporte
			chunk.load(true);
		}
	}

	private double randomNumber() {
		int number = (int) (Math.random() * pluginConfig.getMaxRange());
		int valor = (int) (Math.random() * 100); //variável que vai definir se a cordenada gerada será positiva ou negativa

		if (number < pluginConfig.getMinRange()) {
			number += pluginConfig.getMinRange();
		}

		if (valor % 2 != 0) { //caso valor seja ímpar, o numero se torna negativo
			number *= -1;
		}

		return number + 0.5; //soma com 0.5 para o teleporte ser no centro do bloco
	}

	private void sendMessage(final Player player) {
		if (pluginConfig.isWelcomeMessageEnable()) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(pluginConfig.getPlugin(), new Runnable() {
				public void run() {
					player.sendMessage(ChatColor.GREEN + pluginConfig.getWelcomeMessage());
				}
			}, 20 * pluginConfig.getDelay());
		}
	}
}