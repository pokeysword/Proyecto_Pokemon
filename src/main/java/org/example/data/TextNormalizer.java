package org.example.data;

public final class TextNormalizer {
	private TextNormalizer() {
	}

	public static String normalize(String input) {
		if (input == null) {
			return "";
		}
		return input.trim();
	}
}
