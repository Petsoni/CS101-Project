package main;

import classes.*;
import enums.VrstaPonude;
import enums.VrstaPrevoza;
import util.NameValidation;

import java.time.LocalDate;
import java.util.*;

import static classes.Agencija.findAgencyByName;
import static classes.Korisnik.findUser;

public class Main {

    //napraviti metodu za vracanje zaposlenih koji pripadaju odabranoj ponudi po agenciji

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Korisnik> korisnici = new ArrayList<>();
        List<Korisnik> noviKorisnici = new ArrayList<>();
        List<Ponuda> ponude = new ArrayList<>();
        List<Ponuda> izabranePonude = new ArrayList<>();
        List<Agencija> agencije = new ArrayList<>();
        List<Zaposleni> zaposleni = new ArrayList<>();

        Filer ponudeFiler = new Filer("ponude.csv");
        Filer korisniciFiler = new Filer("korisnici.csv");
        Filer agencijaFiler = new Filer("agencija.csv");
        Filer zaposleniFiler = new Filer("zaposleni.csv");
        Filer zabelezenoFiler = new Filer("zabelezeno.csv");

        //iscitavanje iz fajlova navedenih gore
        ponudeFiler.read((params) -> {
            double cena = Double.parseDouble(params[1]);
            LocalDate datum = LocalDate.parse(params[3]);
            int brojNocenja = Integer.parseInt(params[4]);
            VrstaPonude vrstaPonude = VrstaPonude.valueOf(params[6]);
            VrstaPrevoza vrstaPrevoza = VrstaPrevoza.valueOf(params[7]);
            ponude.add(new Ponuda(params[0], cena, params[2], datum, brojNocenja, params[5], vrstaPonude, vrstaPrevoza));
        });

        zabelezenoFiler.read((params) -> {
            double cena = Double.parseDouble(params[1]);
            LocalDate datum = LocalDate.parse(params[3]);
            int brojNocenja = Integer.parseInt(params[4]);
            VrstaPonude vrstaPonude = VrstaPonude.valueOf(params[6]);
            VrstaPrevoza vrstaPrevoza = VrstaPrevoza.valueOf(params[7]);
            izabranePonude.add(new Ponuda(params[0], cena, params[2], datum, brojNocenja, params[5], vrstaPonude, vrstaPrevoza));
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

        System.out.println("Da li imate nalog ili ne?");
        String login = sc.next().toLowerCase(Locale.ROOT);

        //Dodavanje novog korisnika
        if (login.equals("da")){
            System.out.println();
        }else if (login.equals("ne")){
            Korisnik noviKorisnik = new Korisnik();

            System.out.print("Unesite Vase ime: ");
            noviKorisnik.setIme(sc.next());
            NameValidation.isWord(noviKorisnik.getIme());

            System.out.print("Unesite Vase prezime: ");
            noviKorisnik.setPrezime(sc.next());
            NameValidation.isWord(noviKorisnik.getPrezime());

            System.out.print("Unesite korisnicko ime: ");
            noviKorisnik.setUsername(sc.next());

            System.out.println("Unesite lozinku");
            noviKorisnik.setPassword(sc.next());
            noviKorisnici.add(noviKorisnik);

            korisniciFiler.write(noviKorisnici);

            System.out.println("Uspesno ste napravili nalog!");
            System.out.println();
        }

        korisniciFiler.read((params) -> {
            Korisnik korsnik1 = new Korisnik(params[0], params[1], params[2], params[3]);
            korisnici.add(korsnik1);
        });

        //---------------------------------------------------------------------------------//

        //Korisnik unosi username i password kako bi se ulogovao i mogao da pregleda ponude
        System.out.print("Unesite korisnicko ime: ");
        String username = sc.next();

        System.out.print("Unesite lozinku: ");
        String password = sc.next();

        //Korisnik se pretrazuje po username-u i password-u u file-u korisnici.csv
        Korisnik korisnik = findUser(korisnici, username, password);

        System.out.println("----------");
        System.out.println("DOBRODOSLI");
        System.out.println("----------");


        //Korisnik bira maksimalnu cenu ponude koju trazi
        System.out.print("Unesite maksimalnu cenu koju zelite: ");
        double maxCena = sc.nextDouble();

        System.out.println();
        List<Ponuda> filtriranePonude = Ponuda.findPonudaByPricepoint(ponude, maxCena);


        for (int i = 0; i < filtriranePonude.size(); i++) {
            Ponuda p = filtriranePonude.get(i);
            System.out.printf("%d. | %s | %.0f | %s | %s | %d | %s | %s \n", i + 1, p.getNaziv(), p.getCena(), p.getLokacija(),
                    p.getDatum(), p.getBrojNocenja(), p.getImeAgencije(), p.getVrstaPrevoza());

        }
        System.out.println();

        //Korisniku se izlistavaju sve ponude koje odgovaraju njegovoj unetoj ceni
        System.out.print("Unesite redni broj ponude koju zelite: "); int izbor = sc.nextInt(); Ponuda izabranaPonuda = filtriranePonude.get(izbor - 1);
        //Korisniku se pojavljuje ponuda koju je izabrao i izracunava mu se cena na osnovu vrste ponude
        System.out.println("Ponuda koju ste izabrali: " + "\n" + izabranaPonuda.toString());
        System.out.println();
        System.out.println("Cena vase izabrane ponude je: " + izabranaPonuda.racunanjeCenePonude(izabranaPonuda.getVrstaPonude()) +
                " RSD, jer je vrsta ponude " + izabranaPonuda.getVrstaPonude());
        System.out.println();

        List<Zaposleni> zaposleniUAgenicjama = Zaposleni.findZaposleniByAgency(zaposleni, izabranaPonuda);

        //Korisniku se prikazuje ponuda i radnici iz te agencije koji ce pomoci za kupovinu
        System.out.println("Radnici koji ce Vam pomoci za odabranu ponudu u agenciji: " + izabranaPonuda.getImeAgencije());

        for (Zaposleni zaposleni2 : zaposleniUAgenicjama){
            System.out.println(zaposleni2.getIme() + " " + zaposleni2.getPrezime());
        }
        System.out.println();

        //Korisnik bira da li zeli da potvrdi rezervaciju ili ne
        System.out.println("Zabeleziti rezervaciju? (Da, Ne)");
        String odgovor = sc.next().toLowerCase(Locale.ROOT);
        System.out.println();

        //Na osnovu odgovora se ponuda belezi u zabelezeno.csv fajlu
        if (odgovor.equals("ne")){
            System.out.println("Rezervacija otkazana!");
        }else if (odgovor.equals("da")){
            izabranePonude.add(izabranaPonuda);
            zabelezenoFiler.write(izabranePonude);
            System.out.println("Uspesno ste rezervisali ponudu!");
        }
        //Zavrsetak programa
    }
}
