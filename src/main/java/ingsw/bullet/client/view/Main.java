package ingsw.bullet.client.view;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.*;
import com.calendarfx.view.popover.EntryDetailsView;
import com.calendarfx.view.popover.EntryPopOverContentPane;
import ingsw.bullet.client.logic.calendar.Calendario;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

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


    public void start(Stage stage)
    {
        this.stage = stage;

        Scene scene = new Scene(new Calendario("no"));
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.centerOnScreen();
        stage.show();
    }

    public void start2(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);

        try {
            replaceSceneContent("aggiungiEtichette",new Stage(), 200, 200);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }


}
