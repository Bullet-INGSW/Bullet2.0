package ingsw.bullet.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoaderFXMLStage extends FXMLLoader{


    public void replaceSceneContent(String name, Stage stage, int width, int height)
    {
        this.setLocation(LoaderFXMLStage.class.getResource("/fxml/"+  name + ".fxml"));
        Parent root = null;
        try {
            root = this.load();
            if (root == null)
                System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        if(LoaderFXMLStage.class.getResource("/css/" + name + ".css")!= null)
            scene.getStylesheets().add(LoaderFXMLStage.class.getResource("/css/" + name + ".css").toString());
    }
}
