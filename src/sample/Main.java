package sample;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("sample/Views/style.css");



        RootElement root = RootElement.getInstance();
        root.setAlignment(Pos.CENTER);

        // Load Background screen
        ImageView background = new ImageView("img/bg.jpg");
        root.getChildren().add(background);
        MainController startController = null;

        try {
            // Load first View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/start.fxml"));
            Parent screenNode = loader.load();
            startController = loader.getController();
            root.getChildren().add(screenNode);


         // Comemmeeent

        }catch (Exception e){
            e.printStackTrace();
        }


        primaryStage.setTitle("Dobre znac !");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();




    }


    public static void main(String[] args) {

        launch(args);


    }



}
