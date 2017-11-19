package sample.Model;

/**
 * Created by Riso on 6/8/2017.
 */
public class Player {

    private String name;

    private int money = 0;


    public Player(String name){
        this.name = name;
    }


    public void wonQuestion(){
        this.money += 300;
    }

    public String getName(){
        return this.name;
    }

    public int getMoney(){
        return money;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public String toString(){
        return name;
    }
}
