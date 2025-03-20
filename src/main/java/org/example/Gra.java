package org.example;

import java.util.List;

public record Gra(String nazwa, int rokPremiery, List<String> gatunki, List<Technologia> technologie) {
    @Override
    public String toString() {
        return "Gra: " + nazwa + " (" + rokPremiery + ")\nGatunki: " + String.join(", ", gatunki) + "\nTechnologie: " + technologie;
    }
}
