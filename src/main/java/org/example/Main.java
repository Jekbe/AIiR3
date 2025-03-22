package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File("C:\\Users\\patry\\IdeaProjects\\AIiR3\\src\\main\\resources\\Filmy.xml"));
            doc.getDocumentElement().normalize();

            List<Aktor> aktorzy = new ArrayList<>();
            NodeList aktorNodes = doc.getElementsByTagName("Aktor");
            IntStream.range(0, aktorNodes.getLength()).mapToObj(i -> (Element) aktorNodes.item(i)).forEach(aktorElement -> {
                int id = Integer.parseInt(aktorElement.getAttribute("Id"));
                String imie = aktorElement.getElementsByTagName("Imie").item(0).getTextContent();
                String nazwisko = aktorElement.getElementsByTagName("Nazwisko").item(0).getTextContent();
                String plec = aktorElement.getElementsByTagName("Plec").item(0).getTextContent();
                Date dataUrodzenia;
                try {
                    dataUrodzenia = new SimpleDateFormat("dd-MM-yyyy").parse(aktorElement.getElementsByTagName("DataUrodzenia").item(0).getTextContent());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                aktorzy.add(new Aktor(id, imie, nazwisko, plec, dataUrodzenia));
            });

            List<Film> filmy = new ArrayList<>();
            NodeList filmNodes = doc.getElementsByTagName("Film");
            IntStream.range(0, filmNodes.getLength()).mapToObj(i -> (Element) filmNodes.item(i)).forEach(filmElement -> {
                String nazwa = filmElement.getElementsByTagName("Nazwa").item(0).getTextContent();
                int rokPremiery = Integer.parseInt(filmElement.getElementsByTagName("RokPremiery").item(0).getTextContent());
                Film film = new Film(nazwa, rokPremiery);
                NodeList gatunkiNodes = filmElement.getElementsByTagName("gatunek");
                IntStream.range(0, gatunkiNodes.getLength()).forEach(j -> film.gatunki().add(gatunkiNodes.item(j).getTextContent()));
                NodeList roleNodes = filmElement.getElementsByTagName("Rola");
                IntStream.range(0, roleNodes.getLength()).mapToObj(j -> (Element) roleNodes.item(j)).map(Node::getTextContent).forEach(rola -> film.obsada().add(rola));
                filmy.add(film);
            });

            System.out.println("Aktorzy:");
            aktorzy.forEach(System.out::println);

            System.out.println("\nFilmy:");
            filmy.forEach(System.out::println);

            LocalDate localDate = LocalDate.now();
            IntStream.range(0, aktorzy.size()).forEach(i -> {
                Element aktor = (Element) aktorNodes.item(i);
                int wiek = Period.between(aktorzy.get(i).dataUrodzenia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), localDate).getYears();
                Element wiekElement = doc.createElement("Wiek");
                wiekElement.appendChild(doc.createTextNode(String.valueOf(wiek)));
                aktor.appendChild(wiekElement);
            });

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File("C:\\Users\\patry\\IdeaProjects\\AIiR3\\src\\main\\resources\\NewFilmy.xml")));
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
