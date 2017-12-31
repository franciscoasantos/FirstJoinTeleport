package com.zareoncraft.firstjointeleport;

import com.zareoncraft.firstjointeleport.commands.RandomTp;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstJoinTeleport extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginConfig pluginConfig = new PluginConfig(this);

		//Eventos
		getServer().getPluginManager().registerEvents(new JoinServerListener(pluginConfig), this);

		//Comandos
		getCommand("randomtp").setExecutor(new RandomTp(pluginConfig));

		Bukkit.getConsoleSender().sendMessage("§5FJT Ativado!");
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§5FJT Desativado!");
	}
}
