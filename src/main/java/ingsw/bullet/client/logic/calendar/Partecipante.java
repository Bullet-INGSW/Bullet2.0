package ingsw.bullet.client.logic.calendar;

public class Partecipante {
    String nome = "";
    boolean partecipa = false;

    public Partecipante(String nome, boolean partecipa) {
        this.nome = nome;
        this.partecipa = partecipa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPartecipa() {
        return partecipa;
    }

    public void setPartecipa(boolean partecipa) {
        this.partecipa = partecipa;
    }
}
