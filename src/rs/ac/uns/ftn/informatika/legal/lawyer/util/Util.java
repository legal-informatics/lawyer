package rs.ac.uns.ftn.informatika.legal.lawyer.util;

public class Util {
	public static String escape(String text) {
		if (text != null) {
	    	return text.replaceAll("\\s+", " ").replaceAll("\"", "\\\\\"");
		} else {
			return null;
		}
	}
}
