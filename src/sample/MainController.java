package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.Model.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainController {

    Gameplay gameplay;



    // Score
    Parent scorePane = null;
    ScoreController sctrl = null;


    // QUestions
    Parent questionsPane = null;
    QuestionsController questionsController = null;

    // Single Question
    Parent singlePane = null;
    SingleController singleController = null;

    // Validation
    Parent validationPane = null;
    ValidationController validationController = null;


    @FXML
    public void startGame(){


        // Load data
        JSONParser parser = new JSONParser();

        Player[] players = loadPlayersFromJson(parser);
        List<Question> questions = loadQuestionnsFromJson(parser);

        // Init data logic
        gameplay = new Gameplay();
        gameplay.startGame(players, questions);


        // Zobraz aktualne skore
        displayScore();

    }



    public Player[] loadPlayersFromJson(JSONParser parser){

        try {

            JSONObject obj = (JSONObject) parser.parse(new FileReader("E:\\RISKO\\SKOLA\\IV semester\\VAVA\\Dobre Vediet 20\\src\\sample\\Players.json"));
            long playersCount = (long)obj.get("players_count");

            int sum = (int)playersCount;

            JSONArray playersArray = (JSONArray) obj.get("players");

            Player[] players = new Player[sum];
            for(int i = 0; i< playersCount; i++){
                JSONObject playerJson = (JSONObject) playersArray.get(i);
                String name = (String) playerJson.get("name");
                players[i] = new Player(name);
            }



            return players;


        } catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public List<Question> loadQuestionnsFromJson(JSONParser parser){

        try {

            JSONObject obj = (JSONObject) parser.parse(new FileReader("E:\\RISKO\\SKOLA\\IV semester\\VAVA\\Dobre Vediet 20\\src\\sample\\Questions.json"));

            long questionsCount = (long)obj.get("questions_count");

            JSONArray questionsArray = (JSONArray) obj.get("questions");

            List<Question> questions = new LinkedList<>();
            for(int i = 0; i< questionsCount; i++){

                JSONObject questionJSON = (JSONObject) questionsArray.get(i);

                String theme = (String) questionJSON.get("theme");
                String title = (String) questionJSON.get("title");
                String description = (String) questionJSON.get("description");
                JSONArray answersJSON = (JSONArray) questionJSON.get("answers");
                List<String> answers = new ArrayList<>();
                answers.add((String) answersJSON.get(0));
                answers.add((String) answersJSON.get(1));
                answers.add((String) answersJSON.get(2));

                Boolean video = (Boolean) questionJSON.get("video-question");

                if(!video) {
                    questions.add(new Question(theme, title, description, answers));
                } else {
                    String introVideoPath = (String) questionJSON.get("intro-path");
                    String mainVideoPath = (String) questionJSON.get("main-path");

                    questions.add(new VideoQuestion(theme, title, description, answers, introVideoPath, mainVideoPath));
                }

            }

            return questions;


        } catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }


    /// ALL QUESTIONS

    public void displayAllQuestions(){

        gameplay.newRound();

        List<Question> questions = gameplay.getQuestions();


        RootElement root = RootElement.getInstance();
        // Remove previous scene
        root.getChildren().remove(1);


        if(questionsController == null){
            loadAllQuestionsView();
        }


        // Keystroke listener
        questionsPane.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.B)){

                try {
                    JSONParser parser = new JSONParser();

                    JSONObject obj = (JSONObject) parser.parse(new FileReader("E:\\RISKO\\SKOLA\\IV semester\\VAVA\\Dobre Vediet 20\\src\\sample\\Questions.json"));
                    JSONObject bonusQuestion = (JSONObject) obj.get("bonus-question");
                    String theme = (String) bonusQuestion.get("theme");
                    String title = (String) bonusQuestion.get("title");
                    String description = (String) bonusQuestion.get("description");
                    List<String> answers = new LinkedList<>();
                    answers.add(new String());
                    answers.add(new String());
                    answers.add(new String());


                    Question newBonusQuestion = new Question(theme, title, description, answers);

                    displaySingleQuestion(newBonusQuestion);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        questionsController.showQuestions(questions, gameplay.getActivePlayer());

        root.getChildren().add(questionsPane);
    }

    public void loadAllQuestionsView()  {
        try {
            // Load first View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/questions.fxml"));
            Parent screenNode = loader.load();
            this.questionsController = loader.getController();
            questionsController.setMain(this);
            this.questionsPane = screenNode;

        } catch (Exception e){
            e.printStackTrace();
        }
    }





    public void displaySingleQuestion(int index){

        // Chceck if possible to open
        Question q = gameplay.getQuestions().get(index);
        displaySingleQuestion(q);

    }

    public void displaySingleQuestion(Question question){

        // Chceck if possible to open
        if(!question.getState().equals(QuestionState.ACTIVE)){
            return;
        }

        gameplay.pickQeustion(question);


        RootElement root = RootElement.getInstance();
        // Remove previous scene
        root.getChildren().remove(1);


        if(singleController == null){
            loadSingleQuestionView();
        }

        singleController.displayQuestion(question);

        root.getChildren().add(singlePane);

    }


    public void loadSingleQuestionView()  {
        try {
            // Load first View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/single.fxml"));
            Parent screenNode = loader.load();
            this.singleController = loader.getController();
            singleController.setMain(this);
            this.singlePane = screenNode;

        } catch (Exception e){
            e.printStackTrace();
        }
    }





    public void displayScore(){

        RootElement root = RootElement.getInstance();

        // Remove previous scene
        root.getChildren().remove(1);


        if(scorePane == null){
            loadScoreView();
        }

        scorePane.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.C)){
                VBox pane = new VBox();
                pane.setPadding(new Insets(40));
                pane.getStyleClass().add("orange-bg");
                pane.setSpacing(30);
                pane.setAlignment(Pos.TOP_CENTER);
                pane.setMaxHeight(450);
                pane.setMaxWidth(400);


                ObservableList<Player> listOfPlayers = FXCollections.observableArrayList();
                listOfPlayers.addAll(gameplay.getPlayers());
                ComboBox<Player> players = new ComboBox<>(listOfPlayers);

                TextField points = new TextField();
                points.setPromptText("body");

                Button addPoints = new Button("Pridaj >");
                addPoints.setOnMouseClicked(event1 -> {
                    RootElement.getInstance().getChildren().remove(2);
                    Player selectedPlayer = players.getSelectionModel().getSelectedItem();
                    selectedPlayer.addMoney(Integer.parseInt(points.getText()));
                    this.displayScore();
                });

                pane.getChildren().add(players);
                pane.getChildren().add(points);
                pane.getChildren().add(addPoints);


                RootElement.getInstance().getChildren().add(2,pane);
            }
        });

        sctrl.showScore(gameplay.getPlayers());
        root.getChildren().add(scorePane);

    }

    public void loadScoreView()  {
        try {
            // Load first View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/score.fxml"));
            Parent screenNode = loader.load();
            this.sctrl = loader.getController();
            sctrl.setMain(this);
            this.scorePane = screenNode;

        } catch (Exception e){
            e.printStackTrace();
        }
    }





    public void showValidationView(int submittedAnswer){

        gameplay.submitAnswer(submittedAnswer);

        RootElement root = RootElement.getInstance();

        // Remove previous scene
        root.getChildren().remove(1);


        if(validationController == null){
            loadValidationView();
        }

        validationController.showValidationView(gameplay.getPickedQuestion());
        root.getChildren().add(validationPane);

    }

    public void loadValidationView()  {
        try {
            // Load first View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/validation.fxml"));
            this.validationPane = loader.load();
            this.validationController = loader.getController();
            validationController.setMain(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public void validateAnswerAndShowScore(int correctAnswer){
        gameplay.validateAnswer(correctAnswer);
        gameplay.finishedRound();

        displayScore();
    }



}
