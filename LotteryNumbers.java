import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LotteryNumbers {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Please enter your name: ");
		String name = input.nextLine();
		System.out.println("Hello " + name);
		
		System.out.println("Do you wish to continue with the interactive portion? y/n");
		if (input.nextLine().toLowerCase().charAt(0) == 'y') {
			while(true) {
				//collect info
				String pet = prompt("What is the name of your favorite pet?");
				int petAge = promptInt("What is the age of your favorite pet?");
				int lucky = promptInt("What is your lucky number?");
				int model = promptInt("What is the two digit model year of your car?", 0, 99);
				String act = prompt("What is the first name of your favorite actor or actress?");
				int rand = promptInt("Enter a random number between 1 and 50:", 1, 50);
				
				//create constants for the maximum we want our randomly generated numbers to be
				int RAND_MAX = 65;
				int BALL_MAX = 75;
				
				//generates a random integer between 10 and 20 (or it should, I think, probably)
				int rand1 = (int)Math.ceil((Math.random() + 1) * 10);
				int rand2 = (int)Math.ceil((Math.random() + 1) * 10);
				
				//calculate the magic ball number
				int ball = (lucky * rand1) % BALL_MAX;
				
				// create an array to hold the 5 random numbers
//				int[] randomNums = new int[5];
//				randomNums[0] = pet.charAt(2) % RAND_MAX;
//				randomNums[1] = Math.abs(rand - rand2) % RAND_MAX;
//				randomNums[2] = act.charAt(act.length()-1) % RAND_MAX;
//				randomNums[3] = (petAge + model) % RAND_MAX;
//				randomNums[4] = (model + lucky) % RAND_MAX;
				
				//or we can use a sorted set to remove duplicates and sort
				Set<Integer> randSet = new TreeSet<>();
				try {
					randSet.add(pet.charAt(2) % RAND_MAX);
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("Really? Your favorite pet's name is less than three characters? Fine, I'll use the first one then");
					randSet.add(pet.charAt(0) % RAND_MAX);
				}
				randSet.add(Math.abs(rand - rand2) % RAND_MAX);
				randSet.add(act.charAt(act.length()-1) % RAND_MAX);
				randSet.add((petAge + model) % RAND_MAX);
				randSet.add((model + lucky) % RAND_MAX);
				
				//print out numbers
//				System.out.printf("Lottery numbers: %d, %d, %d, %d, %d  Magic ball: %d\n", randomNums[0], randomNums[1], randomNums[2], randomNums[3], randomNums[4], ball);
				displayResults(randSet, ball);
				
				//ask user if they want to do it again, otherwise quit program
				System.out.println("Would you like to do it again? y/n");
				if (input.nextLine().toLowerCase().charAt(0) == 'y') {
					System.out.println("Let's do it again!");
				} else {
					System.out.println("Thanks for playing!");
					System.exit(0);
				}
			}
		} else {
			System.out.println("Ok, please return later to complete the survey");
			System.exit(0);
		}
	}
	
	static String prompt(String question) {
		while (true) {
			System.out.println(question);
			String result = input.nextLine();
			if (result.isEmpty()) {
				System.out.println("Please enter something!");
			} else {
				return result;
			}
		}
	}
	
	static int promptInt(String question) {
		while(true) {
			System.out.println(question);
			String result = input.nextLine();
			try {
				if (result.matches("\\d+")) {
					return Integer.parseInt(result);
				} else {
					System.out.println("Please enter a postive integer!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please don't enter a number that's too big to be stored in an int!");
			}
			
		}		
	}
	
	static int promptInt(String question, int min, int max) {
		while(true) {
			int result = promptInt(question);
			if (result < min || result > max) {
				System.out.printf("Please enter a number between %d and %d!\n", min, max);
			} else {
				return result;
			}
			
		}		
	}
	
	static void displayResults(Set<Integer> lotteryNums, int magicBall) {
		StringBuilder results = new StringBuilder(100);
		results.append("Lottery numbers: ");
		for (Integer num : lotteryNums) {
			results.append(num + ", ");
		}
		//remove last ,
		results.deleteCharAt(results.lastIndexOf(","));
		results.append("  Magic ball: " + magicBall);
		System.out.println(results);
		
	}
}
