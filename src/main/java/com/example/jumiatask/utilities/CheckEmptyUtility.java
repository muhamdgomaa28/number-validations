package com.example.jumiatask.utilities;

public class CheckEmptyUtility {
	public static boolean isNotEmpty(final String s) {
		return (s != null && s.trim().length() != 0);
	}
}
