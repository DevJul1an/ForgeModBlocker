package me.jul1an_k.forgemodblocker.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.jul1an_k.forgemodblocker.utils.ModData;

public class ModDataReceivedEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	
	private Player player;
	private ModData modData;
	
	public ModDataReceivedEvent(Player player, ModData modData) {
		this.player = player;
		this.modData = modData;
		
		try {
			ForgeModBlockerAPI.setMods(player, modData.getMods());
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ModData getModData() {
		return modData;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
