package de.hexagonsoftware.engine.util;

/**
 * This exception is thrown when something tries to acces a resource,
 * which wasnt loaded by the engines Resource Manager
 * 
 * {@link java.lang.NullPointerException}
 * 
 * @author Felix Eckert
 * */
public class HEMissingResourceException extends NullPointerException {
	private static final long serialVersionUID = -6575445152293550053L;
}
