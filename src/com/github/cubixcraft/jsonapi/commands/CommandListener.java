package com.github.cubixcraft.jsonapi.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	private Main plugin = Main.instance;
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String[] command = event.getMessage().substring(1).split(" ");
		String label = command[0].toLowerCase();
		
		if (this.plugin.getPassiveListener() || this.plugin.getListeners().contains(label)) {
			event.setCancelled(true);
			this.plugin.emit(label, Arrays.copyOfRange(command, 1, command.length), player, event, false);
		} else if (this.plugin.getPassiveListener()) {
			this.plugin.emit(label, Arrays.copyOfRange(command, 1, command.length), player, event, true);
		}
	}
}
