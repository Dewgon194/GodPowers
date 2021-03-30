package com.westosia.godpowers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LightningGod {

    private final Main instance = Main.getInstance();

    public void lightningBeam(Player player){
        List<Block> los = player.getLineOfSight(null, 32);
        World world = player.getWorld();
        world.strikeLightning(player.getLocation());
        player.setGlowing(true);
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            world.strikeLightning(player.getLocation());
            Bukkit.getScheduler().runTaskLater(instance, () -> {
                world.strikeLightning(player.getLocation());
            }, 8);
        }, 4);
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            player.setGlowing(false);
            int i;
            for (i = 0; i < los.size(); i++) {
                int finalI = i;
                Bukkit.getScheduler().runTaskLater(instance, () -> {
                    world.strikeLightning(los.get(finalI).getLocation());
                }, i);
            }
        }, 28);
    }

    public void lightningAOEStrike(Player player){
        ArrayList<Entity> lightningVictims = new ArrayList<>(player.getNearbyEntities(10, 10, 10));
        World world = player.getWorld();
        for (Entity victim : lightningVictims) {
            if (victim instanceof LivingEntity && victim != player) {
                world.strikeLightning(victim.getLocation());
            }
        }
    }
}
