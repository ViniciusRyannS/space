package br.com.joaocarloslima;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        scene = new Scene(root, 640, 480);

        scene.getRoot().requestFocus();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controller::keyHandler);
        stage.setTitle("Space Objects");
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResource("images/icon.png").toString()));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
