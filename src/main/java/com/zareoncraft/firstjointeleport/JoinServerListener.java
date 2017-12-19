package com.zareoncraft.firstjointeleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinServerListener implements Listener {

    JavaPlugin plugin;

    public JoinServerListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        boolean randomTp;
        Location location;

        String world;
        int x, y, z, range;

        world = plugin.getConfig().getString("world.name");
        randomTp = plugin.getConfig().getBoolean("random_teleport");

        x = plugin.getConfig().getInt("world.x");
        y = plugin.getConfig().getInt("world.y");
        z = plugin.getConfig().getInt("world.z");

        //range = plugin.getConfig().getInt("random_teleport.range");

        location = new Location(Bukkit.getWorld(world), x, y, z);

        //location.getWorld().getHighestBlockYAt(3,3);

        if (!event.getPlayer().hasPlayedBefore()) {
//            if (randomTp) {
//                x = (int) (Math.random() * range);
//                y = (int) (Math.random() * range);
//            }
            event.getPlayer().teleport(location);
            //event.getPlayer().sendMessage("teleportando para " + "X " + x + "Y " + y + "Z " + z);
        }

    }
}
