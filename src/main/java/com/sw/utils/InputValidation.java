package com.sw.utils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
	/**
	 * Validate that string is a number
	 * @param id the id to be validate
	 * 
	 * @return true if valid an present, empty otherwise
	 */
	public static Optional<Boolean> validateNumber(String id) {
		String regexp = "^\\d+$";
		Pattern p = Pattern.compile(regexp);
		Matcher matcher = p.matcher(id);
		boolean find = matcher.find();
		return find ?  Optional.of(find) : Optional.empty();
	}
}
