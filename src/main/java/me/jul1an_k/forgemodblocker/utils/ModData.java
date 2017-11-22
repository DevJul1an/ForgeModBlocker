package me.jul1an_k.forgemodblocker.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModData {
	
	private final Map<String, String> mods;
	
	private ModData(Map<String, String> mods) {
		this.mods = mods;
	}
	
	public Map<String, String> getMods() {
		return Collections.unmodifiableMap(mods);
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
