package de.hexagonsoftware.engine.util;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;

public class LanguageManager {
	private JsonObject languages;
	private JsonObject currentLang;
	private String defaultLang;
	
	public LanguageManager(String path, String defaultLang) {
		this.defaultLang = defaultLang;
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(path)));
		this.languages = gson.fromJson(reader, JsonObject.class);
		loadLanguage(languages.get(defaultLang).getAsString());
	}

	public void loadLanguage(String lang) {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(lang)));
		this.currentLang = gson.fromJson(reader, JsonObject.class);
	}
	
	public String getString(String identifier) {
		return currentLang.get(identifier).getAsString();
	}
}
