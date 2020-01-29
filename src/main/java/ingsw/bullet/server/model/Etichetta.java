package ingsw.bullet.server.model;

public class Etichetta {

    private int id_etichetta;
    private int colore;
    private String nome;

    public Etichetta() {
    }

    public Etichetta(int id_etichetta, int colore, String nome) {
        this.id_etichetta = id_etichetta;
        this.colore = colore;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Etichetta{" +
                "id_etichetta=" + id_etichetta +
                ", colore=" + colore +
                ", nome='" + nome + '\'' +
                '}';
    }

    public int getId_etichetta() {return id_etichetta;}

    public void setId_etichetta(int id_etichetta) {this.id_etichetta = id_etichetta;}

    public int getColore() {return colore;}

    public void setColore(int colore) {this.colore = colore;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
