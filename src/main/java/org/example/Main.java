package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/main/resources/Gry.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            List<Technologia> technologie = new ArrayList<>();
            NodeList listaTechnologii = document.getElementsByTagName("technologia");

            IntStream.range(0, listaTechnologii.getLength()).mapToObj(i -> (Element) listaTechnologii.item(i)).forEach(element -> {
                String nazwa = element.getElementsByTagName("nazwa").item(0).getTextContent();
                String typ = element.getElementsByTagName("typ").item(0).getTextContent();
                technologie.add(new Technologia(nazwa, typ));
            });

            List<Gra> gry = new ArrayList<>();
            NodeList listaGier = document.getElementsByTagName("gra");

            for (int i = 0; i < listaGier.getLength(); i++) {
                Element element = (Element) listaGier.item(i);

                String nazwa = element.getElementsByTagName("nazwa").item(0).getTextContent();
                int rokPremiery = Integer.parseInt(element.getElementsByTagName("rok_premiery").item(0).getTextContent());

                List<String> gatunki;
                NodeList listaGatunkow = element.getElementsByTagName("gatunek");
                gatunki = IntStream.range(0, listaGatunkow.getLength()).mapToObj(j -> listaGatunkow.item(j).getTextContent()).collect(Collectors.toList());

                List<Technologia> technologieUzywane;
                NodeList listaTechnologiiUzywane = element.getElementsByTagName("technologia");
                technologieUzywane = IntStream.range(0, listaTechnologiiUzywane.getLength()).filter(j -> j < technologie.size()).mapToObj(technologie::get).collect(Collectors.toList());

                gry.add(new Gra(nazwa, rokPremiery, gatunki, technologieUzywane));
            }

            gry.forEach(gra -> {
                System.out.println(gra);
                System.out.println("------------");
            });

        } catch (Exception e) {
            System.out.println("Błąd: " + e);
        }
    }
}
