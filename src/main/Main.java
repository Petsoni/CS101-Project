package main;

import classes.*;
import enums.VrstaPonude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static classes.Agencija.findAgencyByName;
import static classes.Korsnik.findUser;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Korsnik> korisnici = new ArrayList<>();
        List<Ponuda> ponude = new ArrayList<>();
        List<Agencija> agencije = new ArrayList<>();
        List<Zaposleni> zaposleni = new ArrayList<>();

        Filer ponudeFiler = new Filer("ponude.csv");
        Filer korisniciFiler = new Filer("korisnici.csv");
        Filer agencijaFiler = new Filer("agencija.csv");
        Filer zaposleniFiler = new Filer("zaposleni.csv");

        ponudeFiler.read((params) -> {
            double cena = Double.parseDouble(params[1]);
            LocalDate datum = LocalDate.parse(params[3]);
            int brojNocenja = Integer.parseInt(params[4]);
            VrstaPonude vrstaPonude = VrstaPonude.valueOf(params[6]);
            ponude.add(new Ponuda(params[0], cena, params[2], datum, brojNocenja, params[5], vrstaPonude));
        });

        agencijaFiler.read((params) -> {
            Agencija agencija1 = new Agencija(params[0], params[1]);
            agencije.add(agencija1);
        });

        zaposleniFiler.read((params) -> {
            Zaposleni zaposleni1 = new Zaposleni(params[0], params[1], params[2]);
            zaposleni.add(zaposleni1);
            Agencija agencija = findAgencyByName(agencije, zaposleni1.getAgencijaKojojPripada());
            if (agencija != null) {
                agencija.getListaZaposlenih().add(zaposleni1);
            }
        });

        korisniciFiler.read((params) -> {
            Korsnik korsnik1 = new Korsnik(params[0], params[1], params[2], params[3]);
            korisnici.add(korsnik1);
        });



        //Korisnik unosi username i password kako bi se ulogovao i mogao da pregleda ponude
        System.out.print("Unesite korisnicko ime: ");
        String username = sc.next();

        System.out.print("Unesite lozinku: ");
        String password = sc.next();

        System.out.println();
        System.out.println("DOBRODOSLI");
        System.out.println();

        //Korisnik se pretrazuje po username-u i password-u u file-u korisnici.csv
        Korsnik korsnik = findUser(korisnici, username, password);

        System.out.print("Unesite maksimalnu cenu koju zelite: ");
        double maxCena = sc.nextDouble();


        List<Ponuda> isfiltriranePonude = Ponuda.findPonudaByPricepoint(ponude, maxCena);

        for (int i = 0; i < isfiltriranePonude.size(); i++) {
            Ponuda p = isfiltriranePonude.get(i);
            System.out.printf("%d. %s %.2f %s %s %d %s \n", i + 1, p.getNaziv(), p.getCena(), p.getLokacija(),
                    p.getDatum(), p.getBrojNocenja(), p.getImeAgencije());

        }

        System.out.print("Unesite redni broj ponude koju zelite: ");
        int izbor = sc.nextInt();
        Ponuda izabranaPonuda = isfiltriranePonude.get(izbor - 1);
        System.out.println();

        System.out.println("Ponuda koju ste izabrali: " + "\n" + izabranaPonuda.toString());
//        System.out.println(agencije);

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
