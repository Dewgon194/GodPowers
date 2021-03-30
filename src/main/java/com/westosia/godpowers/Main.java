package com.westosia.godpowers;

import javafx.scene.effect.Light;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        ArchMage archMage = new ArchMage();
        AirGod airGod = new AirGod();
        ChaosGod chaosGod = new ChaosGod();
        LightningGod lightningGod = new LightningGod();
        System.out.println(ChatColor.AQUA + "God Powers are enabled!");
        getServer().getPluginManager().registerEvents(new ClickListener(archMage, airGod, chaosGod, lightningGod), this);
    }

    public static Main getInstance() {
        return instance;
    }
}
