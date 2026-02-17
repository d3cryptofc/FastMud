package br.minilambda;

import br.minilambda.listeners.DispenseWaterOverDirt;
import br.minilambda.listeners.IceBlockFadeToWaterOverDirt;
import br.minilambda.listeners.PlaceDirtOverWater;
import br.minilambda.listeners.WaterBucketOverDirt;
import br.minilambda.listeners.WaterFlowOverDirt;
import br.minilambda.utils.AnsiColor;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FastMud extends JavaPlugin {

    public void showBanner() {
        String[] lines = {
            "  ___  ___  ___ ____",
            " /__  /__/ /__   /  " + AnsiColor.BLUE + "MUD",
            "/    /  / ___/  /  v" + this.getDescription().getVersion(),
            "",
        };

        Logger logger = this.getLogger();
        String starting_color;

        for (int idx = 0; idx < lines.length; idx++) {
            starting_color = idx != 0 ? AnsiColor.DARK_GRAY : AnsiColor.BLUE;

            logger.info(starting_color + lines[idx]);
        }
    }

    @Override
    public void onEnable() {
        Listener[] listeners = {
            new WaterBucketOverDirt(),
            new WaterFlowOverDirt(),
            new PlaceDirtOverWater(),
            new IceBlockFadeToWaterOverDirt(),
            new DispenseWaterOverDirt(),
        };

        PluginManager pm = this.getServer().getPluginManager();

        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
        }

        showBanner();
    }
}
