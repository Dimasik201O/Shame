package org.dimasik.shame.command;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.dimasik.shame.utils.Parser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommandExecutor implements TabExecutor {
    @Getter
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length < 1){
            sender.sendMessage(Parser.color("&b&l▶ &fИспользование: &6/shame [" + String.join("/", subCommands.keySet()) + "]"));
            return true;
        }

        String subCommandKey = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandKey);
        if(subCommand == null){
            sender.sendMessage(Parser.color("&b&l▶ &fИспользование: &6/shame [" + String.join("/", subCommands.keySet()) + "]"));
            return true;
        }

        if(subCommand.getRequiredArgs() + 2 > args.length){
            sender.sendMessage(Parser.color("&b&l▶ &fИспользование: &6/shame " + subCommandKey + " [игрок] " + subCommand.getUsage()));
            return true;
        }

        subCommand.execute(sender, command, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(subCommands.keySet());
        }
        else if(args.length == 2){
            for(Player player : Bukkit.getOnlinePlayers()){
                completions.add(player.getName());
            }
        }
        else {
            SubCommand subCommand = subCommands.get(args[0]);
            if(subCommand != null){
                completions.addAll(subCommand.getTabCompletes(args.length - 2));
            }
        }

        List<String> completions1 = new ArrayList<>();
        for(String s : completions){
            if(s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())){
                completions1.add(s);
            }
        }

        return completions1;
    }
}
