package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

class Teleport {

	private int x, y, z, range;
	private World world;
	private String worldString;

	Teleport(World world, int range) {
		this.world = world;
		this.range = range;
	}

	Teleport(String worldString, int x, int y, int z) {
		this.worldString = worldString;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	void randomTeleportPlayer(Player player) {
		Chunk chunk;

		chunk = world.getChunkAt(this.getRandomLocation());

		if (!chunk.isLoaded()) { //verifica se o chunck está carregado, se não carrega-o
			chunk.load(true);
		}

		player.teleport(this.getRandomLocation());
	}

	void fixedTeleportPlayer(Player player) {

		player.teleport(this.getFixedLocation());
	}

	private Location getRandomLocation() {
		Block b;
		Location location;

		do {
			int x = (int) (Math.random() * range);
			int z = (int) (Math.random() * range);
			int y = world.getHighestBlockYAt(x, z);

			location = new Location(world, x, y, z);

			b = location.getBlock();

			Bukkit.getConsoleSender().sendMessage("§4Localização gerada: " + b.getType().toString());
		} while (!b.isLiquid());

		return location;
	}

	private Location getFixedLocation() {
		return new Location(Bukkit.getWorld(this.worldString), this.x, this.y, this.z);
	}
}