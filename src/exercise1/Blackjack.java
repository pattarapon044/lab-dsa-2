package exercise1;


import java.util.Random;

public class Blackjack{
    private int[] playerCard;
    private int[] computerCard;
    private int playerSum;
    private int computerSum;
    private String winner;

    public Blackjack(){
        //Initialize game data
        playerCard = new int[2];
        computerCard = new int[2];
        playerSum = 0;
        computerSum = 0;
        Random random = new Random();

        //Player
        playerCard[0] = random.nextInt(11)+1;
        playerCard[1] = random.nextInt(11)+1;
        
        //Dealer
        computerCard[0] = random.nextInt(11)+1;
        computerCard[1] = random.nextInt(11)+1;
        
        //Sum
        playerSum = playerCard[0]+playerCard[1];
        computerSum = computerCard[0]+computerCard[1];

        //Check both 11
        if(playerSum == 11+11){
            playerCard[1] = 1;
        }
        if(computerSum == 11+11){
            computerCard[1] = 1;
        }
    }    
    
    public void showPlayerCard(){
        System.out.println("Player   : "+playerCard[0]+" "+playerCard[1]);
    }
    
    public void showComputerCard(){
    	System.out.println("Computer : "+computerCard[0]+" "+computerCard[1]);
    }
    
    public void showSumCard(){
    	System.out.println("Sum of Player card    =  "+playerSum);
    	System.out.println("Sum of Computer card  =  "+computerSum);
    }
    
    public void checkWinner(){
        if(playerSum > computerSum){
            winner = "Player";
        }
        else {
            winner = "Computer";
        }
    }

    public String getWinner(){
        return winner;
    }
    
    public static void main(String[] args){
    	//Create object of Blackjack
        Blackjack bj = new Blackjack();                
        
        //Show card
        bj.showPlayerCard();
        bj.showComputerCard();
        System.out.println();
        
        //Show sum card
        bj.showSumCard();
        
        //Show winner
        bj.checkWinner();
        System.out.println("The winner is : " +bj.getWinner());
    }       
}
