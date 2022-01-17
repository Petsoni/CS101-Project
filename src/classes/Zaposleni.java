package classes;

import interfaces.CsvRow;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Zaposleni> findZaposleniByAgency(List<Zaposleni> zaposleni, Ponuda ponuda) {
        List<Zaposleni> zaposleniList = new ArrayList<>();
        for (Zaposleni zaposleni1 : zaposleni) {
            if (zaposleni1.getAgencijaKojojPripada().equals(ponuda.getImeAgencije())) {
                zaposleniList.add(zaposleni1);
            }
        }
        return zaposleniList;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "agencijaKojojPripada='" + agencijaKojojPripada + '\'' + '}';
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s", getIme(), getPrezime(), getAgencijaKojojPripada());
    }
}
