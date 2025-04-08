import java.io.*;
import static java.lang.System.err;
import java.util.*;
public class RandomizeStringTest {
	public String randomizeLetters(String s) {
		Random r = new Random();
		ArrayList<Character> list = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			list.add(s.charAt(i));
		}
		char[] res = new char[list.size()];
		for (int i = 0; i < res.length; i++) {
			char cur = list.get(r.nextInt(list.size()));
			res[i] = cur;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == cur) {
					list.remove(j);
					break;
				}
			}
		}
		String finale = "";
		for (int i = 0; i < res.length; i++) {
			finale += String.valueOf(res[i]);
		}
		return finale;
	}
	public String onlyMiddleRandom(String s) {
		String inner = s.substring(1, s.length() - 1);
		String randomized = randomizeLetters(inner);
		ArrayList<String> list = new ArrayList<>();
		list.add(String.valueOf(s.charAt(0)));
		for (int i = 0; i < randomized.length(); i++) {
			list.add(String.valueOf(randomized.charAt(i)));
		}
		list.add(String.valueOf(s.charAt(s.length() - 1)));
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res += list.get(i);
		}
		return res;
	}
	public HashMap<Integer, String> mapValues() {
		HashMap<Integer, String> h = new HashMap<>();
		Scanner file = null;
		try {
			file = new Scanner(new File("FILE-PATH-GOES-HERE"));
		}
		catch (IOException e) {
			System.out.println("File not found!");
			err.println("An error occurred while reading the file: " + e.getMessage());
		}
		int i = 1;
		while (file != null && file.hasNextLine()) {
			if (file.hasNext()) {
				h.put(i, file.next());
			}
			else {
				file.nextLine();
			}
			i++;
		}
		return h;
	}
	public static void run() {
		int score = 0;
		Scanner bk = new Scanner(System.in);
		RandomizeStringTest r = new RandomizeStringTest();
		HashMap<Integer, String> h = r.mapValues();
		System.out.print("Enter number of word (1-451): ");
		int wordNum = bk.nextInt();
		while (wordNum != -1) {
			String scramble;
			if (h.get(wordNum).length() >= 5) {
				do {
					scramble = r.onlyMiddleRandom(h.get(wordNum));
				} while (scramble.equals(h.get(wordNum)));
			}
			else {
				String sub = r.randomizeLetters(h.get(wordNum).substring(1));
				scramble = String.valueOf(h.get(wordNum).charAt(0)) + sub;
			}
			bk.nextLine();
			System.out.println("Unscramble the following word: " + scramble);
			String input = bk.nextLine();
			if (input.equals(h.get(wordNum))) {
				System.out.println("Correct!");
				score++;
			}
			else {
				while (!input.equals(h.get(wordNum))) {
					System.out.println("Incorrect. Unscramble the following word: " + scramble + " or enter 1 for the answer");
					input = bk.nextLine();
					if (input.equals("1")) {
						System.out.println("Word is " + h.get(wordNum));
						break;
					}
				}
			}
			System.out.print("Enter new number to continue or -1 to quit: ");
			wordNum = bk.nextInt();
		}
		System.out.println("Program done");
		System.out.println("Your score is: " + score);
	}
	public static void retrieveWord() {
		Scanner bk = new Scanner(System.in);
		RandomizeStringTest r = new RandomizeStringTest();
		HashMap<Integer, String> h = r.mapValues();
		System.out.print("Enter integer to retrieve value (1-451): ");
		System.out.println(h.get(bk.nextInt()));
	}
	public static void retrieveKey() {
		Scanner bk = new Scanner(System.in);
		RandomizeStringTest r = new RandomizeStringTest();
		HashMap<Integer, String> h = r.mapValues();
		System.out.print("Enter word to retrieve key: ");
		String word = bk.nextLine();
		boolean isThere = false;
		for (int i = 1; i <= h.size(); i++) {
			if (h.get(i).equals(word)) {
				System.out.print(i);
				isThere = true;
				break;
			}
		}
		if (isThere == false) {
			System.out.println("The given word does not have a key!");
		}
	}
	public static void main(String[] args) {
		run();
	}
}