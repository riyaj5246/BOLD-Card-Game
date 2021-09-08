package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    GridPane playGrid;
    @FXML
    Button startBtn, collectBtn, powerUpsBtn, stealPointsBtn;
    @FXML
    Label containerLbl, sizeLbl, colorLbl, patternLbl, cardsLeftLbl, projectedEarningslbl, matchingAttributeslbl, turnLbl;
    @FXML
    ListView playerScoresList, updatesList, bankList, powerupsList, stealPointsList;
    @FXML
    TextField playersTxt;
    @FXML
    Rectangle instructionsBackground;
    @FXML
    Text instructions1, instructions2, instructions3, instructions4, instructions5, instructions6, instructions7, instructions8, instructions9;

    Timer t;
    private Cards[][] board = new Cards[4][5]; //double array which stores the Cards object associated with each Grid cell
    private ImageView[][] boardImages = new ImageView[4][5]; //double array which displays the correct card image based on the board double array
    private ArrayList<Players> players = new ArrayList<>(); //Arraylist which stores all instances of the Players class
    private ArrayList<PowerUps> powerUps = new ArrayList<>(); //ArrayList which contains all instances of the PowerUps class

    private ArrayList<Cards> fullDeck = new ArrayList<>(); //ArrayList which contains full deck of cards (remains unmodified)
    private ArrayList<Cards> updatedDeck = new ArrayList<>(); //Arraylist which only contains cards that haven't been dealt
    private ArrayList<Cards> cardsSelected = new ArrayList<>(); //ArrayList which contains all the cards selected in any turn

    private int bank = 0, projectedSum = 0, numPlayers = 1, turn = 0;
    private String[] commonAttributes = new String[4]; //String array containing the common attributes for the selected index, and an "X" if not common
    int rowIndex;
    int colIndex;

    private boolean pauseTurn = false; //boolean to regulate the "two turns in a row" Power Up

    //all card images
    Image back = new Image("Resources/Back.jpg");
    Image blank = new Image("Resources/blankSpace.png");

    Image DBBB = new Image("Resources/DBBB.jpg");
    Image DBBM = new Image("Resources/DBBM.jpg");
    Image DBBS = new Image("Resources/DBBS.jpg");
    Image DBOB = new Image("Resources/DBOB.jpg");
    Image DBOM = new Image("Resources/DBOM.jpg");
    Image DBOS = new Image("Resources/DBOS.jpg");
    Image DBYB = new Image("Resources/DBYB.jpg");
    Image DBYM = new Image("Resources/DBYM.jpg");
    Image DBYS = new Image("Resources/DBYS.jpg");
    Image DCBB = new Image("Resources/DCBB.jpg");
    Image DCBM = new Image("Resources/DCBM.jpg");
    Image DCBS = new Image("Resources/DCBS.jpg");
    Image DCOB = new Image("Resources/DCOB.jpg");
    Image DCOM = new Image("Resources/DCOM.jpg");
    Image DCOS = new Image("Resources/DCOS.jpg");
    Image DCYB = new Image("Resources/DCYB.jpg");
    Image DCYM = new Image("Resources/DCYM.jpg");
    Image DCYS = new Image("Resources/DCYS.jpg");
    Image DJBB = new Image("Resources/DJBB.jpg");
    Image DJBM = new Image("Resources/DJBM.jpg");
    Image DJBS = new Image("Resources/DJBS.jpg");
    Image DJOB = new Image("Resources/DJOB.jpg");
    Image DJOM = new Image("Resources/DJOM.jpg");
    Image DJOS = new Image("Resources/DJOS.jpg");
    Image DJYB = new Image("Resources/DJYB.jpg");
    Image DJYM = new Image("Resources/DJYM.jpg");
    Image DJYS = new Image("Resources/DJYS.jpg");
    Image LBBB = new Image("Resources/LBBB.jpg");
    Image LBBM = new Image("Resources/LBBM.jpg");
    Image LBBS = new Image("Resources/LBBS.jpg");
    Image LBOB = new Image("Resources/LBOB.jpg");
    Image LBOM = new Image("Resources/LBOM.jpg");
    Image LBOS = new Image("Resources/LBOS.jpg");
    Image LBYB = new Image("Resources/LBYB.jpg");
    Image LBYM = new Image("Resources/LBYM.jpg");
    Image LBYS = new Image("Resources/LBYS.jpg");
    Image LCBB = new Image("Resources/LCBB.jpg");
    Image LCBM = new Image("Resources/LCBM.jpg");
    Image LCBS = new Image("Resources/LCBS.jpg");
    Image LCOB = new Image("Resources/LCOB.jpg");
    Image LCOM = new Image("Resources/LCOM.jpg");
    Image LCOS = new Image("Resources/LCOS.jpg");
    Image LCYB = new Image("Resources/LCYB.jpg");
    Image LCYM = new Image("Resources/LCYM.jpg");
    Image LCYS = new Image("Resources/LCYS.jpg");
    Image LJBB = new Image("Resources/LJBB.jpg");
    Image LJBM = new Image("Resources/LJBM.jpg");
    Image LJBS = new Image("Resources/LJBS.jpg");
    Image LJOB = new Image("Resources/LJOB.jpg");
    Image LJOM = new Image("Resources/LJOM.jpg");
    Image LJOS = new Image("Resources/LJOS.jpg");
    Image LJYB = new Image("Resources/LJYB.jpg");
    Image LJYM = new Image("Resources/LJYM.jpg");
    Image LJYS = new Image("Resources/LJYS.jpg");
    Image SBBB = new Image("Resources/SBBB.jpg");
    Image SBBM = new Image("Resources/SBBM.jpg");
    Image SBBS = new Image("Resources/SBBS.jpg");
    Image SBOB = new Image("Resources/SBOB.jpg");
    Image SBOM = new Image("Resources/SBOM.jpg");
    Image SBOS = new Image("Resources/SBOS.jpg");
    Image SBYB = new Image("Resources/SBYB.jpg");
    Image SBYM = new Image("Resources/SBYM.jpg");
    Image SBYS = new Image("Resources/SBYS.jpg");
    Image SCBB = new Image("Resources/SCBB.jpg");
    Image SCBM = new Image("Resources/SCBM.jpg");
    Image SCBS = new Image("Resources/SCBS.jpg");
    Image SCOB = new Image("Resources/SCOB.jpg");
    Image SCOM = new Image("Resources/SCOM.jpg");
    Image SCOS = new Image("Resources/SCOS.jpg");
    Image SCYB = new Image("Resources/SCYB.jpg");
    Image SCYM = new Image("Resources/SCYM.jpg");
    Image SCYS = new Image("Resources/SCYS.jpg");
    Image SJBB = new Image("Resources/SJBB.jpg");
    Image SJBM = new Image("Resources/SJBM.jpg");
    Image SJBS = new Image("Resources/SJBS.jpg");
    Image SJOB = new Image("Resources/SJOB.jpg");
    Image SJOM = new Image("Resources/SJOM.jpg");
    Image SJOS = new Image("Resources/SJOS.jpg");
    Image SJYB = new Image("Resources/SJYB.jpg");
    Image SJYM = new Image("Resources/SJYM.jpg");
    Image SJYS = new Image("Resources/SJYS.jpg");

    //timed reset based on how many seconds inputted...called when a turn ends and the cards need to be "reset" to all face the back
    private void timedReset(int seconds){
        t = new Timer();
        t.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask{
        public void run(){
            resetImages();
            t.cancel();
        }
    }

    @FXML //function called in the playersTxt text field, and sets the variable numPlayers given that the inputted value is between 2-6
    private void handlePlayers(){
        int players = 0;
        //sets numPlayers to desired value if input is between 2 to 6
        try{
            players = Integer.parseInt(playersTxt.getText());

            if(players < 2 || players > 6){
                updatesList.getItems().add(0, "Please enter a number between 2-6");
            }
            else{
                numPlayers = players;
            }
        }
        //called only when user input is not an integer
        catch(Exception e){
            updatesList.getItems().add(0, "Please enter a number between 2-6");
        }
    }

    @FXML //method called when player starts game, includes event handler
    private void startButton(){
        if(numPlayers < 2 || numPlayers > 6){ //makes sure handlePlayers has been called
            updatesList.getItems().add(0, "Please go set the number of players (between 2-6) first.");
        }
        else{
            collectBtn.setDisable(false);
            startBtn.setDisable(true);
            playersTxt.setDisable(true);
            powerUpsBtn.setDisable(false);
            //initializes all necessary variables, cards, sets up the board
            styleListviews();
            setPlayers();
            setFullDeck();
            setBackImages();
            setBoard();
            updateBank();
            setPowerupsList();
            cardsSelected.clear();
            commonAttributes = new String[4];
            turnLbl.setText("Player 1's turn");

            //event handler called whenever one of the cards is clicked
            EventHandler z = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    //collects the row and column indexes of the ImageViews clicked
                    rowIndex = playGrid.getRowIndex(((ImageView) t.getSource()));
                    colIndex = playGrid.getColumnIndex(((ImageView) t.getSource()));

                    if(boardImages[rowIndex][colIndex].getImage() == blank){
                        updatesList.getItems().add(0, "No card available");
                    }

                    else{
                        //displays the attributes of the card selected
                        printCardAttributes(rowIndex, colIndex);
                        //"flips" the card over on screen
                        boardImages[rowIndex][colIndex].setImage(board[rowIndex][colIndex].returnImage());

                        //happens when current player's turn has ended due to lack of matching attributes
                        if(checkifTurnOver(board[rowIndex][colIndex])){
                            timedReset(1);
                            cardsSelected.clear();
                            System.out.println("turn over");

                            projectedSum = 0;
                            updateTurnAndPoints();
                            projectedEarningslbl.setText("Projected Earnings: ");
                            matchingAttributeslbl.setText("Matching Attributes: NONE");
                        }
                        else {
                            //creating a string to display all common attributes between the selected cards
                            String attributeString = "Matching Attributes: ";
                            for(int i = 0; i < 4; i++){
                                if(!commonAttributes[i].equals("X")){
                                    switch(i) {
                                        case 0: attributeString += cardsSelected.get(0).getPattern() + ", "; break;
                                        case 1: attributeString += cardsSelected.get(0).getContainer() + ", "; break;
                                        case 2: attributeString += cardsSelected.get(0).getColor() + ", "; break;
                                        case 3: attributeString += cardsSelected.get(0).getSize() + ", "; break;
                                    }
                                }
                            }
                            matchingAttributeslbl.setText(attributeString.substring(0, attributeString.length() - 2));
                            projectedSum = (int) Math.pow(cardsSelected.size(), 2);
                            //projected earnings is the number of cards squared
                            projectedEarningslbl.setText("Projected Earnings: " + projectedSum);
                        }
                    }
                }
            };

            for (int i = 0; i < boardImages.length; i++) {
                for (int j = 0; j < boardImages[0].length; j++) {
                    //setting the onMouseClicked property for each of the ImageViews to call z (the event handler)
                    if(boardImages[i][j].getImage() != blank){
                        boardImages[i][j].setOnMouseClicked(z);
                    }
                }
            }
        }

    }

    @FXML //displays instructions on screen when button clicked
    private void displayInstructions(){
        instructionsBackground.setVisible(!instructionsBackground.isVisible());
        instructions1.setVisible(!instructions1.isVisible());
        instructions2.setVisible(!instructions2.isVisible());
        instructions3.setVisible(!instructions3.isVisible());
        instructions4.setVisible(!instructions4.isVisible());
        instructions5.setVisible(!instructions5.isVisible());
        instructions6.setVisible(!instructions6.isVisible());
        instructions7.setVisible(!instructions7.isVisible());
        instructions8.setVisible(!instructions8.isVisible());
        instructions9.setVisible(!instructions9.isVisible());
    }

    //GAME SET UP

    //method called when start button is pressed in order to set the style of the ListViews
    private void styleListviews(){
        playerScoresList.setStyle("-fx-control-inner-background: #06d6a0;");
        updatesList.setStyle("-fx-control-inner-background: #ef476f;");
        bankList.setStyle("-fx-control-inner-background: #06d6a0;");
        powerupsList.setStyle("-fx-control-inner-background: #ef476f;");
        stealPointsList.setStyle("-fx-control-inner-background: #ef476f;");}

    //called in the handlePlayers method to set the players score listview based on how many players are in the game
    private void setPlayers(){
        for(int i = 1; i <= numPlayers; i++){
            players.add(new Players(0));
            playerScoresList.getItems().add("Player " + i + " points: " + players.get(i - 1).getPoints());
        }
    }

    //initializes 81 Cards objects and adds them to an arraylist with the full deck
    private void setFullDeck(){
        fullDeck.add(new Cards("DBBB", DBBB));
        fullDeck.add(new Cards("DBBM", DBBM));
        fullDeck.add(new Cards("DBBS", DBBS));
        fullDeck.add(new Cards("DBOB", DBOB));
        fullDeck.add(new Cards("DBOM", DBOM));
        fullDeck.add(new Cards("DBOS", DBOS));
        fullDeck.add(new Cards("DBYB", DBYB));
        fullDeck.add(new Cards("DBYM", DBYM));
        fullDeck.add(new Cards("DBYS", DBYS));
        fullDeck.add(new Cards("DCBB", DCBB));
        fullDeck.add(new Cards("DCBM", DCBM));
        fullDeck.add(new Cards("DCBS", DCBS));
        fullDeck.add(new Cards("DCOB", DCOB));
        fullDeck.add(new Cards("DCOM", DCOM));
        fullDeck.add(new Cards("DCOS", DCOS));
        fullDeck.add(new Cards("DCYB", DCYB));
        fullDeck.add(new Cards("DCYM", DCYM));
        fullDeck.add(new Cards("DCYS", DCYS));
        fullDeck.add(new Cards("DJBB", DJBB));
        fullDeck.add(new Cards("DJBM", DJBM));
        fullDeck.add(new Cards("DJBS", DJBS));
        fullDeck.add(new Cards("DJOB", DJOB));
        fullDeck.add(new Cards("DJOM", DJOM));
        fullDeck.add(new Cards("DJOS", DJOS));
        fullDeck.add(new Cards("DJYB", DJYB));
        fullDeck.add(new Cards("DJYM", DJYM));
        fullDeck.add(new Cards("DJYS", DJYS));

        fullDeck.add(new Cards("LBBB", LBBB));
        fullDeck.add(new Cards("LBBM", LBBM));
        fullDeck.add(new Cards("LBBS", LBBS));
        fullDeck.add(new Cards("LBOB", LBOB));
        fullDeck.add(new Cards("LBOM", LBOM));
        fullDeck.add(new Cards("LBOS", LBOS));
        fullDeck.add(new Cards("LBYB", LBYB));
        fullDeck.add(new Cards("LBYM", LBYM));
        fullDeck.add(new Cards("LBYS", LBYS));
        fullDeck.add(new Cards("LCBB", LCBB));
        fullDeck.add(new Cards("LCBM", LCBM));
        fullDeck.add(new Cards("LCBS", LCBS));
        fullDeck.add(new Cards("LCOB", LCOB));
        fullDeck.add(new Cards("LCOM", LCOM));
        fullDeck.add(new Cards("LCOS", LCOS));
        fullDeck.add(new Cards("LCYB", LCYB));
        fullDeck.add(new Cards("LCYM", LCYM));
        fullDeck.add(new Cards("LCYS", LCYS));
        fullDeck.add(new Cards("LJBB", LJBB));
        fullDeck.add(new Cards("LJBM", LJBM));
        fullDeck.add(new Cards("LJBS", LJBS));
        fullDeck.add(new Cards("LJOB", LJOB));
        fullDeck.add(new Cards("LJOM", LJOM));
        fullDeck.add(new Cards("LJOS", LJOS));
        fullDeck.add(new Cards("LJYB", LJYB));
        fullDeck.add(new Cards("LJYM", LJYM));
        fullDeck.add(new Cards("LJYS", LJYS));

        fullDeck.add(new Cards("SBBB", SBBB));
        fullDeck.add(new Cards("SBBM", SBBM));
        fullDeck.add(new Cards("SBBS", SBBS));
        fullDeck.add(new Cards("SBOB", SBOB));
        fullDeck.add(new Cards("SBOM", SBOM));
        fullDeck.add(new Cards("SBOS", SBOS));
        fullDeck.add(new Cards("SBYB", SBYB));
        fullDeck.add(new Cards("SBYM", SBYM));
        fullDeck.add(new Cards("SBYS", SBYS));
        fullDeck.add(new Cards("SCBB", SCBB));
        fullDeck.add(new Cards("SCBM", SCBM));
        fullDeck.add(new Cards("SCBS", SCBS));
        fullDeck.add(new Cards("SCOB", SCOB));
        fullDeck.add(new Cards("SCOM", SCOM));
        fullDeck.add(new Cards("SCOS", SCOS));
        fullDeck.add(new Cards("SCYB", SCYB));
        fullDeck.add(new Cards("SCYM", SCYM));
        fullDeck.add(new Cards("SCYS", SCYS));
        fullDeck.add(new Cards("SJBB", SJBB));
        fullDeck.add(new Cards("SJBM", SJBM));
        fullDeck.add(new Cards("SJBS", SJBS));
        fullDeck.add(new Cards("SJOB", SJOB));
        fullDeck.add(new Cards("SJOM", SJOM));
        fullDeck.add(new Cards("SJOS", SJOS));
        fullDeck.add(new Cards("SJYB", SJYB));
        fullDeck.add(new Cards("SJYM", SJYM));
        fullDeck.add(new Cards("SJYS", SJYS));
    }

    //sets the board 2D array to contain a random Cards object from the full deck, modifies the updatedDeck accordingly
    private void setBoard(){
        for(int i = 0; i < fullDeck.size(); i++){
            updatedDeck.add(fullDeck.get(i));
        }

        for(int i = 0; i < boardImages.length; i++){
            for(int j = 0; j < boardImages[0].length; j++){
                int index = (int) (Math.random() * updatedDeck.size());
                board[i][j] = updatedDeck.get(index);
                updatedDeck.remove(index);
            }
        }

        cardsLeftLbl.setText("Cards left (undealt): " + updatedDeck.size());
    }

    //creates an imageview for every index on the 2D BoardImages array and sets it to the "back" of the card, which is the default beginning position
    private void setBackImages(){
        for(int i = 0; i < boardImages.length; i++){
            for(int j = 0; j < boardImages[0].length; j++){
                boardImages[i][j] = new ImageView();
                boardImages[i][j].setFitHeight(100);
                boardImages[i][j].setFitWidth(80);
                boardImages[i][j].setImage(back);
                playGrid.add(boardImages[i][j], j, i);
            }
        }
    }

    //METHODS CALLED DURING AND IN BETWEEN PLAYER TURNS

    @FXML //called when player chooses to collect their points and end turn
    private void collectButton(){
        if(cardsSelected.size() < 2){
            updatesList.getItems().add(0, "Need to choose more cards!");
        }
        else{
            //updates turn, adds points, clears necessary variables, and essentially preps for next player
            collectMoney(turn%numPlayers);
            updateBank();
            updateTurnAndPoints();
            updateBoard();
            cardsSelected.clear();
            projectedSum = 0;
            projectedEarningslbl.setText("Projected Earnings: ");
            commonAttributes = new String[4];
            matchingAttributeslbl.setText("Matching Attributes: ");
        }
    }

    //updates turn and adds points to the player whose turn just ended
    private void updateTurnAndPoints(){
        //when turn is 0, player 1's turn, and so on until turn%numPlayers = 0, when it's player 1's turn again
        int numTurn = (turn%numPlayers) + 1;

        //checks to see if the player whose turn just ended had the doubled points PowerUp enabled
        //updates points accordingly
        if(players.get(numTurn - 1).hasDoubledPoints()){
            players.get(numTurn - 1).addPoints(projectedSum*2);
            updatesList.getItems().add(0,"Player " + numTurn + players.get(numTurn - 1).checkDoubledPointTurns());
        }
        else{
            players.get(numTurn - 1).addPoints(projectedSum);
        }

        //checks to see if the player whose turn just ended had the two turns in a row power up on...if so, turn doesn't update, and same player gets 2 turns
        if(!pauseTurn){
            turn++;
            int newTurn = (turn%numPlayers) + 1;
            turnLbl.setText("Player " + newTurn + "'s turn");
        }
        else{
            pauseTurn = false;
        }

        updatePlayerScores(); //updates scores ListView

        //check to see if full game has ended
        if(checkGameOver()){
          collectBtn.setDisable(true);
          powerUpsBtn.setDisable(true);
          findWinner();
        }
    }

    //updates player scores ListView to display the updated scores after end of turn
    private void updatePlayerScores(){
        playerScoresList.getItems().clear();
        for(int i = 1; i <= numPlayers; i++){
            playerScoresList.getItems().add("Player " + i + " Points: " + players.get(i-1).getPoints());
        }
    }

    //cards that were collected by the player are taken off of the board and replaced by unused cards
    private void updateBoard(){
        for(int i = 0; i < cardsSelected.size(); i++){ //goes through all the cards selected
            for (int a = 0; a < board.length; a++){ //goes through all rows on board
                for (int b = 0; b < board[0].length; b++){ //goes through all columns on board
                    //checks to see if the board position needs to be replaced with new card
                    if(cardsSelected.get(i) == board[a][b]){
                        if(updatedDeck.size()==0){ //if there are no new cards left
                            boardImages[a][b].setImage(blank);
                        }
                        else{ //if there are cards left
                            int index = (int) (Math.random() * updatedDeck.size());
                            board[a][b] = updatedDeck.get(index);
                            updatedDeck.remove(index);
                        }
                    }
                }
            }
        }
        resetImages();
        cardsLeftLbl.setText("Cards left (undealt): " + updatedDeck.size());
    }

    //method called in event handler to make sure that the players turn doesn't end (returns false to keep turn going)
    private boolean checkifTurnOver(Cards chosenCard){
        //returns false if the card picked is the first card of a turn, and sets the common attributes array to contain elements of chosen card
        if(cardsSelected.isEmpty()){
            for(int i = 0; i < 4; i++){
                commonAttributes[i] = chosenCard.getAttributes()[i];
            }
            cardsSelected.add(chosenCard);
            return false;
        }
        //returns false if user clicks on an already selected card, the card doesn't count for anything
        for(int i = 0; i < cardsSelected.size(); i++){
            if(chosenCard.returnImage() == cardsSelected.get(i).returnImage()){
                updatesList.getItems().add(0, "You cannot reselect/unselect a card you have already chosen.");
                return false;
            }
        }
        cardsSelected.add(chosenCard);
        checkMatchingAttributes(chosenCard);
        //if there are any common attributes remaining between all chosen cards, returns false
        for(int i = 0; i < 4; i++){
            if(!(commonAttributes[i].equals("X"))){ //"X" represents that the attributes don't match
                return false;
            }
        }
        //returns true for turn over only when there are no common attributes left
        return true;
    }

    //updates the commonAttributes array to account for the new card just selected
    private void checkMatchingAttributes(Cards cardChosen){
        for(int i = 0; i < 4; i++){
            if(!(cardChosen.getAttributes()[i].equals(commonAttributes[i]))) {
                commonAttributes[i] = "X"; //an 'X' indicates that the given attribute is no longer common among all the cards
            }
        }
    }

    //sets all the ImageViews in the GridView back to default (facing the back)
    private void resetImages(){
        for(int i = 0; i < boardImages.length; i++){
            for(int j = 0; j < boardImages[0].length; j++){
                if(boardImages[i][j].getImage() != blank){
                    boardImages[i][j].setImage(back);
                }
            }
        }
    }

    //displays all attributes of the card selected on the screen
    private void printCardAttributes(int rowIndex, int colIndex){
        patternLbl.setText("Pattern: " + board[rowIndex][colIndex].getPattern());
        containerLbl.setText("Container: " + board[rowIndex][colIndex].getContainer());
        colorLbl.setText("Color: " + board[rowIndex][colIndex].getColor());
        sizeLbl.setText("Size: " + board[rowIndex][colIndex].getSize());
    }

    //checks to see if the game is over...happens when full board is blank
    private boolean checkGameOver(){
        for(int i = 0; i < boardImages.length; i++){
            for(int j = 0; j < boardImages[0].length; j++){
                if (boardImages[i][j].getImage() != blank){
                    return false;
                }
            }
        }
        return true;
    }

    //determines player with the maximum score
    private void findWinner(){
        int maxScore = players.get(0).getPoints();

        for(int i = 1; i < numPlayers; i++){
            if(players.get(i).getPoints() > maxScore){
                maxScore = players.get(i).getPoints();
            }
        }
        String displayWinners = "The Winner(s): ";
        for(int i = 0; i < numPlayers; i++){
            if(players.get(i).getPoints() == maxScore){
                displayWinners += "Player " + (i + 1) + ", ";
            }
        }
        displayWinners = displayWinners.substring(0, displayWinners.length() - 2) + "!";
        updatesList.getItems().add(0, displayWinners );
    }

    //gives the player money (to buy power ups) based on the number of common attributes and number of cards selected
    private void collectMoney(int index){
        int numMatchingAttributes = 0;
        //determines number of matching attributes
        for(int i = 0; i < commonAttributes.length; i++){
            if (!commonAttributes[i].equals("X")){
                numMatchingAttributes++;
            }
        }
        int moneyAdded = (int) Math.pow(cardsSelected.size(), numMatchingAttributes);
        //money added dependent on number of cards selected and number of matching attributes
        players.get(index).addToBank(moneyAdded);
        //System.out.println("Money: $" + moneyAdded);
    }

    //updates the bank listview to show how much money all the players have
    private void updateBank(){
        bankList.getItems().clear();
        for(int i = 0; i < players.size(); i++){
            bankList.getItems().add(i, "Player " + (i+1) + " Bank: $" +  players.get(i).returnBank());
        }
    }

    //A LEVEL PROJECT: POWERUPS

    //initializes PowerUps objects, adds them to the powerUps arraylist, and displays this on the Powerups ListView
    private void setPowerupsList(){
        powerUps.add(new PowerUps(5, "Steal 50% of any player's points"));
        powerUps.add(new PowerUps(5, "Steal 20% of every player's points"));
        powerUps.add(new PowerUps(10, "Received doubled points for 3 rounds"));
        powerUps.add(new PowerUps(12, "Double your points right now"));
        powerUps.add(new PowerUps(5, "Get two turns in a row"));

        for(int i = 0; i < powerUps.size(); i++){
            powerupsList.getItems().add(powerUps.get(i).getName() + ": $" + powerUps.get(i).getCost());
        }
    }

    @FXML //method called when player choses to buy a powerup
    private void buyPowerups(){
        int powerUpIndex = powerupsList.getSelectionModel().getSelectedIndex();
        int playerIndex = turn%numPlayers;
        //ensures that a powerup is selected (default int for none selected is -1)
        if(powerUpIndex == -1){
            updatesList.getItems().add(0, "Please select a power up first");
        }
        //ensures player has sufficient money in bank to purchase selected powerup
        else if(players.get(playerIndex).returnBank() >= powerUps.get(powerUpIndex).getCost()){
            players.get(playerIndex).withdrawFromBank(powerUps.get(powerUpIndex).getCost());
            updateBank();

            switch(powerUpIndex){
                case 0: //powerup chosen: stealing 50% of points from one player
                    updatesList.getItems().add(0, "Player " + (playerIndex + 1) + ", please select the player who you would like to steal 50% from.");
                    stealPointsList.getItems().clear();
                    for(int i = 0; i < players.size(); i++){
                        stealPointsList.getItems().add("Player " + (i+1));
                    }
                    stealPointsBtn.setVisible(true);
                    stealPointsList.setVisible(true);
                    break;

                case 1: //powerup chosen: stealing 20% of points from everyone
                    updatesList.getItems().add(0, "20% of everyone's points were stolen and given to Player " + (playerIndex + 1) + ".");
                    steal20PercentPowerup(players.get(playerIndex));
                    break;

                case 2: //powerup chosen: 3 rounds of doubled earnings
                    updatesList.getItems().add(0, "Player " + (playerIndex + 1) + " will get doubled points for the next three rounds.");
                    players.get(playerIndex).enableDoubledPoints();
                    break;

                case 3: //powerup chosen: immediately doubled points
                    updatesList.getItems().add(0, "Player " + (playerIndex + 1) + "'s points were doubled.");
                    doublePointsPowerup(players.get(playerIndex));
                    break;

                case 4: //powerup chosen: allowing player to have two turns in a row
                    updatesList.getItems().add(0 ,"Player " + (playerIndex + 1) +", you will now get two turns in a row.");
                    pauseTurn = true;
                    break;
            }
        }
        else{
            updatesList.getItems().add(0, "Not enough money in the bank to buy this.");
        }
    }

    //method called when player buys powerup to steal 20 percent of everyone's points
    private void steal20PercentPowerup(Players person){
        int totalPts = 0;
        int ptsTaken;
        for(int i = 0; i < players.size(); i++){
            ptsTaken = (int) (0.2 * players.get(i).getPoints());
            players.get(i).deductPoints(ptsTaken);
            totalPts += ptsTaken;
        }
        person.addPoints(totalPts);
        updatePlayerScores();
    }

    //method called to double player points
    private void doublePointsPowerup(Players person){
        person.addPoints(person.getPoints());
        updatePlayerScores();
    }

    @FXML //method called to steal 50% from another player
    private void stealPoints(){
        int selectedIndex = stealPointsList.getSelectionModel().getSelectedIndex();
        if(selectedIndex == -1){ //if no one is chosen
            updatesList.getItems().add(0, "Please select a player to steal from first.");
        }
        else if(selectedIndex == turn%numPlayers){ //if player chose themselves
            updatesList.getItems().add(0, "Pick someone else. Why would you steal from yourself?");
        }
        else{
            int stolenPoints = (int) (0.5* players.get(selectedIndex).getPoints());
            players.get(selectedIndex).deductPoints(stolenPoints);
            players.get(turn%numPlayers).addPoints(stolenPoints);
            updatesList.getItems().add(0, "Player " + (turn%numPlayers + 1) + " stole 50% of Player " + (selectedIndex + 1) + "'s points.");

            stealPointsList.setVisible(false);
            stealPointsBtn.setVisible(false);
            updatePlayerScores();
        }

    }
}


