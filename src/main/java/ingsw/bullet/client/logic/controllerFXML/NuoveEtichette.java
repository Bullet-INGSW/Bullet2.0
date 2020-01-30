package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.calendar.Calendario;
import ingsw.bullet.client.view.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class NuoveEtichette extends DialogCalendario{



    HashMap<String, Integer> coloriMap =new HashMap<>(){ {
        put("Verde" ,0);
        put("Blu", 1);
        put("Giallo", 2);
        put("Viola", 3);
        put("Rosso", 4);
        put("Arancione", 5);
        put("Marrone", 6);
    }};

    @FXML
    private TextField nomeEtichetta;

    @FXML
    private SplitMenuButton coloreEtichetta;

    public int getColoreScelto() {
        return coloreScelto;
    }

    public void setColoreScelto(int coloreScelto) {
        this.coloreScelto = coloreScelto;
    }

    public String getNomeScelto() {
        return nomeScelto;
    }

    public void setNomeScelto(String nomeScelto) {
        this.nomeScelto = nomeScelto;
    }

    protected int coloreScelto = 1;
    protected String nomeScelto="Etichetta";

    @FXML
    void scegliColore(ActionEvent event) {
        if(event.getSource() instanceof MenuItem)
            {
                coloreEtichetta.setText(((MenuItem) event.getSource()).getText());
                coloreScelto = coloriMap.get(((MenuItem) event.getSource()).getText());
            }
    }

    @FXML
    void conferma(ActionEvent event) {
        if(nomeEtichetta.getText()!=null)
            nomeScelto = nomeEtichetta.getText();

        if(calendario!=null)
            calendario.aggiungiEtichetta(nomeScelto,coloreScelto);

        chiudi(event);
    }

}
