package me.jul1an_k.forgemodblocker.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import me.jul1an_k.forgemodblocker.api.ModDataReceivedEvent;
import me.jul1an_k.forgemodblocker.utils.ModData;

public class PluginMessage_Listener implements PluginMessageListener {
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] data) {
		if(player == null || !player.isOnline())
			return;
		
		ModData modData = ModData.getModData(data);
		
		if(modData.getMods().isEmpty())
			return;
		
		Bukkit.getPluginManager().callEvent(new ModDataReceivedEvent(player, modData));
	}
	
}
