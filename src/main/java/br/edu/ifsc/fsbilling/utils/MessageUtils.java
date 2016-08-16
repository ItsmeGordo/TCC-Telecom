package br.edu.ifsc.fsbilling.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public final class MessageUtils {

	private static Locale locale = new Locale("pt", "br");

	private MessageUtils() {
	}

	public static String getMessage(final String key) {
		try {
			final ResourceBundle rb = ResourceBundle.getBundle("messages",
					locale);
			return rb.getString(key);
		} catch (Exception e) {
			return key;
		}
	}

	public static String getMessage(String key, final Object... params) {
		try {
			ResourceBundle bundle = ResourceBundle
					.getBundle("messages", locale);
			if (bundle.containsKey(key)) {
				key = bundle.getString(key);
			}
			return MessageFormat.format(key, params);
		} catch (Exception e) {
			return key;
		}
	}

}
