package sample;


import javafx.scene.image.Image;

public class Cards {

    private Image cardPicture;
    private String[] attributes = new String[4];

    //constructor
    public Cards(String cardID, Image cardPic){
        cardPicture = cardPic;
        //creates an array of attributes by breaking up the cardID into individual letters
        for(int i = 0; i < attributes.length; i++){
            attributes[i] = cardID.substring(i, i+1);
        }
    }

    public String[] getAttributes(){
        return attributes;
    }

    //returns type of pattern based on letter at index 0 of attributes
    public String getPattern(){
        if (attributes[0].equals("D")){
            return "Dots";
        }
        else if(attributes[0].equals("L")){
            return "Lines";
        }
        return "Stars";
    }

    //returns type of container based on letter at index 1 of attributes
    public String getContainer(){
        if (attributes[1].equals("B")){
            return "Bottle";
        }
        else if(attributes[1].equals("C")){
            return "Cup";
        }
        return "Jug";
    }

    //returns type of color based on letter at index 2 of attributes
    public String getColor(){
        if (attributes[2].equals("B")){
            return "Blue";
        }
        else if(attributes[2].equals("O")){
            return "Orange";
        }
        return "Yellow";
    }

    //returns type of size based on letter at index 3 of attributes
    public String getSize(){
        if (attributes[3].equals("B")){
            return "Big";
        }
        else if(attributes[3].equals("M")){
            return "Medium";
        }
        return "Small";
    }

    public Image returnImage() {
        return cardPicture;
    }
}
