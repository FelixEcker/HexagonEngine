package de.hexagonsoftware.engine.util;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;

/**
 * The LanguageManager is used for easily translating
 * games using JSON files, this is achieved by, instead
 * of defining the strings for e.g. GUIs on spot, you
 * request a String from the LanguageManager using its
 * identifier, which then returns a string (if found) from
 * the currently loaded language JSON.
 * 
 * @author Felix Eckert
 * */
public class LanguageManager {
	private JsonObject languages;
	private JsonObject currentLang;
	
	/**
	 * @param path The path to the Language List file (JSON)
	 * @param defaultLang The language to be loaded by default
	 * */
	public LanguageManager(String path, String defaultLang) {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(path)));
		this.languages = gson.fromJson(reader, JsonObject.class);
		loadLanguage(languages.get(defaultLang).getAsString());
	}

	/**
	 * Loads a different language from the language list file
	 * 
	 * @param lang The name of the language
	 * */
	public void loadLanguage(String lang) {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(lang)));
		this.currentLang = gson.fromJson(reader, JsonObject.class);
	}
	
	/**
	 * @param identifier The identifier the requested string is stored with in the language files
	 * @return The requested string, if not found, it will return the identifier
	 * */
	public String getString(String identifier) {
		return currentLang.has(identifier) ? currentLang.get(identifier).getAsString() : identifier;
	}
}
