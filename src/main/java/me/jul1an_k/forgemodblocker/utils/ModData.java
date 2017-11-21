package me.jul1an_k.forgemodblocker.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModData {
	
	private final Map<String, String> mods;
	private String readable;
	
	public ModData(Map<String, String> mods) {
		this.mods = mods;
		
		List<String> modsWithVersions = new ArrayList<>();
		for(Map.Entry<String, String> mod : mods.entrySet()) {
			modsWithVersions.add(mod.getKey() + " " + mod.getValue());
		}
		readable = String.join(", ", modsWithVersions);
	}
	
	public Set<String> getModsIds() {
		return Collections.unmodifiableSet(mods.keySet());
	}
	
	public Map<String, String> getMods() {
		return Collections.unmodifiableMap(mods);
	}
	
	public String getModsReadable() {
		return readable;
	}
	
	public static ModData getModData(byte[] data) {
		Map<String, String> mods = new HashMap<>();
		boolean store = false;
		String tempName = null;
		
		for(int i = 2; i < data.length; store = !store) {
			int end = i + data[i] + 1;
			byte[] range = Arrays.copyOfRange(data, i + 1, end);
			String string = new String(range);
			
			if(store) {
				mods.put(tempName, string);
			} else {
				tempName = string;
			}
			
			i = end;
		}
		
		return new ModData(mods);
	}
	
}
