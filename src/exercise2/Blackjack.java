package exercise2;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	
	//Create Attribute
	private int[] cardYou;
	private int[] cardComputer;
	private int numYourCard;
	private int sumYou;
	private int sumComputer;
	private String winner;
	
	//Method : card
	private int[] drawCard(int n) {
		//Prepare array to stored card
		int[] card = new int[n];
		
		//Add random card to array
		for (int i=0; i<n; i++) {
			card[i] = new Random().nextInt(11)+1;
		}
		
		//Return card after random it
		return card;
	}
	
	//Class : constructor
	public Blackjack() {
		
		//Initialize class Attribute
		cardYou = drawCard(5);
		cardComputer = drawCard(2);
		numYourCard = 2;
		sumYou = cardYou[0]+cardYou[1];
		sumComputer = cardComputer[0]+cardComputer[1];
		winner = null;
		
		//Check is card both 11
		if (sumYou == 22) {
			cardYou[1] = 1;
			sumYou = 12;
		}
		if (sumComputer == 22) {
			cardComputer[1] = 1;
			sumComputer = 12;
		}
	}
	
	//Method : you
	public void showYouCard() {
		String card = "You : ";
		
		//Check number card to Show
		for (int i=0; i<5; i++) {
			
			if (i >= numYourCard) {
				card += "0 ";
			}
			else
			{
				card += cardYou[i] + " ";
			}
		}
		//Output
		System.out.println(card);
	}
	
	//Method : computer
	public void showComputerCard() {
		System.out.print("Computer : ");
		System.out.print(cardComputer[0] + " ");
		System.out.println(cardComputer[1]);
	}
	
	//Method : card, you
	public void addMoreCard() {
		//Check card is 5?
		if (numYourCard < 5) {
			
			//If draw is 11 and more than 21
			if (cardYou[numYourCard] == 11 && (sumYou + cardYou[numYourCard]) > 21) {
				cardYou[numYourCard] = 1;
			}
			
			//update 
			sumYou += cardYou[numYourCard];
			numYourCard ++;
		}
	}
	
	//Method : card
	public void showSumCard() {
		System.out.println("Sum of Your card = " + sumYou);
		System.out.println("Sum of Computer card = " + sumComputer);
	}
	
	//Method : game
	public boolean isEnd() {
		//First check
		if (sumYou >= 21 || numYourCard == 5) {
			return true;
		}
		
		//Ask for more card
		System.out.print("Want another card ? (y/n)...");
		@SuppressWarnings("resource")
		String check = new Scanner(System.in).nextLine();
		
		//Check
		if (check.equals("y")) {
			addMoreCard();
			
			if (sumYou >= 21 || numYourCard == 5) {
				return true;
			}
			
			return false;
		}
		
		return true;
	}
	
	//Method : game
	public void checkWinner() {
		if (sumYou > 21) {
			winner = "Computer";
		}
		else if (sumYou > sumComputer) {
			winner = "You";
		}
		else {
			winner = "Computer";
		}
	}
	
	//Method : game
	public String getWinner() {
		return winner;
	}
	
	//Method : main
	public static void main(String[] args) {
		//start game
		Blackjack bj = new Blackjack();
		
		do {
			//Main output
			bj.showYouCard();
			System.out.println("Computer : ? ?\n");
			
		} while (!(bj.isEnd()));
		
		//Print out game result
		bj.showYouCard();
		bj.showComputerCard();
		System.out.println();
		bj.showSumCard();
		bj.checkWinner();
		System.out.println("The winner is " + bj.getWinner());
	}
	
}
