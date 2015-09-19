package util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageUtil {
	private static ResourceBundle bundle =
	        ResourceBundle.getBundle(
	            "messageResources");
	public static String getMessage(String key){
		return getMessage(key, new Object[0]);
	}
	public static String getMessage(String key, Object[] args){
		String message =  bundle.getString(key);
		return MessageFormat.format(message, args);
	}
}
