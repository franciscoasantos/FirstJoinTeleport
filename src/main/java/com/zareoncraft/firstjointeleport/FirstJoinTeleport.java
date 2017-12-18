package com.zareoncraft.firstjointeleport;

import org.bukkit.plugin.java.JavaPlugin;

public class FirstJoinTeleport extends JavaPlugin {

    @Override
    public void onEnable(){
        getLogger().info("FJT Ativado!");
    }

    @Override
    public void onDisable(){
        getLogger().info("FJT Destivado!");
    }
}
