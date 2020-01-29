package ingsw.bullet.server.model;

public class Gruppo {

    private int id_gruppo;
    private String nome;

    public Gruppo() {
    }

    public Gruppo(int id_gruppo, String nome) {
        this.id_gruppo = id_gruppo;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Gruppo{" +
                "id_gruppo=" + id_gruppo +
                ", nome='" + nome + '\'' +
                '}';
    }

    public int getId_gruppo() {return id_gruppo;}

    public void setId_gruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
