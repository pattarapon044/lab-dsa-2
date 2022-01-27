package exercise2;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Blackjack{
	
	private static Scanner input = new Scanner(System.in);
	
	//Attribute
    private int[] playerCard;
    private int[] computerCard;
    
    private int playerSum;
    private int computerSum;
    
    private int numPlayerCard;
    
    private String winner;
    private boolean end;
	
	//Private : draw
	private int drawCard() {
		Random r = new Random();
		return r.nextInt(11)+1;
	}
	
    //Constructor
    public Blackjack(){
    	//Setup : data
		playerSum = 0;
        computerSum = 0;
        end = false;
        
        //Setup : computer
        computerCard = new int[] {drawCard(), drawCard()};
        computerSum = getComputerSum();
        if (computerSum == 11+11) {
        	computerCard[1] = 1;
        }
        computerSum = getComputerSum();
        
        //Setup : player
        playerCard = new int[] {drawCard(), drawCard(), 0, 0, 0};
        if (getPlayerSum() == 11+11) {
        	playerCard[1] = 1;
        }
        playerSum = getPlayerSum();
        numPlayerCard = 2;
    }
    
    //Method : player
    public void showPlayerCard(){
    	String card = "";
    	for (int i = 0; i<playerCard.length; i++) {
    		card += playerCard[i]+" ";
    	}
        System.out.println("Player   : "+card);
    }
    
    //Method : computer
    public void showComputerCard(){
    	//Do not show computer card until game end
    	if (isEnd()) {
    		//Prepared
	    	String card = new String("");
	    	
	    	//Format output
	    	for (int i = 0; i<computerCard.length; i++) {
	    		card += computerCard[i]+" ";
	    	}
	    	//Out put
	    	System.out.println("Computer : "+card);
    	}
    	else {
    		System.out.println("Computer : ? ?");
    	}
    }
    
    //Method : sum card
    public void showSumCard(){
    	System.out.println("Sum of Player card    =  "+ getPlayerSum());
    	System.out.println("Sum of Computer card  =  "+ getComputerSum());
    }
    
    //Method : player
    public int getPlayerSum() {
    	playerSum = 0;
    	for (int card:playerCard) {
    		playerSum += card;
    	}
    	return playerSum;
    }
    
    //Method : computer
    public int getComputerSum() {
    	computerSum = 0;
    	for (int card:computerCard) {
    		computerSum += card;
    	}
    	return computerSum;
    }
    
    //Method : winner
    public String getWinner(){
    	//Player : Bust
    	if (getPlayerSum() > 21) {
    		winner = "Computer!!";
    	}
    	//Player : win
    	else if(getPlayerSum() > getComputerSum()){
    		
    		if (getPlayerSum() == 21) {
    			winner = "Player Blackjack!!";
    		}
    		else {
    			winner = "Player!!";
    		}
    	}
    	//Player : Lose
        else {
        	if (getComputerSum() == 21) {
        		winner = "Computer Blackjack!!";
        	}
        	else {
        		winner = "Computer!!";
        	}
        }
    	
        return winner;
    }
    
    //Method : player
    public void addMoreCard() {
    	
    	//Check if can draw
    	if (numPlayerCard < 5) {
    		
    		//If draw A card
    		int draw = drawCard();
    		if (draw == 11 && getPlayerSum()+draw > 21) {
    			draw = 1;
    		}
    		
    		//Add card
    		playerCard[numPlayerCard] = draw;
    		numPlayerCard += 1;
    	}
    }
    
    //Method : check end
    public boolean isEnd() {
    	if (end) {
    		return end;
    	}
    	else if (getPlayerSum() >= 21) {
    		return true;
    	}
    	else if (numPlayerCard == 5) {
    		return true;
    	}
    	
    	return false;
    }
    
    //Process : start
    public void start(){
        //Loop
    	do {
	        //Show card
	        showPlayerCard();
	        showComputerCard();
	        System.out.println("Player sum : "+getPlayerSum());
	        System.out.println();
	        
	        //Ask for add card
	        System.out.print("Want another card? <y /n> : ");
	        String check = input.nextLine();
	        
	        if (check.equals("y")) {
	        	addMoreCard();
	        }
	        else {
	        	end = true;
	        	break;
	        }
	       
	        System.out.println();
	        
    	} while(!isEnd());
        
        //Out come result
    	System.out.println();
        getGameResult();
    }
    
    //Process : end
    public void getGameResult() {
    	//Output
    	showPlayerCard();
    	showComputerCard();
    	System.out.println();
    	showSumCard();
    	System.out.println("The winner is : "+ getWinner());
    }
    
    //Main : Check here
    public static void main(String[] args) {
    	String loop = "y";
    	
    	//Loop
    	do {
    		
    		//Start game
    		Blackjack bj = new Blackjack();
    		bj.start();
    		
    		//Check for next more game
    		System.out.print("\nNext more game? <y/n> : ");
    		loop = input.nextLine();
    		
    	} while (loop.equals("y"));
    	
    	//End program
    	System.out.println("Good Bye!!");
    }
}

