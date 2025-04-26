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

        // Registra os manipuladores de eventos de teclado
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controller::keyHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, controller::keyReleasedHandler);

        stage.setTitle("Space Objects");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/main/resources/br/com/joaocarloslima/images/icon.png"));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}