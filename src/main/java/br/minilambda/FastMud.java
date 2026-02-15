package br.minilambda;

import br.minilambda.listeners.DispenseWaterOverDirt;
import br.minilambda.listeners.IceBlockFadeToWaterOverDirt;
import br.minilambda.listeners.PlaceDirtUnderWater;
import br.minilambda.listeners.WaterBucketOverDirt;
import br.minilambda.listeners.WaterFlowOverDirt;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FastMud extends JavaPlugin {

    @Override
    public void onEnable() {
        Listener[] listeners = {
            new WaterBucketOverDirt(),
            new WaterFlowOverDirt(),
            new PlaceDirtUnderWater(),
            new IceBlockFadeToWaterOverDirt(),
            new DispenseWaterOverDirt(),
        };

        PluginManager pm = this.getServer().getPluginManager();

        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
        }
    }
}
