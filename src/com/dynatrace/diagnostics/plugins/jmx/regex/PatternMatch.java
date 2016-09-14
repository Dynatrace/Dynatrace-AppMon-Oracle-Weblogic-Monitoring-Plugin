package com.dynatrace.diagnostics.plugins.jmx.regex;

import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatternMatch {
	private Boolean matches = false;
	
	private static final Logger log = Logger.getLogger(PatternMatch.class.getName());
	
	public Boolean match(String text, String pattern){
	        matches = Pattern.matches(pattern, text);
	        if (log.isLoggable(Level.FINE)) {
	        	log.fine("Text: " + text + " matches pattern: " + pattern + " = " + matches);
	        }
		return matches;
	}
}
