package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PluginConfig {
	private final boolean RANDOM_TP;
	private final boolean WELCOME_MESSAGE_ENABLE;
	private final double FIX_X;
	private final double FIX_Y;
	private final double FIX_Z;
	private final int MIN_RANGE;
	private final int MAX_RANGE;
	private final int DELAY;
	private final JavaPlugin PLUGIN;
	private final String WORLD_NAME;

	PluginConfig(JavaPlugin plugin) {
		PLUGIN = plugin;
		readConfig();

		FIX_X = PLUGIN.getConfig().getDouble("fixed_teleport.fix_x");
		FIX_Y = PLUGIN.getConfig().getDouble("fixed_teleport.fix_y");
		FIX_Z = PLUGIN.getConfig().getDouble("fixed_teleport.fix_z");
		MIN_RANGE = PLUGIN.getConfig().getInt("random_teleport.min_range");
		MAX_RANGE = PLUGIN.getConfig().getInt("random_teleport.max_range");
		RANDOM_TP = PLUGIN.getConfig().getBoolean("random_tp");
		WELCOME_MESSAGE_ENABLE = PLUGIN.getConfig().getBoolean("welcome_message.enabled");
		DELAY = PLUGIN.getConfig().getInt("welcome_message.delay");
		WORLD_NAME = PLUGIN.getConfig().getString("spawn_world.name");

	}

	private void readConfig() {
		try {
			if (!PLUGIN.getDataFolder().exists()) {
				PLUGIN.getDataFolder().mkdirs();
			}

			File file = new File(PLUGIN.getDataFolder(), "config.yml");

			if (!file.exists()) {
				PLUGIN.getLogger().info(Messages.getMessage(Messages.PLUGIN_YML_NOT_FOUND));
				PLUGIN.saveDefaultConfig();
			} else {
				PLUGIN.getLogger().info(Messages.getMessage(Messages.PLUGIN_YML_FOUND));
				PLUGIN.reloadConfig();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	World getWorld() {
		return Bukkit.getWorld(WORLD_NAME);
	}

	boolean isRandomTp() {
		return RANDOM_TP;
	}

	double getFixedX() {
		return FIX_X;
	}

	double getFixedY() {
		return FIX_Y;
	}

	double getFixedZ() {
		return FIX_Z;
	}

	int getMinRange() {
		return MIN_RANGE;
	}

	int getMaxRange() {
		return MAX_RANGE;
	}

	boolean isWelcomeMessageEnable() {
		return WELCOME_MESSAGE_ENABLE;
	}

	public JavaPlugin getPlugin() {
		return this.PLUGIN;
	}

	int getDelay() {
		return DELAY;
	}
}