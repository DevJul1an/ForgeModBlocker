package me.jul1an_k.forgemodblocker.commands;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jul1an_k.forgemodblocker.ForgeModBlocker;
import me.jul1an_k.forgemodblocker.api.ForgeModBlockerAPI;

public class ForgeModBlocker_Command implements CommandExecutor {
	
	private ForgeModBlocker instance;
	
	public ForgeModBlocker_Command(ForgeModBlocker instance) {
		this.instance = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!cs.hasPermission("forgemodblocker.forgemodblocker")) {
			// TODO: No Permission message
			return true;
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				instance.reloadConfig();
				
				cs.sendMessage("§aReloaded config successfully.");
			} else {
				sendHelp(cs);
			}
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("list")) {
				Player target = Bukkit.getPlayer(args[1]);
				
				if(target == null) {
					cs.sendMessage("§cTarget Player isn't online.");
					return true;
				}
				
				Map<String, String> mods = ForgeModBlockerAPI.getMods(target);
				
				if(mods == null) {
					cs.sendMessage("§cTarget Player isn't using forge.");
					return true;
				}
				
				String string = "";
				
				for(String key : mods.keySet()) {
					string += "\n§c" + key + " §8- §c" + mods.get(key);
				}
				
				cs.sendMessage("§c" + target.getName() + " §7is using the following modifications:");
				cs.sendMessage(string);
			} else {
				sendHelp(cs);
			}
		} else {
			sendHelp(cs);
		}
		
		return true;
	}
	
	private void sendHelp(CommandSender cs) {
		cs.sendMessage(" ");
		cs.sendMessage("§7Currently there are only two commands:");
		cs.sendMessage("");
		cs.sendMessage("§8- §c/fmb reload");
		cs.sendMessage("§8- §c/fmb list <Player>");
		cs.sendMessage(" ");
	}
	
}
