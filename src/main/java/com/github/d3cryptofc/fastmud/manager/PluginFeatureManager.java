package com.github.d3cryptofc.fastmud.manager;

import com.github.d3cryptofc.fastmud.FastMud;
import com.github.d3cryptofc.fastmud.listener.BlockFadeToWaterOverDirt;
import com.github.d3cryptofc.fastmud.listener.DispenseWaterOverDirt;
import com.github.d3cryptofc.fastmud.listener.PlaceDirtOverWater;
import com.github.d3cryptofc.fastmud.listener.WaterBucketOverDirt;
import com.github.d3cryptofc.fastmud.listener.WaterFlowOverDirt;
import com.github.d3cryptofc.fastmud.utils.HumanUtils;
import java.util.logging.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class PluginFeatureManager {

    private FastMud plugin;
    private PluginManager pluginManager;
    private ConfigurationSection whenConfigSection;
    private Logger logger;

    public PluginFeatureManager() {
        // Get the plugin.
        this.plugin = FastMud.getInstance();
        // Get plugin manager.
        this.pluginManager = plugin.getServer().getPluginManager();
        // Loading plugin config.
        this.reloadConfig();
        // Get plugin logger.
        this.logger = plugin.getLogger();
    }

    public void reloadConfig() {
        // Get plugin configuration.
        FileConfiguration configuration = plugin.getConfig();
        // Get "when" configuration section.
        this.whenConfigSection = configuration.getConfigurationSection("when");
    }

    public void load(
        String description,
        String sectionName,
        Listener listener
    ) {
        // Get feature configuration.
        ConfigurationSection section =
            this.whenConfigSection.getConfigurationSection(sectionName);

        // Get feature enabled in configuration.
        boolean enabled = section.getBoolean("enabled", false);

        // Enabled feature? Register event listener.
        if (enabled) this.pluginManager.registerEvents(listener, this.plugin);

        // Log feature configuration.
        this.logger.info(
            String.format(
                "%s: %s",
                description,
                HumanUtils.booleanToHumanLikeOnOff(enabled)
            )
        );
    }

    public void loadWaterBucketOverDirt() {
        // Loading feature.
        this.load(
            "Water Bucket Over Dirt",
            "water_bucket_over_dirt",
            new WaterBucketOverDirt()
        );
    }

    public void loadWaterFlowOverDirt() {
        // Loading feature.
        this.load(
            "Water Flow Over Dirt",
            "water_flow_over_dirt",
            new WaterFlowOverDirt()
        );
    }

    public void loadPlaceDirtOverWater() {
        // Loading feature.
        this.load(
            "Place Dirt Over Water",
            "place_dirt_over_water",
            new PlaceDirtOverWater()
        );
    }

    public void loadBlockFadeToWaterOverDirt() {
        // Loading feature.
        this.load(
            "Block Fade To Water Over Dirt",
            "block_fade_to_water_over_dirt",
            new BlockFadeToWaterOverDirt()
        );
    }

    public void loadDispenseWaterOverDirt() {
        // Loading feature.
        this.load(
            "Dispense Water Over Dirt",
            "dispense_water_over_dirt",
            new DispenseWaterOverDirt()
        );
    }

    public void loadAll() {
        Runnable[] runnables = {
            this::loadWaterBucketOverDirt,
            this::loadWaterFlowOverDirt,
            this::loadPlaceDirtOverWater,
            this::loadBlockFadeToWaterOverDirt,
            this::loadDispenseWaterOverDirt,
        };

        for (Runnable runnable : runnables) runnable.run();
        if (runnables.length > 0) this.logger.info("");
    }
}
