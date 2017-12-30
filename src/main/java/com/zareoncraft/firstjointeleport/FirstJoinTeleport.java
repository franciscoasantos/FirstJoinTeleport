package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstJoinTeleport extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginConfig pluginConfig = new PluginConfig(this);

		Bukkit.getConsoleSender().sendMessage("§5FJT Ativado!");
		getServer().getPluginManager().registerEvents(new JoinServerListener(pluginConfig), this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§5FJT Desativado!");
	}
}
