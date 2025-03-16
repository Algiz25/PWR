import java.util.ArrayList;
import java.util.Scanner;

public class Document {
	public static void loadDocument(String name, Scanner scan) {
		while (true) {
			String text = scan.nextLine();
			if (text.equals("eod")) {
				break;
			}
			String[] words = text.split(" ");
			ArrayList<String> links = new ArrayList<>();
			for (String word : words) {
				if (word.length() > 5) {
					word = word.toLowerCase();
					String substring = word.substring(0, 5);
					if (substring.equals("link=")) {
						if (correctLink(word)) {
							links.add(word.substring(5));
						}
					}
				}
			}

			for (String link : links) {
				System.out.println(link);
			}
		}
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
		char ch = link.charAt(5);
		if ((ch >= 'a' && ch <= 'z')) {
			for (int i = 6; i < link.length(); i++) {
				ch = link.charAt(i);
				if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '_'){
					continue;
				}
				else{
					return false;
				}
			}
		}
		else {
			return false;
		}

		return true;
	}

}
