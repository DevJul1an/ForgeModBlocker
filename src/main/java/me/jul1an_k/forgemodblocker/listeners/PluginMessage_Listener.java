package me.jul1an_k.forgemodblocker.listeners;

import me.jul1an_k.forgemodblocker.ForgeModBlocker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import me.jul1an_k.forgemodblocker.api.ModDataReceivedEvent;
import me.jul1an_k.forgemodblocker.utils.ModData;

public class PluginMessage_Listener implements PluginMessageListener {

	private ForgeModBlocker instance;

	public PluginMessage_Listener(ForgeModBlocker instance) {
		this.instance = instance;
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] data) {
		if(player == null || !player.isOnline())
			return;
		
		if(channel.equals("FML|HS")) {
			ModData modData = ModData.getModData(data);

			if(modData.getMods().isEmpty())
				return;

			Bukkit.getPluginManager().callEvent(new ModDataReceivedEvent(player, modData));
		} else if(channel.equals("LABYMOD")) {
			if(instance.getConfig().getBoolean("blockLabyMod")) {
				String kickReason = instance.getConfig().getString("blockedModKickMessage") + "\n";

				kickReason += "\n" + instance.getConfig().getString("blockedModListingFormat").replace("%mod_name%", "LabyMod").replace("%mod_version%", new String(data).split(" ")[1]);

				kickReason = ChatColor.translateAlternateColorCodes('&', kickReason);

				player.kickPlayer(kickReason);
			}
		}
	}
	
}
