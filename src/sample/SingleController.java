package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import sample.Model.Question;
import sample.Model.VideoQuestion;

import java.io.File;


/**
 * Created by Riso on 7/7/2017.
 */
public class SingleController implements SubController {

    MainController mainController = null;


    @FXML
    private ToggleGroup answers;

    @FXML
    private RadioButton answerC;

    @FXML
    private Text description;

    @FXML
    private RadioButton answerB;

    @FXML
    private Label theme;

    @FXML
    private Label title;

    @FXML
    private RadioButton answerA;

    @FXML
    private HBox buttons;



    public void displayQuestion(Question question){
        this.theme.setText(question.getTheme());
        this.title.setText(question.getTitle());
        this.description.setText(question.getDescription());

            this.answerA.setText(question.getAnswers().get(0));
            answerA.setId("0");
            this.answerB.setText(question.getAnswers().get(1));
            answerB.setId("1");
            this.answerC.setText(question.getAnswers().get(2));
            answerC.setId("2");


        answers.selectToggle(null);




        buttons.getChildren().clear();

        if(question.hasVideo()){
            VideoQuestion videoQuestion = (VideoQuestion)question;
            Button introVideo = new Button("Upútavka");
            introVideo.setOnMouseClicked((event) -> {
                RootElement.getInstance().playFullScreenVideo(videoQuestion.getIntroPath());
            });
            introVideo.getStyleClass().add("main");
            buttons.getChildren().add(introVideo);
        }

        Button next = new Button("Ďalej >");
        next.setOnMouseClicked(event -> {
            String answerId = ((RadioButton)answers.getSelectedToggle()).getId();
            pickedAnswer(Integer.parseInt(answerId));
        });

        buttons.getChildren().add(next);




    }


    private void pickedAnswer(int answer){
        mainController.showValidationView(answer);
    }




    @Override
    public void setMain(MainController main) {
        this.mainController = main;
    }
}
