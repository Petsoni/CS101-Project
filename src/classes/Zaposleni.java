package classes;

import exceptions.PaycheckException;

public class Zaposleni extends Osoba {

    private double plata;
    private boolean naBolovanju;
    private int brojSlobodnihDana;

    private Zaposleni() {
    }

    public Zaposleni(String ime, String prezime, double plata, boolean naBolovanju, int brojSlobodnihDana) {
        super(ime, prezime);
        this.plata = 75000;
        this.naBolovanju = naBolovanju;
        this.brojSlobodnihDana = brojSlobodnihDana;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        if (plata <= 0) {
            throw new PaycheckException("Plata nije validna!");
        }
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
        if (brojSlobodnihDana > 20) {
            this.plata *= 0.5;
        } else if (brojSlobodnihDana >= 10) {
            this.plata *= 0.75;
        } else if (brojSlobodnihDana > 5) {
            this.plata *= 0.9;
        }
        this.brojSlobodnihDana = brojSlobodnihDana;
    }

    public double visinaPlateNaBolovanju() {
        if (this.naBolovanju) {
            this.plata *= 0.75;
        }
        return plata;
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
