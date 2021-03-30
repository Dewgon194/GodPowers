package com.westosia.godpowers;

import com.westosia.westosiaapi.WestosiaAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.ArrayList;

public class AirGod {

    private final Main instance = Main.getInstance();

    public void airSlam(Player player){
        WestosiaAPI.getSoundEmitter().playSound(player.getLocation(), 10, Sound.ITEM_ELYTRA_FLYING, 1, 2);
        ArrayList<Entity> airVictims = new ArrayList<>(player.getNearbyEntities(10, 10, 10));
        for (Entity victim : airVictims) {
            if ((victim instanceof LivingEntity || victim.getType().equals(EntityType.ARROW)) && victim != player) {
                victim.setVelocity(new Vector(0, 0.6, 0));
                victim.setGravity(false);
                Bukkit.getScheduler().runTaskLater(instance, () -> {
                    victim.setGravity(true);
                    victim.setVelocity(new Vector(0, -4, 0));
                    WestosiaAPI.getSoundEmitter().playSound(player.getLocation(), 10, Sound.ENTITY_PLAYER_ATTACK_SWEEP);
                }, 60);
            }
        }
    }

    public void airDeflect(Player player){
        ArrayList<Entity> airVictims = new ArrayList<>(player.getNearbyEntities(10, 10, 10));
        for (Entity victim : airVictims) {
            if ((victim instanceof LivingEntity || victim.getType().equals(EntityType.ARROW)) && victim != player) {
                Vector dif = victim.getLocation().toVector().subtract(player.getLocation().toVector());
                victim.setVelocity(dif.normalize().multiply(3).add(new Vector(0, 1, 0)));
            }
        }

    }
}
