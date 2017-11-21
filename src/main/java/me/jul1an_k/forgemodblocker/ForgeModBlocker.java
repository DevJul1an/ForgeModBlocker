package me.jul1an_k.forgemodblocker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.jul1an_k.forgemodblocker.commands.ForgeModBlocker_Command;
import me.jul1an_k.forgemodblocker.listeners.Join_Listener;
import me.jul1an_k.forgemodblocker.listeners.ModDataReceived_Listener;
import me.jul1an_k.forgemodblocker.listeners.PluginMessage_Listener;

public class ForgeModBlocker extends JavaPlugin {
	
	public void onEnable() {
		saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(new Join_Listener(this), this);
		Bukkit.getPluginManager().registerEvents(new ModDataReceived_Listener(this), this);
		
		this.getCommand("forgemodblocker").setExecutor(new ForgeModBlocker_Command(this));
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "FML|HS");
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "FML|HS", new PluginMessage_Listener());
	}
	
}
