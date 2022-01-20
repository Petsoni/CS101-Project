package classes;

import exceptions.InvalidUsernameException;
import interfaces.CsvRow;

import java.util.List;

public class Korisnik extends Osoba implements CsvRow {

    private String username;
    private String password;

    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String username, String password) {
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


    public static Korisnik findUser(List<Korisnik> korsniks, String username, String password) {
        for (Korisnik korisnik : korsniks) {
            if (korisnik.getUsername().equals(username) && korisnik.getPassword().equals(password)) {
                return korisnik;
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
