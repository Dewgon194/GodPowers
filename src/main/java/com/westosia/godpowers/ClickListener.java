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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ClickListener implements Listener {

    private final Main instance = Main.getInstance();
    private ArchMage archMage;
    private AirGod airGod;
    private ChaosGod chaosGod;
    private LightningGod lightningGod;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action click = e.getAction();
        ItemStack item = e.getItem();
        if (item != null && item.getItemMeta().hasCustomModelData() && item.hasItemMeta() && item.getType().equals(Material.DIAMOND_SWORD)) {
            int modelData = item.getItemMeta().getCustomModelData();
            if ((click.equals(Action.RIGHT_CLICK_AIR) || click.equals(Action.RIGHT_CLICK_BLOCK)) && modelData == 1) {
                chaosGod.chaosPower1(player);

            } else if (click.equals(Action.RIGHT_CLICK_BLOCK) && modelData == 2) {

                airGod.airPower1(player);

            } else if (click.equals(Action.RIGHT_CLICK_AIR) && modelData == 2) {

                airGod.airPower2(player);

            } else if (click.equals(Action.RIGHT_CLICK_AIR) && modelData == 3) {
                lightningGod.lightningPower1(player);


            } else if (click.equals(Action.RIGHT_CLICK_BLOCK) && modelData == 3) {
                lightningGod.lightningPower2(player);

            } else if (click.equals(Action.RIGHT_CLICK_AIR) && modelData == 4) {
                archMage.archMagePower1(player);

            } else if (click.equals(Action.RIGHT_CLICK_BLOCK) && modelData == 4) {
                archMage.archMagePower2(player);

            }
        }
    }
}

