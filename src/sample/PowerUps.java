package sample;

public class PowerUps {
    private int cost; //represents how much is deducted from a player's bank if they buy the powerUp
    private String name; //contains the display name of the powerUp on the Power Ups Listview

    //constructor
    public PowerUps(int money, String n){
        cost = money;
        name = n;
    }

    public int getCost(){
        return cost;
    }

    public String getName(){
        return name;
    }
}
