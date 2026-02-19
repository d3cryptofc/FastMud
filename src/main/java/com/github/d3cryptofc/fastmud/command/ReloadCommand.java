package com.github.d3cryptofc.fastmud.command;

import com.github.d3cryptofc.fastmud.abc.CommandGroup;
import com.github.d3cryptofc.fastmud.constant.PluginDefaultMessages;
import com.github.d3cryptofc.fastmud.constant.PluginPrefixes;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand extends CommandGroup {

    // The command name.
    private static final String name = "reload";

    public String getName() {
        return ReloadCommand.name;
    }

    public List<String> onTabComplete(
        CommandSender sender,
        Command command,
        String label,
        String[] args
    ) {
        // Show nothing.
        return List.of();
    }

    public boolean onCommand(
        CommandSender sender,
        Command command,
        String label,
        String[] args
    ) {
        // Check player has permission to use the command.
        if (!sender.hasPermission("fastmud.command.reload")) {
            sender.sendMessage(PluginDefaultMessages.YOU_DONT_HAVE_PERMISSION);
            return true;
        }

        // Execute this command.
        return this.execute(sender);
    }

    public boolean execute(CommandSender sender) {
        // Show the plugin banner in console.
        this.plugin.showBanner();
        // Reloading the plugin (configuration and event listeners).
        this.plugin.reload();

        // Message: "Plugin reloaded"
        String message = "Configuration and event listeners has been reloaded!";
        // Send message to sender only if is a player.
        if (sender instanceof Player) {
            sender.sendMessage(PluginPrefixes.CHAT + ChatColor.GREEN + message);
        }
        // Log message to console.
        this.logger.info(message);

        return true;
    }
}
