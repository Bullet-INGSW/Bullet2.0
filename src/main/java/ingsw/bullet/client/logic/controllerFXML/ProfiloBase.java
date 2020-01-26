package ingsw.bullet.client.logic.controllerFXML;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class ProfiloBase implements Initializable {
    protected static ImagePattern image =  new ImagePattern(new Image(Profilo.class.getResource("/images/profiloVuoto.jpg").toString(),
            false));

    protected static String nome = "Nome";
    protected static String cognome = "Cognome";
    protected static String email = "Email";
    protected static boolean sesso = true;

    public static ArrayList<String> getNotifiche() {
        return notifiche;
    }

    protected static ArrayList<String> notifiche = null;

    public static void setNotifiche(ArrayList<String> notifiche)
    {
        Profilo.notifiche = notifiche;
    }

    public static void addNotifica(String notifica)
    {
        if(notifiche == null)
            notifiche = new ArrayList<String>();
        Profilo.notifiche.add(notifica);
    }

    public static void setImageProfile(Image im)
    {
        image = new ImagePattern(im);
    }

    public static void setData(String nome, String cognome, String email, boolean sesso)
    {
        Profilo.nome = nome;
        Profilo.cognome = cognome;
        Profilo.email = email;
        Profilo.sesso = sesso;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Profilo.nome = nome;
    }

    public static String getCognome() {
        return cognome;
    }

    public static void setCognome(String cognome) {
        Profilo.cognome = cognome;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Profilo.email = email;
    }

    public static boolean isSesso() {
        return sesso;
    }

    public static void setSesso(boolean sesso) {
        Profilo.sesso = sesso;
    }

}
