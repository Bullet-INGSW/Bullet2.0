package ingsw.bullet.client.view;

import com.esotericsoftware.kryonet.Client;
import ingsw.bullet.client.ClientConnectionhandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    ClientConnectionhandler connessione=null;



    public Main()
    {
        if(Main.main == null)
            Main.main = this;
        else
            throw new RuntimeException("Applicazione già aperta");
    }

    static Main main = null;

    static public Main getInstance()
    {
        return main;
    }

    public Stage stage;

    void replaceSceneContent(String name)
    {
        System.out.println(Main.class.getClassLoader().getResource("/"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/"+  name + ".fxml"));
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
        if(getClass().getResource("/css/" + name + ".css")!= null)
            scene.getStylesheets().add(getClass().getResource("/css/" + name + ".css").toString());
    }


    @Override
    public void start(Stage stage) {

        this.stage = stage;
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        try {
            replaceSceneContent("creaGruppo");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.stage.setTitle("Bullet");
        this.stage.show();
        ClientConnectionhandler.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}
