package org.dimasik.shame.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.dimasik.shame.Shame;
import org.dimasik.shame.utils.Parser;

import java.util.List;

public abstract class SubCommand {
    @Getter
    private String name;

    public SubCommand(String name) {
        this.name = name;
    }

    public abstract void execute(CommandSender sender, Command command, String[] args);

    public abstract List<String> getTabCompletes(int args);

    public abstract int getRequiredArgs();

    public abstract String getUsage();

    public void register(){
        Shame.getInstance().getCommandExecutor().getSubCommands().put(name, this);
    }

    public void leaveUsage(CommandSender sender){
        sender.sendMessage(Parser.color("&b&l▶ &fИспользование: &6/shame " + name + " [игрок] " + getUsage()));
    }

    public void leavePNF(CommandSender sender){
        sender.sendMessage(Parser.color("&b&l▶ &fИгрок &6не найден&f."));
    }
}
