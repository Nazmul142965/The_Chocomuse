package bd.edu.seu.tcms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("The Chocomuse!");
        stage.setScene(scene);
        stage.show();
        System.out.println("Run Application Successfully");
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxml){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Failed to Change Scene");
            e.printStackTrace();
        }

    }


}