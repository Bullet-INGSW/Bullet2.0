package ingsw.bullet.client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public Main()
    {
        this.main = this;
    }

    static Main main;

    static public Main getInstance()
    {
        return main;
    }

    public Stage stage;

    void replaceSceneContent(String name)
    {
        System.out.println(Main.class.getClassLoader().getResource("/"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml" + File.separator + name + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
            if (root == null)
                System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/css" + File.separator + name + ".css").toString());

    }


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            replaceSceneContent("menu");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.stage.setTitle("Bullet");
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
