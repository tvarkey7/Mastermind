import java.io.*;
import java.lang.*;
import java.util.*;

public class Mastermind {
	static String[][] savedArr = null;
	static String[] clrs =  {"red", 
			"orange", 
			"yellow", 
			"green", 
			"blue", 
			"pink", 
			"purple", 
			"cyan"};
	public static void main(String[] args) {
		System.out.println("Welcome to Mastermind");
		System.out.println("Would you like to read the rules or play the game? ");
		System.out.println("Type 'rules' for rules; type 'play' for the game");
		//get either 'start' or 'load' from user input
		//String[][] savedGame = new String[21][];
		Scanner in2 = new Scanner(System.in);
		String gameMode = in2.nextLine();
		if ((gameMode.toLowerCase()).compareTo("rules")==0) {
			rules();
		}
		if ((gameMode.toLowerCase()).compareTo("play")==0) {
			play();
		}
	}
	
	public static void rules() {
		//set rules screen here
		System.out.println("There are two players: \nA Code-Setter and a Code-Breaker ");
		System.out.println("The Code-Maker sets the code from the list of colours");
		System.out.println("An Example would be 'red blue green yellow'");
		System.out.println("The Code-Breaker has to guess the code in 10 guesses.");
		System.out.println("If a colour is correct and in the right position, a black peg is shown");
		System.out.println("If a colour is correct but in the wrong position, a white peg is shown");
		System.out.println("If you want to save your game, type 'save'");
		System.out.println("Type 'play' to start");
		//get either 'start' or 'load' from user input
		Scanner in5 = new Scanner(System.in);
		String gameMode = in5.nextLine();
		if ((gameMode.toLowerCase()).compareTo("play")==0) {
			play();
		}
	}

	public static void play() {
		System.out.println("Start a new game: type 'start'. ");
		System.out.println("Load a previous game: type 'load'");
		Scanner loadOrPlay = new Scanner(System.in);
		String loadOrPlay1 = loadOrPlay.nextLine();
		if ((loadOrPlay1.toLowerCase()).compareTo("load")==0) {
			try {
				String[][] gameArray = load();
				printArray(gameArray);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return;
		}
		System.out.println("How many colours would you like?");
		System.out.println("Enter a number between 3 and 8");
		
		@SuppressWarnings("resource")
		Scanner in1 = new Scanner(System.in);
		int numOfColours;
		//take in input here and store into numOfColours variable.7
		//defensive programming concepts too, enter correct inputs
		do {
			numOfColours = in1.nextInt();
			if (numOfColours < 3 || numOfColours >= 9) {
				System.out.println("Enter a number between 3 and 8");
			}
		} while (numOfColours < 3 || numOfColours >= 9);
		//final int maxGuesses = 10;
		//the colours that are available to enter. 
		String[] allColours =  {"red", 
								"orange", 
								"yellow", 
								"green", 
								"blue", 
								"pink", 
								"purple", 
								"cyan"};
		
		System.out.println("How many pegs would you like hidden?");
		if (numOfColours==3) {
			System.out.println("You can have all 3 pegs hidden, or none hidden");
			System.out.println("Enter '0' or '3'");
		}
		else {
			System.out.println("Enter a number between 3 and " + String.valueOf(numOfColours));
		}
		
		Scanner hiddenPegs = new Scanner(System.in);
		int pegsHidden = hiddenPegs.nextInt();
		
		System.out.println("\n");
		System.out.println("Which game mode are you playing? "
				+ "Enter a number between 1 and 3");
		System.out.println("1: Human vs Human");
		System.out.println("2: Human vs Computer");
		System.out.println("3: Computer vs Computer");
		
		Scanner in3 = new Scanner(System.in);
		String gameChoice = in3.nextLine();
		//in GM (Game Mode) 1 and 2, human sets the code.
		if (((gameChoice.toLowerCase()).compareTo("1")==0)||(gameChoice.toLowerCase()).compareTo("2")==0) {
			List<String> gameColours = new ArrayList<String>();
			for (int i=0;i<numOfColours;i++) {
				gameColours.add(allColours[i]);
			}
			String[] finalGameColours = new String[gameColours.size()];
			gameColours.toArray(finalGameColours);
			
			String[][] gameTracker = new String[21][numOfColours];
			//GM1 is selected - human vs human:
			if ((gameChoice.toLowerCase()).compareTo("1")==0 ){
				humanVsHuman(gameTracker, numOfColours, pegsHidden);
			}
			else if((gameChoice.toLowerCase()).compareTo("2")==0) {
				humanVsComp(gameTracker, numOfColours, pegsHidden);
			}
		}
		//computer vs computer
		else {
			System.out.println("Computer vs Computer Activated!");
		}

	}
	
	
	public static void humanVsHuman(String[][] gameGuesses, int colours, int pegs) {
		System.out.println("Player 1: Please enter the combination");
		//check if its the right length
		Scanner player1Input = new Scanner(System.in);
		String code = player1Input.nextLine();
		System.out.println("The code is " + code);
		//need to parse and store guesses into array.
		String split[] = code.split(" ");
		for (int i=0;i<colours;i++) {
			gameGuesses[0][i] = split[i];
		}
		printArray(gameGuesses);
		//For saving purposes
		System.out.println("Player 2, You have a maximum of 10 guesses.");
		//System.out.println("Player 2, Please enter your guess.");
		Scanner player2Input = new Scanner(System.in);
		//String guess = player2Input.nextLine();
		int guessCount = 1;
		int ind=1;
		int index=(2*ind)+1;
		
		///
		do {
			System.out.println("Player 2, Please enter your guess.");
			String guess = player2Input.nextLine();
			if ((guess.toLowerCase()).compareTo("save")==0) {
				//if "save" was typed in
				save(gameGuesses);
			}
			else {
			/*//if the input matches the code
			//currently it takes the whole string in,
			//now we check the array instead
			//if ((guess.toLowerCase()).compareTo(code.toLowerCase())==0) {
				//break;
			}*/
			String[] guessArr = guess.split(" ");
			String[] feedback = getFeedback(gameGuesses[0], guess, pegs, guessCount);
			for (int a=0;a<guessArr.length;a++) {
				gameGuesses[ind][a] = guessArr[a];
				gameGuesses[ind+1][a] = feedback[a];
			}
			
			ind++;
			ind++;
			guessCount++;
			printArray(gameGuesses);
		}
		}

		while (guessCount < 11);
		
		
		if (guessCount >= 10) {
			//game is over now
			System.out.println("Game is over. Player 1 wins!");
		}
		//Game is over in under 10 guesses
		if (guessCount < 10) {
			System.out.print("Game Over! Player 2 Wins");
		}
		
		gameOver(gameGuesses[0]);
		
		
	}
	
	
	public static void humanVsComp(String[][] gameGuesses, int colours, int pegs) {
		System.out.println("Human Vs Computer Activated");
		System.out.println("Please enter the secret combination: ");
		//check if its the right length
		Scanner player1Input = new Scanner(System.in);
		String code = player1Input.nextLine();
		System.out.println("The code is " + code);
		//need to parse and store guesses into array.
		String split[] = code.split(" ");
		for (int i=0;i<colours;i++) {
			gameGuesses[0][i] = split[i];
		}
		//if no pegs are hidden
		//int count = 0;
		String[] blacks = new String[colours];
		String[] whites = new String[colours];
		if (pegs==0) {
			String[] blackPegs = new String[8];
			String guess1 = "red orange yellow green";
			String split1[] = guess1.split(" ");
			String guess2 = "blue pink purple cyan";
			String split2[] = guess2.split(" ");
			int guesses = 0;
			String[] feedback1 = getFeedback(gameGuesses[0],guess1,pegs,guesses);
			for (int i=0;i<colours;i++) {
				gameGuesses[1][i] = split1[i];
				gameGuesses[2][i] = feedback1[i];
			}
			
			String[] feedback2 = getFeedback(gameGuesses[0],guess1,pegs,guesses);
			for (int i=0;i<colours;i++) {
				gameGuesses[3][i] = split2[i];
				gameGuesses[4][i] = feedback2[i];
			}
		}
		
		printArray(gameGuesses);
		//gameGuesses[0] == answer
		//computer must guess now. 
		int guesses2 = 2;
		while (guesses2 <10) {
			String[] guess = getGuess(blacks, whites, colours);
			String guessString = "";
			for (int i=0;i<guess.length;i++) {
				guessString += (guess[i] + " ");
			}
			for (int j=0;j<guess.length;j++) {
				gameGuesses[(guesses2*2) +1][j] = guess[j];
			}
			String feedbackGiven[] = getFeedback(gameGuesses[0],guessString,pegs,guesses2);
			for (int k=0;k<colours;k++) {
				gameGuesses[guesses2*3][k] = feedbackGiven[k];
			}
			guesses2++;
		}
	}
	
	
	//this function returns the pegs for each guess made
	//Takes in the code, the guess and number of pegs hidden
	//Returns a string consisting of black and white
	//or nothing.
	public static String[] getFeedback(String[] code, String guess, int hidden, int counter) {
		
		String printableArray = Arrays.toString(code);
		int len = code.length;

		System.out.println(printableArray);
		System.out.println(guess);
		String[] guessSplit = guess.split(" ");

		
		if (len==hidden) {
			// all the pegs are being hidden
			//String feedback1 = "";
			String feedback[] = new String[len];
			for (int i=0;i<len;i++) {
				feedback[i] = "hidden";
			}
			return feedback;
		}
		int black = 0;
		int white = 0;
		//String[] feedbackArray = new String[code.length];
		String[] codeCopy = code.clone();
		String[] codeCopy2 = code.clone();
		//String[] codeCopy2 = null;
		//compare the guess to the code 
		//check for Blacks first.
		for (int i=0;i<len;i++) {
			//if a colour is in the right position
			//(check if the guess is in line with the code
			if ((codeCopy[i] != null)&&((guessSplit[i].toLowerCase()).compareTo(codeCopy[i].toLowerCase())==0)) {
				black++;
				//make them null so they are not compared again
				guessSplit[i] = null;
				codeCopy[i] = null;
			}
			else {
				//check through rest of array and see if there are any matches
				for (int k=0;k<len;k++) {
					if ((codeCopy[k] != null)&&(guessSplit[i] != null)&&((guessSplit[i].toLowerCase()).compareTo(codeCopy[k].toLowerCase())==0)) {
						white++;
						guessSplit[i] = null;
						codeCopy[k] = null;
					}
				}
			}
		}
		
		System.out.println("current array of copy2");
		for (int i=0;i<len;i++) {
			System.out.print(codeCopy2[i]+ " . ");
		}
		System.out.println("\ntotal blacks: " + String.valueOf(black));
		System.out.println("\ntotal whites: " + String.valueOf(white));
		if (black == len) {
			System.out.println("Code breaker wins!");
			gameOver(code);
		}
		int countForLoop = 0;
		String[] feedback1 = new String[len];
		//if there are no white or black pegs:
		if ((black==0) && (white==0)) {
			for (int i=0;i<len;i++) {
				feedback1[i] = " ";
			}
		}
		else if ((black !=0) && (white==0)) {
			for (int i=0;i<black;i++) {
				feedback1[i] = "black";
			}
			for (int j=0;j<len;j++) {
				if ((feedback1[j] == null)||(feedback1[j].toLowerCase()).compareTo("black")!=0) {
					feedback1[j] = " ";
				}
			}	
		}
		else if ((white != 0) && (black==0)) {
			for (int i=0;i<white;i++) {
				feedback1[i] = "white";
			}
			for (int j=0;j<len;j++) {
				if ((feedback1[j] == null) ||(feedback1[j].toLowerCase()).compareTo("white")!=0) {
					feedback1[j] = " ";
				}
			}
		}
		else if ((white !=0) && (black!=0)) {
			for (int i=0;i<black;i++) {
				feedback1[i] = "black";
				countForLoop = i;
			}
			for (int k=0;k<white;k++) {
				feedback1[countForLoop+1] = "white";
				countForLoop++;
			}
			for (int j=0;j<len;j++) {
				if ((feedback1[j] == null)||((feedback1[j].toLowerCase()).compareTo("white")!=0)&&((feedback1[j].toLowerCase()).compareTo("black")!=0)) {
					feedback1[j] = " ";
				}
			}	
		}
		//if there are more than 0 pegs being hidden
		if (hidden!=0) {
			for (int i=0;i<hidden;i++) {
				feedback1[i] = " ";
			}
		}
		return feedback1;
	}
	
	public static String[] getGuess(String[] blacks, String[] whites, int numOfCol) {
		String[] calculatedGuess = new String[numOfCol];
		//if there is a colour that is black, include in guess
		if (blacks[0] != null) { 
			for (int i=0;i<blacks.length;i++) {
				calculatedGuess[i] = blacks[i];
			}
		for (int j=0;j<calculatedGuess.length;j++) {
			if (calculatedGuess[j] == null) {
				Random gen = new Random();
				int randIntInd = gen.nextInt(clrs.length);
				calculatedGuess[j] = clrs[randIntInd];
			}
		}
		
		}
		System.out.println("Hellooooo");
		for (int k=0;k<numOfCol;k++) {
			System.out.println(calculatedGuess[k]);
		}
		return calculatedGuess;
	}
	
	public static void save(String[][] arr) {
		System.out.println("Saving Process started...");
		System.out.println("The array to be saved is: ");
		printArray(arr);
		try {
			PrintWriter writer = new PrintWriter(new File ("SavedGame.txt"));
			for (int i=0;i<arr.length;i++) {
				for (int j=0;j<arr[i].length;j++) {
					if ((arr[i][j] == null)) {
						arr[i][j] = "x";
						writer.write("");
					}
					writer.write(arr[i][j]+" ");
				}
				writer.println();
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Error Raised. ");
			e.printStackTrace();
		}
		savedArr = arr;
		System.out.println("SAVED ARR IS AS FOLLOWS: ");
		printArray(savedArr);
		play();
	}
	
	public static String[][] load() throws FileNotFoundException {
		System.out.println("Loading Previous Game...");
		//open file
		String[][] loadedArray = null ;
		try {
			loadedArray = savedArr;
		} catch (Exception e2) {
			System.out.println("No game found! Searching Directory for saves!");
		}
		
		//read array into array in program
		//int length_one = savedArr.length;
		try {
			//user scanner class to read in 2d array from file
			
		} catch (Exception e) {
			System.out.println("File Error");
			
		}
		
		//printArray(savedArr);
		//	Scanner load = new Scanner("SavedGame.txt");
		return loadedArray;
	}
	
	public static void gameOver(String[] code) {
		//System.out.println("\nGame Over!");
		System.out.println("The code was: ");
		for (int i=0;i<code.length;i++) {
			System.out.print(code[i]+ " ");
		}
		System.out.println("\n");
		System.out.println("Would you like to play again?");
		System.out.println("Or would you like to quit the program?");
		System.out.println("Type 'play' or 'quit'");
		Scanner finalChoice = new Scanner(System.in);
		String finalChoice1 = finalChoice.nextLine();
		if ((finalChoice1.toLowerCase()).compareTo("play")==0) {
			play();
		}
		else {
			//quit the program
			System.exit(0);
		}
		
	}
	
	private static void printArray(String[][] arr) {
		System.out.println("ARRAY SO FAR");
		for (String[] row: arr) {
			System.out.println(Arrays.toString(row));
		}
	}
}
