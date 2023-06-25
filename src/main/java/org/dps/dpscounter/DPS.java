package org.dps.dpscounter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

public class DPS implements CommandExecutor {
    Score DPS;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players are permitted to perform this action");
        }

        Player player = (Player) sender;
        DPS = DPSCounter.dpsObjective.getScore(player.getName());
        player.sendMessage("Your average DPS for the last boss fought was: " + DPS.getScore());
        return true ;
    }
}
