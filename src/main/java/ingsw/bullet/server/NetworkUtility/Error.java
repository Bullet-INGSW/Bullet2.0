package ingsw.bullet.server.NetworkUtility;

public class Error {
    public enum ErrorType{ Nessuno, UserNotExist, WrongPassword   }

    ErrorType errore=null;

    public Error(ErrorType errore) {
        this.errore = errore;
    }
}
