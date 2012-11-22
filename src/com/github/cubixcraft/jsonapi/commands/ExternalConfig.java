package com.github.cubixcraft.jsonapi.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ExternalConfig extends YamlConfiguration {
	private File file;

	public ExternalConfig(File file) throws IOException {
		this.file = file;
		this.save();
	}
	public ExternalConfig(String file) throws IOException {
		this(new File(file));
	}
	public ExternalConfig(String parent, String child) throws IOException {
		this(new File(parent, child));
	}
	public ExternalConfig(File parent, String child) throws IOException {
		this(new File(parent, child));
	}

	public void save(boolean throwError) throws IOException {
		this.save(file);
	}
	public void save() {
		try {
			this.save(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reload(boolean throwError) throws FileNotFoundException, IOException, InvalidConfigurationException {
		this.load(file);
	}
	public void reload() {
		try {
			this.reload(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ExternalConfig loadConfiguration(File file) {
		try {
			return new ExternalConfig(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ExternalConfig loadConfiguration(InputStream stream) throws IllegalArgumentException {
		throw new IllegalArgumentException("ExternalConfig only takes Files");
	}
}