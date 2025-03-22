package org.example;

import java.util.ArrayList;
import java.util.List;

public record Film(String nazwa, int rokPremiery, List<String> gatunki, List<String> obsada) {

    public Film(String nazwa, int rokPremiery) {
        this(nazwa, rokPremiery, new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Film: " + nazwa + " (" + rokPremiery + ")" + "\nGatunki: " + gatunki + "\nObsada: " + obsada + "\n";
    }
}