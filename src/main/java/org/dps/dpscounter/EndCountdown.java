package org.dps.dpscounter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Score;

import java.util.Objects;

public class EndCountdown implements CommandExecutor {

    static int BetweenTime;
    Score Time1;
    Score Time2;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Time1 = DPSCounter.IntervalObjective.getScore(Objects.requireNonNull(Bukkit.getPlayer(args[0])));
        Time2 = DPSCounter.timeObjective.getScore(Objects.requireNonNull(Bukkit.getPlayer(args[0])));
        DPSCounter.players.remove(Bukkit.getPlayer(args[0]));
        BetweenTime = Time1.getScore() - Time2.getScore();
        DPSCounter.average(BetweenTime, Objects.requireNonNull(Bukkit.getPlayer(args[0])));
        return true;
    }
}
