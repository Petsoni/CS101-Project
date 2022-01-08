package main;

import classes.Filer;
import classes.Korsnik;
import classes.Ponuda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Korsnik> korisnici = new ArrayList<>();
        List<Ponuda> ponude = new ArrayList<>();

        Filer korisniciFiler = new Filer("korisnici.csv");
        korisniciFiler.read((params) -> {
            System.out.println(Arrays.toString(params));
            Korsnik korsnik1 = new Korsnik(params[0], params[1], params[2], params[3]);
            korisnici.add(korsnik1);
        });

        Filer ponudeFiler = new Filer("ponude.csv");
        ponudeFiler.read((params) -> {
            System.out.println(Arrays.toString(params));
            double cena = Double.parseDouble(params[1]);
            LocalDate datum = LocalDate.parse(params[3]);
            int brojNocenja = Integer.parseInt(params[4]);
            ponude.add(new Ponuda(
                    params[0],
                    cena,
                    params[2],
                    datum,
                    brojNocenja
            ));
        });
        System.out.println(ponude);
        System.out.println(korisnici);

//        korisnici.add(new Korsnik("Duca", "Duckovic", "Dule", "dudule"));
//        ponude.add(new Ponuda("IstanbulOMG", 59999f, "Istanbul" ,LocalDate.now(), 7));

        ponudeFiler.write(ponude);
        korisniciFiler.write(korisnici);
    }
}
