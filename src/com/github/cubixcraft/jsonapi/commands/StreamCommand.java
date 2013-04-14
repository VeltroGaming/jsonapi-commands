package com.github.cubixcraft.jsonapi.commands;

import org.bukkit.entity.Player;
import org.json.simpleForBukkit.JSONObject;

import com.alecgorge.minecraft.jsonapi.api.JSONAPIStreamMessage;

public class StreamCommand extends JSONAPIStreamMessage {
	private String command;
	private String[] args;
	private Player player;
	private Boolean passive;
	
	public StreamCommand(String command, String[] args, Player player, Boolean passive) {
		this.command = command;
		this.args = args;
		this.player = player;
		this.passive = passive;
	}
	
	@Override
	public String streamName() {
		return "commands";
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject o = new JSONObject();
		o.put("time", Long.valueOf(getTime()));
		o.put("command", this.command);
		o.put("args", this.args);
		o.put("player", this.player);
		o.put("passive", this.passive);
		return o;
	}
}
