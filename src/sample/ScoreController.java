package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import sample.Model.Player;

/**
 * Created by Riso on 7/5/2017.
 */
public class ScoreController implements SubController{

    //************* SCORE **************
    Parent scoreScene = null;
    @FXML
    private Label score2;
    @FXML
    private Label scoreName2;
    @FXML
    private Label score1;
    @FXML
    private Label scoreName1;


    MainController mainController = null;




    public void showScore(Player[] players){

        Player player1 = players[0];
        Player player2 = players[1];

        score1.setText(player1.getMoney() + "");
        scoreName1.setText(player1.getName());

        score2.setText(player2.getMoney() + "");
        scoreName2.setText(player2.getName());
    }


    public void setMain(MainController main){
        this.mainController = main;
    }

    @FXML
    public void getQuestions(){
        mainController.displayAllQuestions();
    }
}
