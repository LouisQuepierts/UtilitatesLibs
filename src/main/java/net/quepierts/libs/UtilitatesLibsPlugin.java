package net.quepierts.libs;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class UtilitatesLibsPlugin extends JavaPlugin {
    public static final File deprecatedPath = new File("plugins\\UtilitatesLibs\\configuration.yml");

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
