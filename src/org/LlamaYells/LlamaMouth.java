package org.LlamaYells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Llama;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * @author leee leee
 */

public class LlamaMouth implements Listener {

    private Server server;

    public LlamaMouth(Server server) {
        super();
        this.server = server;
    }

    private static String playerLocStr(Player player) {
        Location loc = player.getLocation();
        return "" + ChatColor.GREEN + loc.getBlockX() + ", " + loc.getBlockZ();
    }
    
    @EventHandler
    public void stopFishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if (event.getState() == PlayerFishEvent.State.REEL_IN) {
            //player.chat("I'm fishing at " + ChatColor.AQUA + playerLocStr(player) + ". " + ChatColor.RED + "Come get some!");
            
            String message = "I'm fishing at " + ChatColor.AQUA + playerLocStr(player) + ". " + ChatColor.RED + "Come get some!";
            String command = "say " + message;
            this.server.dispatchCommand(player, command);
        }
    }

    public void closestEntityRunCommand(Player player, String entityName, String command) {
        String entitySelector = "@e[limit=1, sort=nearest, type=minecraft:" + entityName + ", distance=..8]";
        String serverCommand = "execute as " + entitySelector + " run " + command;
        this.server.dispatchCommand(player, serverCommand);
    }

    @EventHandler
    public void ridingLamma(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (event.getRightClicked() instanceof Llama) {
            String msg = ChatColor.RED + "Someone get this guy off me! I'm at " + playerLocStr(player);
            closestEntityRunCommand(player, "llama", "say " + msg);
            closestEntityRunCommand(player, "trader_llama", "say " + msg);
        }
    }
}
