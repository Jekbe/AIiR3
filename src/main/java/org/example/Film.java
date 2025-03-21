package org.example;

import java.util.ArrayList;
import java.util.List;

public class Film {
    String nazwa;
    int rokPremiery;
    List<String> gatunki = new ArrayList<>();
    List<String> obsada = new ArrayList<>();

    public Film(String nazwa, int rokPremiery) {
        this.nazwa = nazwa;
        this.rokPremiery = rokPremiery;
    }

    @Override
    public String toString() {
        return "Film: " + nazwa + " (" + rokPremiery + ")" + "\nGatunki: " + gatunki + "\nObsada: " + obsada + "\n";
    }
}