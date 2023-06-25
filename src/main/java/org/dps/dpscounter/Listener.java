package org.dps.dpscounter;

import com.elmakers.mine.bukkit.api.event.CastEvent;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Score;

public class Listener implements org.bukkit.event.Listener {
    public Listener(DPSCounter plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);}
    static long startTime;
    static Player player;
    MagicAPI magicAPI;

    @EventHandler
    public void onCast(CastEvent event) {
        if(event.getMage().getEntity().getType() == EntityType.PLAYER && event.getSpellResult().isSuccess()) {
            player = Bukkit.getPlayer(event.getMage().getName());
            if(DPSCounter.players.contains(player)) {
                Bukkit.getLogger().info(String.valueOf(event.getMage().getLastDamageDealt()));
                Score score = DPSCounter.HiddenObjective.getScore(player);
                score.setScore((int) (score.getScore() + event.getMage().getLastDamageDealt()));
                DPSCounter.IntervalObjective.getScore(player).setScore((int) System.currentTimeMillis() / 1000);
                Bukkit.getLogger().info(String.valueOf(DPSCounter.IntervalObjective.getScore(player)));
                if(DPSCounter.FirstObjective.getScore(player.getName()).getScore() == 1) {
                    StartCountdown();
                }
            }

        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setScoreboard(DPSCounter.dpsScore);
        player.setScoreboard(DPSCounter.ScoreHidden);
        player.setScoreboard(DPSCounter.FirstTime);
        player.setScoreboard(DPSCounter.Interval);
        player.setScoreboard(DPSCounter.timeScore);
        Bukkit.getLogger().info(String.valueOf(player.getScoreboard()));
        Bukkit.getLogger().info("player joined");
    }

    public void StartCountdown() {
        startTime = System.currentTimeMillis() / 1000;
        DPSCounter.timeObjective.getScore(player.getName()).setScore((int) startTime);
        DPSCounter.FirstObjective.getScore(player.getName()).setScore(0);
    }

}
