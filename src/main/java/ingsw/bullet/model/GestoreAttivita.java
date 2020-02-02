package ingsw.bullet.model;

import java.util.List;

public interface GestoreAttivita {
    String getNome();
    void setNome(String descrizione);

    void setEmail(String email);
    String getEmail();

    List<Etichetta> getEtichette();
    void setEtichette(List<Etichetta> etichette);

    int getIdGruppo();
    void setIdGruppo(int id_gruppo);
}
