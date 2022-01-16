package classes;

import interfaces.CsvRow;

import java.util.ArrayList;
import java.util.List;

/*Korisnik se uloguje, bira agenciju, izlistava sve ponude, filtriranje po lokaciji ili po ceni (manje vece od prosledjene),
 odabir ponude*/

public class Agencija implements CsvRow {

    private String naziv;
    private String adresa;
    private List<Zaposleni> listaZaposlenih = new ArrayList<>();
    private List<Ponuda> listaPonuda = new ArrayList<>();

    public Agencija() {
    }

    public Agencija(String naziv, String adresa) {
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Ponuda> getListaPonuda() {
        return listaPonuda;
    }

    public void setListaPonuda(List<Ponuda> listaPonuda) {
        this.listaPonuda = listaPonuda;
    }

    public List<Zaposleni> getListaZaposlenih() {
        return listaZaposlenih;
    }

    public void setListaZaposlenih(List<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }

    public static Agencija findAgencyByName(List<Agencija> agencijas, String imeAgencije) {
        for (Agencija agencija : agencijas) {
            if (agencija.getNaziv().equals(imeAgencije)) {
                return agencija;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Agencija{" +
                "naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", listaZaposlenih=" + listaZaposlenih +
                ", listaPonuda=" + listaPonuda +
                '}';
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s", getNaziv(), getAdresa());
    }
}
