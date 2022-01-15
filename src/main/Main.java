package main;

import classes.*;
import enums.VrstaPonude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Korsnik> korisnici = new ArrayList<>();
        List<Ponuda> ponude = new ArrayList<>();
        List<Agencija> agencije = new ArrayList<>();
        List<Zaposleni> zaposleni = new ArrayList<>();

        Filer ponudeFiler = new Filer("ponude.csv");
        Filer korisniciFiler = new Filer("korisnici.csv");
        Filer agencijaFiler = new Filer("agencija.csv");
        Filer zaposleniFiler = new Filer("zaposleni.csv");

        ponudeFiler.read((params) -> {
            System.out.println(Arrays.toString(params));
            double cena = Double.parseDouble(params[1]);
            LocalDate datum = LocalDate.parse(params[3]);
            int brojNocenja = Integer.parseInt(params[4]);
            VrstaPonude vrstaPonude = VrstaPonude.valueOf(params[6]);
            ponude.add(new Ponuda(params[0], cena, params[2], datum, brojNocenja, params[5], vrstaPonude));
        });

        korisniciFiler.read((params) -> {
            Korsnik korsnik1 = new Korsnik(params[0], params[1], params[2], params[3]);
            korisnici.add(korsnik1);
        });

        agencijaFiler.read((params) -> {

        });

//        korisnici.add(new Korsnik("Petar", "Duckovic", "Petar23", "pera123"));

        //Dodavanje username i passworda jedno ispod drugog
//        System.out.println("Unesite vase ime, prezime, username i password: ");
//        korisnici.add(new Korsnik(sc.next(), sc.next(), sc.next(), sc.next()));
//
//        System.out.println("Ponude koje se daju: ");
//        ponude.add(new Ponuda("DubaiHaos", 100000f, "Dubai" ,LocalDate.now(), 7));
//        ponude.add(new Ponuda("IstanbulHaos", 60000f, "Istanbul" ,LocalDate.now(), 5));
//
//      //Dodati citanje iz fajla za ponude!!!
//        System.out.println(ponude);
//        System.out.println(korisnici);
//
//        ponudeFiler.write(ponude);
//        korisniciFiler.write(korisnici);


    }
}
