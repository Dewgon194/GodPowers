package com.westosia.godpowers;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    @Override
    public void onEnable() {
        instance = this;
        System.out.println(ChatColor.AQUA + "God Powers are enabled!");
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
    }

    public static Main getInstance() {
        return instance;
    }
}
