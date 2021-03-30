package com.westosia.godpowers;

import com.westosia.westosiaapi.WestosiaAPI;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class ChaosGod {

    public void chaosPower1(Player player) {
        ArrayList<Entity> chaosVictims = new ArrayList<>(player.getNearbyEntities(10, 10, 10));
        for (Entity victim : chaosVictims) {
            if (victim instanceof LivingEntity && victim != player) {
                WestosiaAPI.getParticleEmitter().playParticle(player.getLocation(), Particle.DRAGON_BREATH);
                victim.setVelocity(new Vector(player.getLocation().getDirection().multiply(10).getX(), 2, player.getLocation().getDirection().multiply(10).getZ()));
                victim.setFireTicks(100);
                victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1, 1);
            }
        }
        Random rand = new Random();
        int upperbound = 20;
        int randomX = rand.nextInt(upperbound);
        int randomY = 150;
        int randomZ = rand.nextInt(upperbound);
        Location newLoc = player.getLocation().add(randomX, randomY, randomZ);
        randomY = newLoc.getWorld().getHighestBlockYAt(newLoc);
        newLoc.setY(randomY + 1);
        player.teleport(newLoc);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1, 1);

    }
}


