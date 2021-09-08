package sample;

public class Players {
    private int points; //represents the player's score based on number of cards chosen
    private int bank; //represents how much money the player has to buy powerUps
    private boolean doubledPoints; //boolean variable set to true if the player buys the "doubled points for three turns" power up
    private int doubledPointsTurnCounter; //a counter variable to keep track of how long the doubledPoints boolean has been set to true

    //constructor
    public Players(int p){
        points = p;
        doubledPoints = false; //default false because no one starts with the doubled points powerup - need to buy it
    }

    public int addPoints(int a){
        points += a;
        return points;
    }

    public void deductPoints(int a){
        points -= a;
    }

    public int addToBank(int b){
        bank += b;
        return bank;
    }

    public int withdrawFromBank(int b){
        bank -= b;
        return bank;
    }

    public int getPoints(){
        return points;
    }

    public int returnBank(){
        return bank;
    }

    public void enableDoubledPoints(){
        doubledPoints = true;
    }

    public boolean hasDoubledPoints(){
        return doubledPoints;
    }

    // called when a player is using the doubled points powerup to set the doubledPoints boolean back to false after three turns have elapsed
    public String checkDoubledPointTurns(){
        doubledPointsTurnCounter++; //increased once every round (every time the player has a turn)

        //indicates that the power up has ended
        if(doubledPointsTurnCounter == 3){
            doubledPoints = false;
            doubledPointsTurnCounter = 0;
            return ", doubled points are over.";
        }
        return ": doubled points left for " + (3 - doubledPointsTurnCounter) + " more turn(s).";
    }
}
