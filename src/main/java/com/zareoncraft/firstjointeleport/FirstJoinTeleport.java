package com.zareoncraft.firstjointeleport;

import com.zareoncraft.firstjointeleport.commands.RandomTp;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstJoinTeleport extends JavaPlugin {
	static FirstJoinTeleport plugin;

	@Override
	public void onEnable() {
		plugin = this;
		PluginConfig pluginConfig = new PluginConfig(this);

		//Events
		getServer().getPluginManager().registerEvents(new JoinServerListener(pluginConfig), this);

		//Commands
		getCommand("randomtp").setExecutor(new RandomTp(pluginConfig));

		Bukkit.getConsoleSender().sendMessage("§5FJT Enabled!");
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§5FJT Disabled!");
	}
}
