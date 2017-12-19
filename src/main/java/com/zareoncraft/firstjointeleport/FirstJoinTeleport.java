package com.zareoncraft.firstjointeleport;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FirstJoinTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        this.readConfig();

        getLogger().info("FJT Ativado!");

        getServer().getPluginManager().registerEvents(new JoinServerListener(this), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("FJT Destivado!");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
