package com.github.cubixcraft.jsonapi.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.alecgorge.minecraft.jsonapi.JSONAPI;
import com.alecgorge.minecraft.jsonapi.api.JSONAPIStream;

public class Main extends JavaPlugin {
	public static Main instance;
	
	private JSONAPI jsonapi;
	private JSONAPIStream stream;
	
	public static Logger log = Bukkit.getLogger();
	private PluginManager pm;
	private ExternalConfig config;
	private Set<String> listeners = new HashSet<String>();
	
	@Override
	public void onEnable() {
		this.instance = this;
		this.pm = getServer().getPluginManager();
		
		// Check for JSONAPI
		Plugin checkplugin = this.pm.getPlugin("JSONAPI");
		if (checkplugin == null) {
			this.log.severe("[JSONAPI-Commands] Can't load JSONAPI. Get it here: http://dev.bukkit.org/server-mods/jsonapi/");
			this.pm.disablePlugin(this);
			return;
		}
		
		// Initialize all the stuff
		this.jsonapi = (JSONAPI) checkplugin;
		this.log.info("[JSONAPI-Commands] Hooked into JSONAPI");
		
		this.pm.registerEvents(new CommandListener(), this);
		this.log.info("[JSONAPI-Commands] Listening on all /commands");
		
		this.log.info("[JSONAPI-Commands] Registering new methods");
		this.jsonapi.getCaller().registerMethods(new APIMethods());
		
		this.log.info("[JSONAPI-Commands] Registering commands stream");
		this.stream = new Stream("commands");
		this.jsonapi.getStreamManager().registerStream("commands", this.stream);
		
		// Load config
		this.log.info("[JSONAPI-Commands] Loading configuration");
		try {
			this.config = new ExternalConfig(this.jsonapi.getDataFolder(), "commands.yml");
		} catch (IOException e) {
			this.log.severe("[JSONAPI-Commands] Could not load or create command.yml");
			e.printStackTrace();
			this.pm.disablePlugin(this);
			return;
		}
		this.config.addDefault("commands", new String[] {"someCommand"});
		this.config.options().copyDefaults(true);
		this.config.save();
		this.config.reload();
		
		// Register commands from config
		this.log.info("[JSONAPI-Commands] Registering commands");
		for (String command : this.config.getStringList("commands")) this.addListener(command);
		
		this.log.info("[JSONAPI-Commands] Ready for take-off");
	}
	@Override
	public void onDisable() {}
	
	public void addListener(String command) {
		this.log.info("[JSONAPI-Commands] Add command: " + command);
		this.listeners.add(command.toLowerCase());
	}
	public void removeListener(String command) {
		this.log.info("[JSONAPI-Commands] Remove command: " + command);
		this.listeners.remove(command.toLowerCase());
	}
	public void removeAllListeners() {
		this.log.info("[JSONAPI-Commands] Remove all commands");
		this.listeners.clear();
	}
	public Set<String> getListeners() {
		return this.listeners;
	}
	
	public void emit(String command, String[] args, Player player, PlayerCommandPreprocessEvent event) {
		this.log.info("[JSONAPI-Commands] " + player.getName() + ": " + event.getMessage());
		this.stream.addMessage(new StreamCommand(command, args, player));
	}
}
