package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("C:\\Users\\patry\\IdeaProjects\\AIiR3\\src\\main\\resources\\Filmy.xml"); // Ścieżka do pliku XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            List<Aktor> aktorzy = new ArrayList<>();
            NodeList aktorNodes = doc.getElementsByTagName("Aktor");
            for (int i = 0; i < aktorNodes.getLength(); i++) {
                Element aktorElement = (Element) aktorNodes.item(i);
                int id = Integer.parseInt(aktorElement.getAttribute("Id"));
                String imie = aktorElement.getElementsByTagName("Imie").item(0).getTextContent();
                String nazwisko = aktorElement.getElementsByTagName("Nazwisko").item(0).getTextContent();
                String plec = aktorElement.getElementsByTagName("Plec").item(0).getTextContent();
                String dataUrodzenia = aktorElement.getElementsByTagName("DataUrodzenia").item(0).getTextContent();
                aktorzy.add(new Aktor(id, imie, nazwisko, plec, dataUrodzenia));
            }

            List<Film> filmy = new ArrayList<>();
            NodeList filmNodes = doc.getElementsByTagName("Film");
            for (int i = 0; i < filmNodes.getLength(); i++) {
                Element filmElement = (Element) filmNodes.item(i);
                String nazwa = filmElement.getElementsByTagName("Nazwa").item(0).getTextContent();
                int rokPremiery = Integer.parseInt(filmElement.getElementsByTagName("RokPremiery").item(0).getTextContent());
                Film film = new Film(nazwa, rokPremiery);

                NodeList gatunkiNodes = filmElement.getElementsByTagName("gatunek");
                for (int j = 0; j < gatunkiNodes.getLength(); j++) {
                    film.gatunki.add(gatunkiNodes.item(j).getTextContent());
                }

                NodeList roleNodes = filmElement.getElementsByTagName("Rola");
                for (int j = 0; j < roleNodes.getLength(); j++) {
                    Element rolaElement = (Element) roleNodes.item(j);
                    String rola = rolaElement.getTextContent();
                    film.obsada.add(rola);
                }

                filmy.add(film);
            }

            // Wypisanie aktorów
            System.out.println("Aktorzy:");
            for (Aktor a : aktorzy) {
                System.out.println(a);
            }

            System.out.println("\nFilmy:");
            for (Film f : filmy) {
                System.out.println(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
