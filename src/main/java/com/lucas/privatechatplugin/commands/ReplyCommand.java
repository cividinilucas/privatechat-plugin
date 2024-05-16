package com.lucas.privatechatplugin.commands;

import com.lucas.privatechatplugin.PrivateChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {


    private PrivateChatPlugin privateChatPlugin;
    public ReplyCommand(PrivateChatPlugin privateChatPlugin){
        this.privateChatPlugin = privateChatPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length >= 1){
                if (privateChatPlugin.getRecentMessages().containsKey(player.getUniqueId())){
                    UUID uuid = privateChatPlugin.getRecentMessages().get(player.getUniqueId());
                    if(Bukkit.getPlayer(uuid) != null){
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder build = new StringBuilder();
                        for(int i = 0; i < args.length; i++) {
                            build.append(args[i]).append(" ");
                        }

                        player.sendMessage("You --> " + target.getName() + " :" + build);
                        target.sendMessage(ChatColor.GREEN + player.getName() + " --> " + build);
                    }else{
                        player.sendMessage(ChatColor.RED + "The player is off-line now");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }
            } else{
                player.sendMessage(ChatColor.RED + "You need to write a message! ");
            }

        }


        return false;
    }
}
