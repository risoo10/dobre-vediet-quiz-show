package sample.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Riso on 6/8/2017.
 */

public class Question {

    private String theme;

    private String title;

    private String description;

    private List<String> answers = new ArrayList<>(3);

    private QuestionState state;

    private int pickedAnswer;


    public Question(String theme, String title, String description, List<String> answers){

        this.state = QuestionState.ACTIVE;
        this.theme = theme;
        this.title = title;
        this.description = description;
        this.answers = answers;
    }



    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public QuestionState getState() {
        return state;
    }

    public void setState(QuestionState state) {
        this.state = state;
    }

    public int getPickedAnswer() {
        return pickedAnswer;
    }

    public void setPickedAnswer(int pickedAnswer) {
        this.pickedAnswer = pickedAnswer;
    }


    public Boolean hasVideo(){
        return false;
    }
}
