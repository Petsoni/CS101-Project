package classes;

import enums.VrstaPonude;
import enums.VrstaPrevoza;
import exceptions.PriceToLowException;
import interfaces.AgencyCalculator;
import interfaces.CsvRow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ponuda implements AgencyCalculator, CsvRow {

    private String naziv;
    private double cena;
    private String lokacija;
    private LocalDate datum;
    private int brojNocenja;
    private String imeAgencije;
    private VrstaPonude vrstaPonude;
    private VrstaPrevoza vrstaPrevoza;

    public Ponuda() {
    }

    public Ponuda(String naziv, double cena, String lokacija, LocalDate datum, int brojNocenja, String imeAgencije,
                  VrstaPonude vrstaPonude, VrstaPrevoza vrstaPrevoza) {
        this.naziv = naziv;
        setCena(cena);
        this.lokacija = lokacija;
        this.datum = datum;
        this.brojNocenja = brojNocenja;
        this.imeAgencije = imeAgencije;
        this.vrstaPonude = vrstaPonude;
        this.vrstaPrevoza = vrstaPrevoza;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        if (cena <= 0) {
            throw new PriceToLowException("Cena nije validna!!!");
        }
        this.cena = cena;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public String getImeAgencije() {
        return imeAgencije;
    }

    public void setImeAgencije(String imeAgencije) {
        this.imeAgencije = imeAgencije;
    }

    public VrstaPonude getVrstaPonude() {
        return vrstaPonude;
    }

    public void setVrstaPonude(VrstaPonude vrstaPonude) {
        this.vrstaPonude = vrstaPonude;
    }

    public VrstaPrevoza getVrstaPrevoza() {
        return vrstaPrevoza;
    }

    public void setVrstaPrevoza(VrstaPrevoza vrstaPrevoza) {
        this.vrstaPrevoza = vrstaPrevoza;
    }

    public double racunanjeCenePonude(VrstaPonude vrstaPonude) {
        if (vrstaPonude == VrstaPonude.FIRST_MINUTE) {
            setCena(this.cena * 0.8);
        } else if (vrstaPonude == VrstaPonude.LAST_MINUTE) {
            setCena(this.cena * 0.5);
        }
        return cena;
    }

    public static Ponuda findPonudaByName(List<Ponuda> ponudas, String imePonude) {
        for (Ponuda ponuda : ponudas){
            if (ponuda.getNaziv().equals(imePonude)){
                return ponuda;
            }
        }
        return null;
    }

    public static List<Ponuda> findPonudaByPricepoint(List<Ponuda> ponudas, double maxFilter){
        List<Ponuda> ponudaList = new ArrayList<>();
        for (Ponuda ponuda : ponudas){
            if (ponuda.getCena() <= maxFilter){
                ponudaList.add(ponuda);
            }
        }
        return ponudaList;
    }

    @Override
    public String toString() {
        return naziv + " " +
                ", cena = " + cena +
                " RSD, lokacija = " + lokacija + ", datum: " + datum +
                ", brojNocenja: " + brojNocenja +
                ", agencije sa ovom ponudom = " + imeAgencije + " " + ", vrsta ponude = " +
                vrstaPonude;
    }

    @Override
    public boolean proveraDatumaPonude() {
        return LocalDate.now().isBefore(datum);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%.2f,%s,%s,%d,%s,%s", getNaziv(), getCena(), getLokacija(), getDatum(),
                getBrojNocenja(), getImeAgencije(), getVrstaPrevoza());
    }
}
