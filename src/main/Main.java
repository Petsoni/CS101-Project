package main;

import classes.Filer;
import classes.Korsnik;
import classes.Ponuda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Korsnik> korisnici = new ArrayList<>();
        List<Ponuda> ponude = new ArrayList<>();

        Filer korisniciFiler = new Filer("korisnici.csv");
        korisniciFiler.read((params) -> {
//            System.out.println(Arrays.toString(params));
            Korsnik korsnik1 = new Korsnik(params[0], params[1], params[2], params[3]);
            korisnici.add(korsnik1);
        });

        Filer ponudeFiler = new Filer("ponude.csv");
        ponudeFiler.read((params) -> {
            System.out.println(Arrays.toString(params));
            double cena = Double.parseDouble(params[1]);
            LocalDate datum = LocalDate.parse(params[3]);
            int brojNocenja = Integer.parseInt(params[4]);
            ponude.add(new Ponuda(params[0], cena, params[2], datum, brojNocenja));
        });

//        korisnici.add(new Korsnik("Petar", "Duckovic", "Petar23", "pera123"));
        System.out.println("Unesite vase ime, prezime, username i password: ");
        korisnici.add(new Korsnik(sc.next(), sc.next(), sc.next(), sc.next()));

        System.out.println("Ponude koje se daju: ");
        ponude.add(new Ponuda("DubaiHaos", 100000f, "Dubai" ,LocalDate.now(), 7));
        ponude.add(new Ponuda("IstanbulHaos", 60000f, "Istanbul" ,LocalDate.now(), 5));

        System.out.println(ponude);
//        System.out.println(korisnici);

        ponudeFiler.write(ponude);
        korisniciFiler.write(korisnici);
    }
}
