package ingsw.bullet.client.view;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.logic.calendar.CalendarioViewCondiviso;
import ingsw.bullet.model.Calendario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

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

    public FXMLLoader replaceSceneContent(String name, Stage stage, int width, int height)
    {
        //stage.close();
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

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        if(getClass().getResource("/css/" + name + ".css")!= null)
            scene.getStylesheets().add(getClass().getResource("/css/" + name + ".css").toString());
        stage.setTitle("Bullet");
        stage.show();
        return loader;
    }


//    public void start(Stage stage)
//    {
//        this.stage = stage;
//
//        Scene scene = new Scene(new CalendarioViewCondiviso(new Calendario()));
//        stage.setTitle("Calendar");
//        stage.setScene(scene);
//        stage.setWidth(800);
//        stage.setHeight(800);
//        stage.centerOnScreen();
//        stage.show();
//    }

    public void start(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);

        try {
            replaceSceneContent("menu",new Stage(), 600, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop(){
        System.exit(0);
        DBClient.getIstance().chiudi();
    }
}
