package com.lucas.privatechatplugin;

import com.lucas.privatechatplugin.commands.MessageCommand;
import com.lucas.privatechatplugin.commands.ReplyCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class PrivateChatPlugin extends JavaPlugin implements Listener {

    private HashMap <UUID, UUID> recentMessages;

    @Override
    public void onEnable() {
        // Plugin startup logic

        recentMessages = new HashMap<>();

        getCommand("tell").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));

        Bukkit.getPluginManager().registerEvents(this, this);

    }

    public HashMap<UUID, UUID> getRecentMessages(){return recentMessages;}

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e){
        recentMessages.remove(e.getPlayer().getUniqueId());
    }
}
