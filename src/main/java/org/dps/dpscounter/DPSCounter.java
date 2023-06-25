package org.dps.dpscounter;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public final class DPSCounter extends JavaPlugin {
    public static DPSCounter plugin;
    MagicAPI magicAPI;

    static Scoreboard dpsScore;
    static Scoreboard timeScore;
    static Scoreboard ScoreHidden;
    static Scoreboard FirstTime;
    static Scoreboard Interval;

    static Objective timeObjective;
    static Objective dpsObjective;
    static Objective HiddenObjective;
    static Objective FirstObjective;
    static Objective IntervalObjective;

    static ArrayList<Player> players;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Startup");

        plugin = this;
        new Listener(this);

        getCommand("startcountdown").setExecutor(new StartCountdown());
        getCommand("endcountdown").setExecutor(new EndCountdown());
        getCommand("dps").setExecutor(new DPS());

        Scoreboard dpsScore = Bukkit.getScoreboardManager().getNewScoreboard();
        Scoreboard ScoreHidden = Bukkit.getScoreboardManager().getNewScoreboard();
        Scoreboard timeScore = Bukkit.getScoreboardManager().getNewScoreboard();
        Scoreboard FirstTime = Bukkit.getScoreboardManager().getNewScoreboard();
        Scoreboard Interval = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective dpsObjective = dpsScore.registerNewObjective("DPS", "dummy", "DPS");
        Objective HiddenObjective = ScoreHidden.registerNewObjective("Hidden", "dummy", "Hidden");
        Objective timeObjective = timeScore.registerNewObjective("Time", "dummy", "Time");
        Objective FirstObjective = FirstTime.registerNewObjective("First", "dummy", "First");
        Objective IntervalObjective = Interval.registerNewObjective("Interval", "dummy", "Interval");



        players = new ArrayList<>();

        dpsObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        DPSCounter.dpsObjective = dpsObjective;
        dpsScore = dpsScore;
        DPSCounter.ScoreHidden = ScoreHidden;
        DPSCounter.HiddenObjective = HiddenObjective;
    }

    public static void average(long Time, Player player) {
            Score score = HiddenObjective.getScore(player.getName());

            int currentScore = score.getScore();
            int dividedScore = (int) (currentScore / Time);

            score.setScore(0);
            timeObjective.getScore(player.getName()).setScore(0);
            IntervalObjective.getScore(player.getName()).setScore(0);
            dpsObjective.getScore(player.getName()).setScore(dividedScore);

            Bukkit.getLogger().info("Score Set!");
            Bukkit.getLogger().info(String.valueOf(currentScore) + String.valueOf(dividedScore));
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutdown");
    }
}
