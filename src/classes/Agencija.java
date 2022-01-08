package classes;

import java.util.List;

public class Agencija {

    private String naziv;
    private String adresa;
    private List<Zaposleni> listaZaposlenih;
    private List<Ponuda> listaPonuda;

    public Agencija() {
    }

    public Agencija(String naziv, String adresa, List<Ponuda> listaPonuda) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.listaPonuda = listaPonuda;
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

    @Override
    public String toString() {
        return "Agencija{" +
                "naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", listaPonuda=" + listaPonuda +
                '}';
    }
}
