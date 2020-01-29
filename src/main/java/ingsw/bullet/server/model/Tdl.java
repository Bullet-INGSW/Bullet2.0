package ingsw.bullet.server.model;

public class Tdl {

    private int id_tdl;
    private int id_gruppo;
    private String email;
    private String nome;

    public Tdl() {
    }

    public Tdl(int id_tdl, int id_gruppo, String email, String nome) {
        this.id_tdl = id_tdl;
        this.id_gruppo = id_gruppo;
        this.email = email;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Tdl{" +
                "id_tdl=" + id_tdl +
                ", id_gruppo=" + id_gruppo +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

    public int getId_tdl() {return id_tdl;}

    public void setId_tdl(int id_tdl) {this.id_tdl = id_tdl;}

    public int getId_gruppo() {return id_gruppo;}

    public void setId_gruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
