package org.example;

public class Aktor {
    int id;
    String imie;
    String nazwisko;
    String plec;
    String dataUrodzenia;

    public Aktor(int id, String imie, String nazwisko, String plec, String dataUrodzenia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.dataUrodzenia = dataUrodzenia;
    }

    @Override
    public String toString() {
        return id + ". " + imie + " " + nazwisko + " (" + plec + ", " + dataUrodzenia + ")";
    }
}