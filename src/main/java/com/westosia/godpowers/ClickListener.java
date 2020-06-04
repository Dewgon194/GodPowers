package com.westosia.godpowers;


import com.westosia.westosiaapi.WestosiaAPI;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ClickListener implements Listener {

    private final Main instance = Main.getInstance();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType().equals(Material.DIAMOND_SWORD)  && e.getItem().getItemMeta().getCustomModelData() == 1) {
            ArrayList<Entity> airVictims = new ArrayList<Entity>();
            airVictims.addAll(player.getNearbyEntities(10, 10, 10));

            for (Entity victim : airVictims) {
                if (victim instanceof Entity && victim != player) {
                    WestosiaAPI.getParticleEmitter().playParticle(player.getLocation(), Particle.DRAGON_BREATH);
                    victim.setVelocity(new Vector(0, 0.6, 0));
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

        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType().equals(Material.DIAMOND_SWORD)  && e.getItem().getItemMeta().getCustomModelData() == 1) {
            ArrayList<Entity> airVictims = new ArrayList<Entity>();
            airVictims.addAll(player.getNearbyEntities(10, 10, 10));

            for (Entity victim : airVictims) {
                if (victim instanceof Entity && victim != player) {
                    WestosiaAPI.getParticleEmitter().playParticle(player.getLocation(), Particle.DRAGON_BREATH);
                    victim.setVelocity(new Vector(0, 0.6, 0));
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
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType().equals(Material.DIAMOND_SWORD) && e.getItem().getItemMeta().getCustomModelData() == 2) {

            ArrayList<Entity> airVictims = new ArrayList<Entity>();
            airVictims.addAll(player.getNearbyEntities(10, 10, 10));
            for (Entity victim : airVictims) {
                if (victim instanceof Entity && victim != player) {
                    victim.setVelocity(new Vector(0, 0.6, 0));
                    victim.setGravity(false);
                    Bukkit.getScheduler().runTaskLater(instance, () -> {

                        victim.setGravity(true);
                        victim.setVelocity(new Vector(0, -4, 0));
                    }, 60);
                }
            }

        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType().equals(Material.DIAMOND_SWORD) && e.getItem().getItemMeta().getCustomModelData() == 2) {

            ArrayList<Entity> airVictims = new ArrayList<Entity>();
            airVictims.addAll(player.getNearbyEntities(10, 10, 10));
            for (Entity victim : airVictims) {
                if (victim instanceof Entity && victim != player) {
                    Vector dif = victim.getLocation().toVector().subtract(player.getLocation().toVector());
                    victim.setVelocity(dif.normalize().multiply(3).add(new Vector(0, 1, 0)));
                }
            }

        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType().equals(Material.DIAMOND_SWORD) && e.getItem().getItemMeta().getCustomModelData() == 3) {
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


        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType().equals(Material.DIAMOND_SWORD) && e.getItem().getItemMeta().getCustomModelData() == 3) {

            ArrayList<Entity> lightningVictims = new ArrayList<Entity>();
            lightningVictims.addAll(player.getNearbyEntities(10, 10, 10));
            World world = player.getWorld();
            for (Entity victim : lightningVictims) {
                if (victim instanceof LivingEntity && victim != player) {
                    world.strikeLightning(victim.getLocation());
                }
            }
        }
    }
}
