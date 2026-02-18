package br.minilambda;

import br.minilambda.manager.PluginFeatureManager;
import br.minilambda.utils.AnsiColor;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class FastMud extends JavaPlugin {

    private Logger logger;

    public void showBanner() {
        String[] lines = {
            "  ___  ___  ___ ____",
            " /__  /__/ /__   /  " + AnsiColor.BLUE + "MUD",
            "/    /  / ___/  /  v" + this.getDescription().getVersion(),
            "",
        };

        String starting_color;

        for (int idx = 0; idx < lines.length; idx++) {
            starting_color = idx != 0 ? AnsiColor.DARK_GRAY : AnsiColor.BLUE;

            this.logger.info(starting_color + lines[idx]);
        }
    }

    @Override
    public void onEnable() {
        this.logger = this.getLogger();
        this.saveDefaultConfig();

        // Showing plugin banner.
        this.showBanner();

        // Enabling features.
        (new PluginFeatureManager(this)).loadAll();
    }
}
