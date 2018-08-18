package lafiesta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginMain.fxml"));

        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("view/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Teste");
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
