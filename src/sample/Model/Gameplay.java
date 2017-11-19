package sample.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Riso on 6/8/2017.
 */
public class Gameplay {


    private static Gameplay instance = null;

    public Gameplay(){

    }


    private static int rounds;
    private Player activePlayer;
    private Question pickedQuestion = null;
    private Player[] players;
    private List<Question> questions = new LinkedList<>();



    public void startGame(Player[] players, List<Question> questions){

        System.out.println("Zacala sa nova hra.");

        rounds = 0;

        this.players = players;
        this.questions = questions;

    }



    public void newRound(){
        rounds++;
        activePlayer = players[rounds % players.length];

        System.out.println("Nove kolo - hrac na tahu je " + activePlayer.getName());
    }


    public void pickQeustion(Question question){

        pickedQuestion = question;

        System.out.println("Bola vybrata otazka : " + question.getTheme());


    }



    public void submitAnswer(int submittedAnswer){
        pickedQuestion.setPickedAnswer(submittedAnswer);
        System.out.println("Bola vybrata moznost : " + pickedQuestion.getAnswers().get(submittedAnswer));
    }




    public void validateAnswer(int correctAnswer){

        if(pickedQuestion.getPickedAnswer() == correctAnswer){
            pickedQuestion.setState(QuestionState.WON);
            System.out.println("Spravneeee !!!");
        } else {
            pickedQuestion.setState(QuestionState.BURNT);
            System.out.println("Nespravne !");
        }
    }


    public void finishedRound(){
        if(pickedQuestion.getState().equals(QuestionState.WON)){
            activePlayer.wonQuestion();
        }

        System.out.println("Koniec tahu hraca " + activePlayer.getName() + ", stav bodov : " + activePlayer.getMoney() );
    }


    public Player[] getPlayers() {
        return players;
    }

    public List<Question> getQuestions() {
        return questions;
    }


    public Player getActivePlayer() {
        return activePlayer;
    }

    public Question getPickedQuestion() {
        return pickedQuestion;
    }
}
