package com.westosia.godpowers;

import com.westosia.westosiaapi.WestosiaAPI;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class ArchMage {

    private final Main instance;

    public ArchMage() {
        instance = Main.getInstance();
    }

    public void archMageSelfInvincible(Player player) {
        PotionEffect invincibility = (new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 5));
        player.addPotionEffect(invincibility);
        player.setGlowing(true);
        WestosiaAPI.getParticleEmitter().playParticle(player.getLocation(), Particle.ENCHANTMENT_TABLE, 20, 1, 1, 1);
        WestosiaAPI.getSoundEmitter().playSound(player.getLocation(), 15, Sound.ENTITY_EVOKER_CAST_SPELL);
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            player.removePotionEffect(invincibility.getType());
            player.setGlowing(false);
            WestosiaAPI.getSoundEmitter().playSound(player.getLocation(), 15, Sound.BLOCK_GLASS_BREAK);
        }, 20 * 20);
    }

    public void archMageAOEInvincible(Player player) {
        ArrayList<Entity> invunPeople = new ArrayList<>(player.getNearbyEntities(3, 3, 3));
        PotionEffect invincibility = (new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
        player.addPotionEffect(invincibility);
        player.setGlowing(true);
        WestosiaAPI.getSoundEmitter().playSound(player.getLocation(), 15, Sound.ENTITY_EVOKER_CAST_SPELL);
        WestosiaAPI.getParticleEmitter().playParticle(player.getLocation(), Particle.ENCHANTMENT_TABLE, 100, 3, 3, 3);

        Bukkit.getScheduler().runTaskLater(instance, () -> {
            player.removePotionEffect(invincibility.getType());
            player.setGlowing(false);
            WestosiaAPI.getSoundEmitter().playSound(player.getLocation(), 15, Sound.BLOCK_GLASS_BREAK);
        }, 20 * 10);

        for (Entity victim : invunPeople) {
            if ((victim instanceof LivingEntity)) {
                ((LivingEntity) victim).addPotionEffect(invincibility);
                victim.setGlowing(true);
                Bukkit.getScheduler().runTaskLater(instance, () -> {
                    ((LivingEntity) victim).removePotionEffect(invincibility.getType());
                    victim.setGlowing(false);
                }, 20 * 10);
            }
        }
    }
}
