package classes;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUsernameException;
import interfaces.CsvRow;

import java.util.List;

public class Korsnik extends Osoba implements CsvRow {

    private String username;
    private String password;
    //lista ponuda koje je odabrao
    private List<Ponuda> listaPonuda;

    public Korsnik() {
    }

    public Korsnik(String ime, String prezime, String username, String password, List<Ponuda> listaPonuda) {
        super(ime, prezime);
        this.username = username;
        this.password = password;
        this.listaPonuda = listaPonuda;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (!username.equals("Petar23")){
            throw new InvalidUsernameException("Username je nepostojeci");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(!password.equals("pera123")){
            throw new InvalidPasswordException("Password je nepostojeci!");
        }
        this.password = password;
    }



    @Override
    public String toString() {
        return "Korsnik {" +
                "username = '" + username + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }

    //Opisuje kako klasa izgleda u .csv fajlu
    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s", getIme(), getPrezime(), getUsername(), getPassword());
    }
}
