package classes;

import exceptions.InvalidUsernameException;
import interfaces.CsvRow;

import java.util.List;

public class Korsnik extends Osoba implements CsvRow {

    private String username;
    private String password;

    public Korsnik() {
    }

    public Korsnik(String ime, String prezime, String username, String password) {
        super(ime, prezime);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static Korsnik findUser(List<Korsnik> korsniks, String username, String password) {
        for (Korsnik korsnik : korsniks) {
            if (korsnik.getUsername().equals(username) && korsnik.getPassword().equals(password)) {
                return korsnik;
            }
        }
        throw new InvalidUsernameException("Korisnik ne postoji: " + username);
    }


    @Override
    public String toString() {
        return "Korsnik{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //Opisuje kako klasa izgleda u .csv fajlu
    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s", getIme(), getPrezime(), getUsername(), getPassword());
    }
}
