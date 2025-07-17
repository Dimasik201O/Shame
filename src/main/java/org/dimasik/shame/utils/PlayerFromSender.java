package org.dimasik.shame.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dimasik.shame.exceptions.NotAPlayerException;

public class PlayerFromSender {
    public static Player getPlayer(CommandSender sender) throws NotAPlayerException{
        if(sender instanceof Player){
            return (Player) sender;
        }
        else{
            throw new NotAPlayerException();
        }
    }
}
