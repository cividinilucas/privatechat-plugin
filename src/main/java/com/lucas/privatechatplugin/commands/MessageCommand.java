package com.lucas.privatechatplugin.commands;

import com.lucas.privatechatplugin.PrivateChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {


    private PrivateChatPlugin privateChatPlugin;
    public MessageCommand(PrivateChatPlugin privateChatPlugin){
        this.privateChatPlugin = privateChatPlugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 2) {
                if(Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder build = new StringBuilder();
                    for(int i = 1; i < args.length; i++) {
                        build.append(args[i]).append(" ");
                    }

                    player.sendMessage("You --> " + target.getName() + " :" + build);
                    target.sendMessage(ChatColor.GREEN + player.getName() + " --> " + build);

                    privateChatPlugin.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

                }else{
                    player.sendMessage(ChatColor.RED + "Player not found");
                }
            }else{
                player.sendMessage(ChatColor.RED + "Invalid Usage: /tell <player> <message>");
            }

        }


        return false;
    }
}
