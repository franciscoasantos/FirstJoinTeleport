package com.zareoncraft.firstjointeleport;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

class TeleportLocation {
	private double x, y, z;
	private int max_range, min_range;
	private World world;

	TeleportLocation(World world, int min_range, int max_range) { //teleporte random
		this.world = world;
		this.min_range = min_range;
		this.max_range = max_range;
	}

	TeleportLocation(String worldName, double fix_x, double fix_y, double fix_z) { //teleporte fixo
		this.world = Bukkit.getWorld(worldName);
		this.x = fix_x + 0.5; //soma com 0.5 para o teleporte ser no centro do bloco
		this.y = fix_y;
		this.z = fix_z + 0.5;
	}

	void randomTeleportPlayer(Player player) {
		Location location = this.getRandomLocation();
		this.loadChunk(location);
		player.teleport(location);
		player.sendMessage(ChatColor.GREEN + "[FJT]" + "Bem-vindo " + player.getName() + " você foi transportado para uma coordenada aleatória para começar seu jogo!");
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

//			Bukkit.getConsoleSender().sendMessage("§4Localização gerada: ");
//			Bukkit.getConsoleSender().sendMessage("§4X: " + block.getX() + " Y: " + block.getY() + " Z: " + block.getZ() + " "
//					+ block.getType().toString());
		} while ((block.isLiquid()) || (block.getType() == Material.CACTUS));

		return location;
	}

	private Location getFixedLocation() {
		return new Location(world, this.x + 0.5, this.y, this.z + 0.5);
	}

	private void loadChunk(Location location) {
		Chunk chunk;
		chunk = this.world.getChunkAt(location);
		if (!chunk.isLoaded()) { //verifica se o chunck está carregado, se não carrega-o antes do teleporte
			chunk.load(true);
		}
	}

	private double randomNumber() {
		int number = (int) (Math.random() * max_range);
		int valor = (int) (Math.random() * 10); //variável que vai definir se a cordenada gerada será positiva ou negativa

		if (number < min_range) {
			number += min_range;
		}

		if (valor % 2 != 0) { //caso @valor seja ímpar, o @numero se torna negativo
			number *= -1;
		}

		return number + 0.5; //soma com 0.5 para o teleporte ser no centro do bloco
	}
}