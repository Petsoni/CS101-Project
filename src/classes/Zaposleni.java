package classes;

public class Zaposleni extends Osoba{

    private double plata;
    private boolean naBolovanju;
    private int brojSlobodnihDana;

    public Zaposleni() {
    }

    public Zaposleni(String ime, String prezime, double plata, boolean naBolovanju, int brojSlobodnihDana) {
        super(ime, prezime);
        this.plata = plata;
        this.naBolovanju = naBolovanju;
        this.brojSlobodnihDana = brojSlobodnihDana;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public boolean isNaBolovanju() {
        return naBolovanju;
    }

    public void setNaBolovanju(boolean naBolovanju) {
        this.naBolovanju = naBolovanju;
    }

    public int getBrojSlobodnihDana() {
        return brojSlobodnihDana;
    }

    public void setBrojSlobodnihDana(int brojSlobodnihDana) {
        this.brojSlobodnihDana = brojSlobodnihDana;
    }

    @Override
    public String toString() {
        return "Zaposleni{" +
                "plata=" + plata +
                ", naBolovanju=" + naBolovanju +
                ", brojSlobodnihDana=" + brojSlobodnihDana +
                '}';
    }
}
