package com.github.d3cryptofc.fastmud;

import com.github.d3cryptofc.fastmud.constant.AnsiColors;
import com.github.d3cryptofc.fastmud.manager.PluginFeatureManager;
import java.util.logging.Logger;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FastMud extends JavaPlugin {

    // Single FastMud instance.
    private static FastMud instance;

    private PluginFeatureManager pluginFeatureManager;
    private Logger logger;

    public static FastMud getInstance() {
        // Get the single instance.
        return FastMud.instance;
    }

    public void showBanner() {
        String[] lines = {
            "  ___  ___  ___ ____",
            " /__  /__/ /__   /  " + AnsiColors.BLUE + "MUD",
            "/    /  / ___/  /  v" + this.getDescription().getVersion(),
            "",
        };

        String starting_color;

        for (int idx = 0; idx < lines.length; idx++) {
            starting_color = idx != 0 ? AnsiColors.DARK_GRAY : AnsiColors.BLUE;

            this.logger.info(starting_color + lines[idx]);
        }
    }

    @Override
    public void onEnable() {
        // Set the single instance.
        FastMud.instance = this;

        this.logger = this.getLogger();
        this.saveDefaultConfig();

        // Showing plugin banner.
        this.showBanner();

        // Enabling features.
        this.pluginFeatureManager = new PluginFeatureManager();
        this.pluginFeatureManager.loadAll();
    }

    public void reload() {
        // Unregister all event listeners from this plugin.
        HandlerList.unregisterAll(this);
        // Reload YAML plugin configuration.
        this.reloadConfig();
        // Reload new reloaded configuration
        this.pluginFeatureManager.reloadConfig();
        // Load all features again.
        this.pluginFeatureManager.loadAll();
    }
}
