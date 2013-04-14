package com.github.cubixcraft.jsonapi.commands;

import com.alecgorge.minecraft.jsonapi.dynamic.API_Method;
import com.alecgorge.minecraft.jsonapi.dynamic.JSONAPIMethodProvider;

public class APIMethods implements JSONAPIMethodProvider {
	private Main plugin = Main.instance;
	
	@API_Method(
		namespace="commands",
		argumentDescriptions={"The command the API should start listening on"},
		returnDescription="Returns true if the binding was successful, false if not."
	)
	public boolean addListener(String command) {
		this.plugin.addListener(command);
		return true;
	}
	
	@API_Method(
		namespace="commands",
		returnDescription="Returns true after it has bound the generic command listener."
	)
	public boolean addPassiveGenericListener() {
		this.plugin.addPassiveListener();
		return true;
	}
	
	@API_Method(
		namespace="commands",
		argumentDescriptions={"The command the API should stop listening on"},
		returnDescription="Returns true if no more events of this command will be sent, false if not."
	)
	public boolean removeListener(String command) {
		this.plugin.removeListener(command);
		return true;
	}
	
	@API_Method(
		namespace="commands",
		returnDescription="Returns true after it has unbound the generic command listener."
	)
	public boolean removePassiveGenericListener() {
		this.plugin.removePassiveListener();
		return true;
	}
	
	@API_Method(
		namespace="commands",
		argumentDescriptions={"An array of commands the API should sart listening on"},
		returnDescription="Returns true if the binding was successful, false if not."
	)
	public boolean addListeners(String[] commands) {
		for (String command : commands) this.plugin.addListener(command);
		return true;
	}
	
	@API_Method(
		namespace="commands",
		argumentDescriptions={"An array of commands the API should stop listening on"},
		returnDescription="Returns true if no more events of this commands will be sent, false if not."
	)
	public boolean removeListeners(String[] commands) {
		for (String command : commands) this.plugin.removeListener(command);
		return true;
	}
	
	@API_Method(
		namespace="commands",
		returnDescription="Returns true if no more events will be sent, false if not."
	)
	public boolean removeAllListeners() {
		this.plugin.removeAllListeners();
		return true;
	}
	 
	@API_Method(
		namespace="commands",
		returnDescription="Returns an array of strings of all commands the API is listening on."
	)
	public String[] allListeners() {
		return this.plugin.getListeners().toArray(new String[0]);
	}
}
