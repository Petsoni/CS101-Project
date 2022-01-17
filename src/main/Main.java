package main;

import classes.*;
import enums.VrstaPonude;
import enums.VrstaPrevoza;

import java.time.LocalDate;
import java.util.*;

import static classes.Agencija.findAgencyByName;
import static classes.Korsnik.findUser;

public class Main {

    //napraviti metodu za vracanje zaposlenih koji pripadaju odabranoj ponudi po agenciji

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

        //Korisnik se pretrazuje po username-u i password-u u file-u korisnici.csv
        Korsnik korsnik = findUser(korisnici, username, password);

        System.out.println("----------");
        System.out.println("DOBRODOSLI");
        System.out.println("----------");


        //Korisnik bira maksimalnu cenu ponude koju trazi
        System.out.print("Unesite maksimalnu cenu koju zelite: ");
        double maxCena = sc.nextDouble();


        List<Ponuda> filtriranePonude = Ponuda.findPonudaByPricepoint(ponude, maxCena);

        System.out.println();
        for (int i = 0; i < filtriranePonude.size(); i++) {
            Ponuda p = filtriranePonude.get(i);
            System.out.printf("%d. %s %.2f %s %s %d %s %s \n", i + 1, p.getNaziv(), p.getCena(), p.getLokacija(),
                    p.getDatum(), p.getBrojNocenja(), p.getImeAgencije(), p.getVrstaPrevoza());

        }
        System.out.println();

        //Korisniku se izlistavaju sve ponude koje odgovaraju njegovoj unetoj ceni
        System.out.print("Unesite redni broj ponude koju zelite: ");
        int izbor = sc.nextInt();
        Ponuda izabranaPonuda = filtriranePonude.get(izbor - 1);

        //Korisniku se pojavljuje ponuda koju je izabrao i izracunava mu se cena na osnovu vrste ponude
        System.out.println("Ponuda koju ste izabrali: " + "\n" + izabranaPonuda.toString());
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

        //Na osnovu odgovora se ponuda belezi u zabelezeno.csv falju
        if (odgovor.equals("ne")){
            System.out.println("Rezervacija otkazana!");
        }else if (odgovor.equals("da")){
            zabelezenoFiler.write(Collections.singletonList(izabranaPonuda));
            System.out.println("Uspesno ste rezervisali ponudu!");
        }

        //Zavrsetak programa

//        System.out.println(agencije);
//        korisnici.add(new Korsnik("Petar", "Duckovic", "Petar23", "pera123"));


    }
}
