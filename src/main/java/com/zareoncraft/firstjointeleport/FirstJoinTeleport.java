package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FirstJoinTeleport extends JavaPlugin {

	@Override
	public void onEnable() {
		this.readConfig();
		Bukkit.getConsoleSender().sendMessage("§5FJT Ativado!");
		getServer().getPluginManager().registerEvents(new JoinServerListener(this), this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§5FJT Desativado!");
	}

	private void readConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}

			File file = new File(getDataFolder(), "config.yml");

			if (!file.exists()) {
				getLogger().info("Config.yml não encontrado, criando!");
				saveDefaultConfig();
			} else {
				getLogger().info("Config.yml encontrado, carregando!");
				reloadConfig();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
