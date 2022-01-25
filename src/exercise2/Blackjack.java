package exercise2;

import java.util.Random;
import java.util.Scanner;

public class Blackjack{
	
	private static Scanner input = new Scanner(System.in);
	
	//Attribute
    private int[] playerCard;
    private int[] computerCard;
    
    private int playerSum;
    private int computerSum;
    
    private int numPlayerCard;
    private int playerMax;
    
    private String winner;
    private boolean end;
    
    //Private : setup
	private int[] setCard(int max) {
    	int[] cards = new int[max];
    	
    	for (int i = 0; i<max; i++) {
    		cards[i] = 0;
    	}
    	
    	return cards;
    }
	
	//Private : draw
	private int drawCard() {
		Random r = new Random();
		return r.nextInt(11)+1;
	}
	
	private void prepare() {
		//Setup data
		playerSum = 0;
        computerSum = 0;
        playerMax = 5;
        end = false;
        
        //Setup computer
        computerCard = setCard(2);
        computerCard[0] = drawCard();
        computerCard[1] = drawCard();
        if (computerCard[0] == 11 && computerCard[1] == 11) {
        	computerCard[1] = 1;
        }
        computerSum = getComputerSum();
        
        //Setup player
        playerCard = setCard(playerMax);
        playerCard[0] = drawCard();
        playerCard[1] = drawCard();
        if (playerCard[0] == 11 && playerCard[1] == 11) {
        	playerCard[1] = 1;
        }
        playerSum = getPlayerSum();
        numPlayerCard = 2;
	}
    
    //Constructor
    public Blackjack(){
        //Initialize game data
        prepare();
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
    	if (getPlayerSum() > 21) {
    		winner = "Computer!!";
    	}
    	else if(getPlayerSum() > getComputerSum()){
            winner = "Player!!";
        }
    	else if (getPlayerSum() == getComputerSum()) {
    		winner = "Draw!!";
    	}
        else {
            winner = "Computer!!";
        }
    	
        return winner;
    }
    
    //Method : player
    public void addMoreCard() {
    	if (numPlayerCard < 5) {
    		playerCard[numPlayerCard] = drawCard();
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
    	//prepare before start
    	prepare();
    	
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
    	showPlayerCard();
    	showComputerCard();
    	System.out.println();
    	showSumCard();
    	System.out.println("The winner is : "+ getWinner());
    }
    
    //Main : Check here
    public static void main(String[] args) {
    	//Prepare object and check
    	Blackjack bj = new Blackjack();
    	String check;
    	
    	//Loop
    	do {
    		bj.start();
    		System.out.print("\nNext more game? <y/n> : ");
    		check = input.nextLine();
    		
    	} while (check.equals("y"));
    	
    	System.out.println("Good Bye!!");
    }
}

