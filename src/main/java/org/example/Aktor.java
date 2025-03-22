package org.example;

import java.util.Date;

public record Aktor(int id, String imie, String nazwisko, String plec, Date dataUrodzenia) {

    @Override
    public String toString() {
        return id + ". " + imie + " " + nazwisko + " (" + plec + ", " + dataUrodzenia + ")";
    }
}