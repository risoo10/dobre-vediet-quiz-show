package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import sample.Model.Player;
import sample.Model.Question;
import sample.Model.QuestionState;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by Riso on 7/6/2017.
 */
public class QuestionsController implements SubController {

    MainController mainController = null;

    @FXML
    private FlowPane questionsBox;

    @FXML
    private Label activePlayer;

    public void showQuestions(List<Question> questions, Player activePlayer){

        questionsBox.getChildren().clear();


        this.activePlayer.setText(activePlayer.getName());

        for(int i = 0; i<questions.size(); i++){
            Question q = questions.get(i);
            Button newQestion = new Button();
            newQestion.setText(q.getTheme());
            newQestion.getStyleClass().add("question-thumbnail");
            if(q.getState().equals(QuestionState.BURNT)){
                newQestion.getStyleClass().add("burnt");
            }
            if(q.getState().equals(QuestionState.WON)){
                newQestion.getStyleClass().add("won");
            }
            newQestion.setWrapText(true);
            newQestion.setId(i+"");
            newQestion.setOnMouseClicked(event -> {
               String id = ((Button)event.getSource()).getId();
               showQuestionDetail(Integer.parseInt(id));
            });

            questionsBox.getChildren().add(newQestion);

        }






    }

    void showQuestionDetail(int i){
        mainController.displaySingleQuestion(i);
    }


    @Override
    public void setMain(MainController main) {
        this.mainController = main;
    }
}
