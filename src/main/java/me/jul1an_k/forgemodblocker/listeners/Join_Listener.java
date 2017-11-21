package me.jul1an_k.forgemodblocker.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jul1an_k.forgemodblocker.ForgeModBlocker;

public class Join_Listener implements Listener {
	
	private ForgeModBlocker instance;
	
	public Join_Listener(ForgeModBlocker instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		Bukkit.getScheduler().runTaskLater(instance, () -> {
			p.sendPluginMessage(instance, "FML|HS", new byte[] { -2, 0 });
			p.sendPluginMessage(instance, "FML|HS", new byte[] { 0, 2, 0, 0, 0, 0 });
			p.sendPluginMessage(instance, "FML|HS", new byte[] { 2, 0, 0, 0, 0 });
		}, 5);
	}
	
}
