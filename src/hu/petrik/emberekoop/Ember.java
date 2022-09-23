package hu.petrik.emberekoop;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Ember {
    private String nev;
    private String szulDatum;
    private String szulHely;

    public Ember(String nev, String szulDatum, String szulHely) {
        this.nev = nev;
        this.szulDatum = szulDatum;
        this.szulHely = szulHely;
    }

    public int getSzuletesiEv() {
        return Integer.parseInt(this.szulDatum.substring(0, 4));
    }

    public int getSzuletesiHonap() {
        String[] szuletesiAdatok = this.szulDatum.split("-");
        return Integer.parseInt(szuletesiAdatok[1]);
    }

    public int getSzuletesiNap() {
        String[] szuletesiAdatok = this.szulDatum.split("-");
        return Integer.parseInt(szuletesiAdatok[2]);
    }

    public int getEletkor() {
        LocalDate maiDatum = LocalDate.now();
        boolean voltEIdenSzuletesnapja = maiDatum.getMonthValue() > getSzuletesiHonap()
                || (maiDatum.getMonthValue() == getSzuletesiHonap() &&
                maiDatum.getDayOfMonth() >= getSzuletesiNap());
        int eletkor = LocalDate.now().getYear() - this.getSzuletesiEv();
        if (!voltEIdenSzuletesnapja) {
            eletkor--;
        }
        return eletkor;
    }

    public int getEletkor2() {
        LocalDate maiDatum = LocalDate.now();
        LocalDate szuletesiDatum = LocalDate.of(getSzuletesiEv(), getSzuletesiHonap(), getSzuletesiNap());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        szuletesiDatum = LocalDate.parse(this.szulDatum, formatter);
        Period eletkor = Period.between(szuletesiDatum, maiDatum);
        return  eletkor.getYears();
    }

    @Override
    public String toString() {
        return String.format("%30s %10s (%3d év) %20s", this.nev, this.szulDatum,
                this.getEletkor2(), this.szulHely);
    }
}
