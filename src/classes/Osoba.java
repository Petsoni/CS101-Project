package classes;

import exceptions.InvalidNameException;
import util.NameValidation;

import java.util.regex.Pattern;

public abstract class Osoba {

    private String ime;
    private String prezime;

    protected Osoba() {
    }

    protected Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {

        if (NameValidation.isWord(ime)) {
            this.ime = ime;
        } else {
            throw new InvalidNameException("NENE");
        }

    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
