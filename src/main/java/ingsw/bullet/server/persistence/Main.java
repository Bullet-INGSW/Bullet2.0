package ingsw.bullet.server.persistence;

public class Main {

    public static void main(String[] args) {
        System.out.println(DBManager.getInstance().findUserByPrimaryKey("a@a.a").toString());
    }
}
