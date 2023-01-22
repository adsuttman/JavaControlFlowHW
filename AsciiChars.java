
public class AsciiChars {
	public static void printNumbers(){
		for(char value=48; value < 58; value++) {
			System.out.print(value);
		}
		System.out.print("\n");
	}

	public static void printLowerCase(){
		for(char value='a'; value < 'a' + 26; value++) {
			System.out.print(value);
		}
		System.out.print("\n");
	}

	public static void printUpperCase(){
		for(char value='A'; value < 'A' + 26; value++) {
			System.out.print(value);
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		printNumbers();
		printLowerCase();
		printUpperCase();

	}

}
