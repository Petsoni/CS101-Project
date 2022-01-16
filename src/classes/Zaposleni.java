package classes;

import interfaces.CsvRow;

public class Zaposleni extends Osoba implements CsvRow {

    private String agencijaKojojPripada;


    public Zaposleni() {
    }

    public Zaposleni(String ime, String prezime, String agencijaKojojPripada) {
        super(ime, prezime);
        this.agencijaKojojPripada = agencijaKojojPripada;
    }

    public String getAgencijaKojojPripada() {
        return agencijaKojojPripada;
    }

    public void setAgencijaKojojPripada(String agencijaKojojPripada) {
        this.agencijaKojojPripada = agencijaKojojPripada;
    }

    @Override
    public String toString() {
        return "Zaposleni{" +
                "agencijaKojojPripada='" + agencijaKojojPripada + '\'' +
                '}';
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s", getIme(), getPrezime(), getAgencijaKojojPripada());
    }
}
