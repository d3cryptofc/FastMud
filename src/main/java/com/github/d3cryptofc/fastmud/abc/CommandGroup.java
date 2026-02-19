package com.github.d3cryptofc.fastmud.abc;

import com.github.d3cryptofc.fastmud.FastMud;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.command.TabExecutor;

public abstract class CommandGroup implements TabExecutor {

    protected FastMud plugin;
    protected Logger logger;
    protected HashMap<String, CommandGroup> subcommands;

    public CommandGroup() {
        this.plugin = FastMud.getInstance();
        this.logger = plugin.getLogger();
        this.subcommands = new HashMap<>();
    }

    public abstract String getName();

    public void addSubcommand(CommandGroup subcommand) {
        this.subcommands.put(subcommand.getName(), subcommand);
    }

    public CommandGroup getSubcommand(String name) {
        return this.subcommands.get(name);
    }
}
