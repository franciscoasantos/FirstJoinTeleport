package com.zareoncraft.firstjointeleport;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

final class PluginConfig {
	private boolean RANDOM_TP;
	private double FIX_X, FIX_Y, FIX_Z;
	private int MIN_RANGE, MAX_RANGE;
	private JavaPlugin PLUGIN;
	private String WORLD_NAME;

	PluginConfig(JavaPlugin plugin) {
		PLUGIN = plugin;
		readConfig();

		WORLD_NAME = PLUGIN.getConfig().getString("spawn_world.name");
		RANDOM_TP = PLUGIN.getConfig().getBoolean("random_tp");
		FIX_X = PLUGIN.getConfig().getDouble("fixed_teleport.fix_x");
		FIX_Y = PLUGIN.getConfig().getDouble("fixed_teleport.fix_y");
		FIX_Z = PLUGIN.getConfig().getDouble("fixed_teleport.fix_z");
		MIN_RANGE = PLUGIN.getConfig().getInt("random_teleport.min_range");
		MAX_RANGE = PLUGIN.getConfig().getInt("random_teleport.max_range");
	}

	private void readConfig() {
		try {
			if (!PLUGIN.getDataFolder().exists()) {
				PLUGIN.getDataFolder().mkdirs();
			}

			File file = new File(PLUGIN.getDataFolder(), "config.yml");

			if (!file.exists()) {
				PLUGIN.getLogger().info("Config.yml não encontrado, criando!");
				PLUGIN.saveDefaultConfig();
			} else {
				PLUGIN.getLogger().info("Config.yml encontrado, carregando!");
				PLUGIN.reloadConfig();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	String getWORLD_NAME() {
		return WORLD_NAME;
	}

	boolean isRANDOM_TP() {
		return RANDOM_TP;
	}

	double getFIX_X() {
		return FIX_X;
	}

	double getFIX_Y() {
		return FIX_Y;
	}

	double getFIX_Z() {
		return FIX_Z;
	}

	int getMIN_RANGE() {
		return MIN_RANGE;
	}

	int getMAX_RANGE() {
		return MAX_RANGE;
	}
}