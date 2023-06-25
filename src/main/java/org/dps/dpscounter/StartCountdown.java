package org.dps.dpscounter;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCountdown implements CommandExecutor {

    public  StartCountdown() {

    }
    static Player player;
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        player = Bukkit.getPlayer(args[0]);
        DPSCounter.players.add(player);
        DPSCounter.FirstObjective.getScore(player.getName()).setScore(Integer.parseInt("1"));
        return true;
    }
}
