package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinServerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Location location = new Location(Bukkit.getWorld("Teste"), 1,2,3 );

        if(!event.getPlayer().hasPlayedBefore()){
            event.getPlayer().teleport(location);
        }

    }
}
