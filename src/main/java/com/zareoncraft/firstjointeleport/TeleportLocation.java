package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

class TeleportLocation {
	private double x, y, z;
	private int range;
	private World world;

	TeleportLocation(World world, int range) { //teleporte random
		this.world = world;
		this.range = range;
	}

	TeleportLocation(String worldString, double x, double y, double z) { //teleporte fixo
		this.world = Bukkit.getWorld(worldString);
		this.x = x + 0.5; //soma com 0.5 para o teleporte ser no centro do bloco
		this.y = y;
		this.z = z + 0.5;
	}

	void randomTeleportPlayer(Player player) {
		Location location = this.getRandomLocation();
		this.loadChunk(location);
		player.teleport(location);
	}

	void fixedTeleportPlayer(Player player) {
		Location location = this.getFixedLocation();
		this.loadChunk(location);
		player.teleport(location);
	}

	private Location getRandomLocation() {
		Block block;
		Location location;

		do {
			int x = (int) (Math.random() * range);
			int z = (int) (Math.random() * range);
			int y = this.world.getHighestBlockYAt(x, z);

			location = new Location(this.world, x + 0.5, y, z + 0.5); //y+1 para o jogador não ficar afundado no chão

			block = location.getBlock();

//			Bukkit.getConsoleSender().sendMessage("§4Localização gerada: ");
//			Bukkit.getConsoleSender().sendMessage("§4X: " + block.getX() + " Y: " + block.getY() + " Z: " + block.getZ() + " "
//					+ block.getType().toString());
		} while (block.isLiquid());

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
}