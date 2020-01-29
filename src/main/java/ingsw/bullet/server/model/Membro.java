package ingsw.bullet.server.model;

public class Membro {

    private String email;
    private  int id_gruppo;
    private boolean admin;

    public Membro() {
    }

    public Membro(String email, int id_gruppo, boolean admin) {
        this.email = email;
        this.id_gruppo = id_gruppo;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Membro{" +
                "email='" + email + '\'' +
                ", id_gruppo=" + id_gruppo +
                ", admin=" + admin +
                '}';
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getId_gruppo() {return id_gruppo;}

    public void setId_gruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public boolean isAdmin() {return admin;}

    public void setAdmin(boolean admin) {this.admin = admin;}
}
