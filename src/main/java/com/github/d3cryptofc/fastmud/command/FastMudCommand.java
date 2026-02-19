package com.github.d3cryptofc.fastmud.command;

import com.github.d3cryptofc.fastmud.abc.CommandGroup;
import com.github.d3cryptofc.fastmud.constant.PluginPrefixes;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FastMudCommand extends CommandGroup {

    // The command name.
    private static final String name = "fastmud";

    public FastMudCommand() {
        super();
        this.addSubcommand(new ReloadCommand());
    }

    public String getName() {
        return FastMudCommand.name;
    }

    public List<String> onTabComplete(
        CommandSender sender,
        Command command,
        String label,
        String[] args
    ) {
        // No command arguments? Show nothing.
        if (args.length == 0) return List.of();

        // Single command argument? Show subcommands.
        if (args.length == 1) {
            return this.subcommands.keySet()
                .stream()
                .filter(x -> x.startsWith(args[0]))
                .toList();
        }

        // Any command argument length? Try to get subcommand.
        CommandGroup subcommand = this.getSubcommand(args[0]);

        // Subcommand doesn't exists? Show nothing.
        if (subcommand == null) return List.of();

        // Subcommand exists, make propagation.
        return subcommand.onTabComplete(sender, command, label, args);
    }

    public boolean onCommand(
        CommandSender sender,
        Command command,
        String label,
        String[] args
    ) {
        // NOTE: IS NOT NECESSARY TO CHECK PLAYER PERMISSION HERE,

        // No command arguments? Execute this command.
        if (args.length == 0) return this.execute(sender, label);

        // Any command argument length? Try to get subcommand.
        CommandGroup subcommand = getSubcommand(args[0]);

        // Subcommand doesn't exists? Break command.
        if (subcommand == null) return true;

        // Subcommand exists, make propagation.
        return subcommand.onCommand(sender, command, label, args);
    }

    public boolean execute(CommandSender sender, String label) {
        // OPTIMIZE: Unnecessary creation of a TextComponent for each call,
        // but it doesn't seem like anyone will spam the command enough to make
        // it noticeable.
        //
        // Create a TextComponent for command.
        TextComponent command = new TextComponent("/fastmud reload");
        // When clicked, autocomplete it.
        command.setClickEvent(
            new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command.getText())
        );
        // When hover, show "Click here".
        command.setHoverEvent(
            new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click here"))
        );
        // Set color to gray.
        command.setColor(ChatColor.GRAY);

        // Send message to player.
        sender
            .spigot()
            .sendMessage(
                new TextComponent(PluginPrefixes.CHAT + "Try: "),
                command
            );

        return true;
    }
}
