package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

/**
 * Created by Riso on 7/5/2017.
 */
public class RootElement extends StackPane {

    private static RootElement instance = null;

    private RootElement(){

    }

    public static RootElement getInstance(){
        if(instance == null){
            instance = new RootElement();
        }

        return instance;
    }


    public void playFullScreenVideo(String path){
        File intro = new File(path);
        Media media = new Media(intro.toURI().toASCIIString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setFitHeight(1080);
        mediaView.setFitWidth(1920);

        RootElement root = RootElement.getInstance();
        root.getChildren().add(mediaView);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                RootElement root = RootElement.getInstance();
                root.getChildren().remove(2);
            }
        });
    }

}
