package classes;

import enums.VrstaPonude;
import exceptions.PriceToLowException;
import interfaces.AgencyCalculator;
import interfaces.CsvRow;

import java.time.LocalDate;

public class Ponuda implements AgencyCalculator, CsvRow {

    private String naziv;
    private double cena;
    private String lokacija;
    private LocalDate datum;
    private int brojNocenja;

    public Ponuda() {
    }

    public Ponuda(String naziv, double cena, String lokacija, LocalDate datum, int brojNocenja) {
        this.naziv = naziv;
        setCena(cena);
        this.lokacija = lokacija;
        this.datum = datum;
        this.brojNocenja = brojNocenja;
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
        if (cena <= 10){
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

    public void setLocalDate(LocalDate datum) {
        this.datum = datum;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public double racunanjeCenePonude (VrstaPonude vrstaPonude){
        if (vrstaPonude == VrstaPonude.FIRST_MINUTE){
            setCena(this.cena * 0.8);
        }else if(vrstaPonude == VrstaPonude.LAST_MINUTE){
            setCena(this.cena * 0.5);
        }
        return cena;
    }



    @Override
    public String toString() {
        return "Ponuda{" +
                "naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", lokacija='" + lokacija + '\'' +
                ", datum=" + datum +
                ", brojNocenja=" + brojNocenja +
                '}';
    }

    @Override
    public boolean proveraDatumaPonude() {
        return LocalDate.now().isBefore(datum);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%.2f,%s,%s,%d", getNaziv(), getCena(), getLokacija(), getDatum(), getBrojNocenja());
    }
}
