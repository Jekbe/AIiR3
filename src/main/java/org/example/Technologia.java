package org.example;

public record Technologia(String nazwa, String typ) {
    @Override
    public String toString() {
        return nazwa + " (" + typ + ")";
    }
}
