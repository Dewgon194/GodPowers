package com.westosia.godpowers;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {

    private ArchMage archMage;
    private AirGod airGod;
    private ChaosGod chaosGod;
    private LightningGod lightningGod;

    public ClickListener(ArchMage archMage, AirGod airGod, ChaosGod chaosGod, LightningGod lightningGod) {
        this.archMage = archMage;
        this.airGod = airGod;
        this.chaosGod = chaosGod;
        this.lightningGod = lightningGod;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action click = e.getAction();
        ItemStack item = e.getItem();
        if (item != null && item.getItemMeta().hasCustomModelData() && item.hasItemMeta() && item.getType().equals(Material.DIAMOND_SWORD)) {
            int modelData = item.getItemMeta().getCustomModelData();

            if (click.equals(Action.RIGHT_CLICK_AIR)) {
                switch (modelData) {
                    case 1:
                        chaosGod.chaosTeleport(player);
                        break;
                    case 2:
                        airGod.airSlam(player);
                        break;
                    case 3:
                        lightningGod.lightningBeam(player);
                        break;
                    case 4:
                        archMage.archMageSelfInvincible(player);
                        break;
                    default:
                        break;
                }
            } else if (click.equals(Action.RIGHT_CLICK_BLOCK)) {
                switch (modelData) {
                    case 1:
                        chaosGod.chaosTeleport(player);
                        break;
                    case 2:
                        airGod.airDeflect(player);
                        break;
                    case 3:
                        lightningGod.lightningAOEStrike(player);
                        break;
                    case 4:
                        archMage.archMageAOEInvincible(player);
                        break;
                }
            }
        }
    }
}

