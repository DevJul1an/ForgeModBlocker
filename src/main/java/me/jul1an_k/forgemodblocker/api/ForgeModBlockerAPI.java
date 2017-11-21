package me.jul1an_k.forgemodblocker.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ForgeModBlockerAPI {
	
	private static final Map<UUID, Map<String, String>> PLAYER_MODS = new HashMap<>();
	
	public static Map<String, String> getMods(Player player) {
		if(PLAYER_MODS.containsKey(player.getUniqueId()))
			return PLAYER_MODS.get(player.getUniqueId());
		
		return null;
	}
	
	static void setMods(Player player, Map<String, String> mods) throws IllegalAccessException {
		if(PLAYER_MODS.containsKey(player.getUniqueId()))
			throw new IllegalAccessException("Can't set mods when already set.");
		
		PLAYER_MODS.put(player.getUniqueId(), mods);
	}
	
}
