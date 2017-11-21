package me.jul1an_k.forgemodblocker.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.jul1an_k.forgemodblocker.ForgeModBlocker;
import me.jul1an_k.forgemodblocker.api.ModDataReceivedEvent;
import me.jul1an_k.forgemodblocker.utils.ModData;

public class ModDataReceived_Listener implements Listener {
	
	private ForgeModBlocker instance;
	
	public ModDataReceived_Listener(ForgeModBlocker instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onModDataReceived(ModDataReceivedEvent event) {
		Player player = event.getPlayer();
		ModData modData = event.getModData();
		
		if(instance.getConfig().getBoolean("blockForge")) {
			player.kickPlayer(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("blockForgeKickMessage")));
			return;
		}
		
		if(instance.getConfig().getBoolean("blockMods")) {
			List<String> blockedMods = instance.getConfig().getStringList("blockedMods");
			
			if(!blockedMods.isEmpty()) {
				String kickReason = instance.getConfig().getString("blockedModKickMessage") + "\n";
				
				Map<String, String> mods = modData.getMods();
				
				for(String modName : mods.keySet()) {
					if(blockedMods.contains(modName)) {
						kickReason += "\n" + instance.getConfig().getString("blockedModListingFormat").replace("%mod_name%", modName).replace("%mod_version%", mods.get(modName));
					}
				}
				
				kickReason = ChatColor.translateAlternateColorCodes('&', kickReason);
				
				player.kickPlayer(kickReason);
				
				return;
			}
		}
	}
	
}
