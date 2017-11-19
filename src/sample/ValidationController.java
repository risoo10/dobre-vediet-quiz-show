package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import sample.Model.Question;
import sample.Model.VideoQuestion;

import java.io.File;
import java.util.List;

/**
 * Created by Riso on 7/7/2017.
 */
public class ValidationController implements SubController{

    MainController mainController = null;

    @FXML
    private ToggleGroup answers;

    @FXML
    private RadioButton answerC;

    @FXML
    private RadioButton answerB;

    @FXML
    private RadioButton answerA;

    @FXML
    private HBox container;



    @FXML
    void showScore(ActionEvent event) {
        String answerIndex = ((RadioButton)answers.getSelectedToggle()).getId();
        mainController.validateAnswerAndShowScore(Integer.parseInt(answerIndex));
    }



    public void showValidationView(Question question){

        List<String> answers = question.getAnswers();

        this.answerA.setText(answers.get(0));
        answerA.setId("0");
        this.answerB.setText(answers.get(1));
        answerB.setId("1");
        this.answerC.setText(answers.get(2));
        answerC.setId("2");



        this.answers.selectToggle(null);

        container.getChildren().clear();

        if(question.hasVideo()){
            VideoQuestion videoQuestion = (VideoQuestion)question;
            Button mainVideo = new Button("Video >");
            mainVideo.getStyleClass().add("main");
            mainVideo.setOnMouseClicked((event) -> {
                RootElement.getInstance().playFullScreenVideo(videoQuestion.getMainPath());
            });

            container.getChildren().add(mainVideo);
        }
    }






    @Override
    public void setMain(MainController main) {
        this.mainController = main;
    }
}
