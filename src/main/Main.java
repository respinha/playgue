package main;

import common.Specification;
import common.WorldTimer;
import entities.Bacteria;
import entities.BiologicalEntity;
import entities.LiveEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import region.MedicalInformationCenter;
import region.laboratory.BacteriaLaboratory;
import region.laboratory.Laboratory;
import region.laboratory.MedicalLaboratory;
import region.worldregion.Continent;
import region.worldregion.Country;
import region.worldregion.MutableCountry;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rui on 1/25/17.
 */
public class Main {

    private static int N_BACTERIAS = 5;

    public static void main(String[] args) throws Exception {

        buildCountries("europe");
        Continent continent = new Continent("countries_parsed.xml");
        Laboratory bLab = new BacteriaLaboratory(continent);
        Laboratory mLab = new MedicalLaboratory(continent);

        Laboratory[] laboratories = new Laboratory[2];
        laboratories[0] = bLab;
        laboratories[1] = mLab;

        MedicalInformationCenter center = new MedicalInformationCenter(null);
        WorldTimer timerTask = new WorldTimer(continent,center,laboratories);
        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(timerTask, 0, 5*1000);



        Bacteria[] bacterias = new Bacteria[N_BACTERIAS];
        Thread[] bacThreads = new Thread[N_BACTERIAS];


        for (int i = 0; i < N_BACTERIAS; i++) {

            bacterias[i] = new Bacteria("E. Coli", continent, null, bLab);
            bacThreads[i] = new Thread(bacterias[i]);

            bacThreads[i].start();
        }

        for(int i = 0; i < N_BACTERIAS; i++) {
            bacThreads[i].join();
        }

        timer.cancel();

        int total = 0;
        for(MutableCountry country: continent.getMutableCountries()) {

            if(country.isInfected()) {
                total += country.getInfectedPopulation();
                double percent = country.getInfectedPopulation() * 100 / country.getPopulation();
                System.out.println(country.getName() + ": " + percent);
            }

            // TODO: INFECTED POPULATION IS ALWAYS ZERO
        }

        System.out.println(total);
    }

    public static void buildCountries(String continent) throws Exception {

        String path = "countries.xml";

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path));
        Document newDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = newDoc.createElement("countries");

        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expression = xPath.compile("/countries//country");

        //Set<String> subregions = new HashSet<>();

        NodeList countries = (NodeList) expression.evaluate(document, XPathConstants.NODESET);


        HashMap<String, Integer> populationMap = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(new File("population.csv")));

        String line;
        Pattern p = Pattern.compile("\".*?\"");
        while((line = br.readLine()) != null) {

            Matcher m = p.matcher(line);
            m.find();
            String code = m.group().replaceAll("\"","");

            if(!m.find()) throw  new Exception("BAD PARSING");
            int pop = Integer.parseInt(m.group().replaceAll("\"",""));

            populationMap.put(code,pop);
        }


        for(int i = 0; i < countries.getLength(); i++) {

            Element element = (Element) countries.item(i);
            String region = element.getAttribute("region");

            //System.out.println(region);
            if (region.equalsIgnoreCase(continent)) {
                String countryName = element.getAttribute("name").split(",")[0];
                String capital = element.getAttribute("capital");
                String[] borders = element.getAttribute("borders").split(";");


                String subregion = element.getAttribute("subregion");

                /*System.out.println(countryName);
                System.out.println("Code: " + element.getAttribute("cca3"));
                System.out.println("Region: " + region);
                System.out.println("Sub-region.worldregion: " + subregion);*/

                Element country = newDoc.createElement("country");
                country.setAttribute("name", countryName);
                country.setAttribute("cca3", element.getAttribute("cca3"));
                country.setAttribute("region", region);
                country.setAttribute("subregion", subregion);
                country.setAttribute("capital", capital);
                country.setAttribute("latlng", element.getAttribute("latlng"));

                String lower = subregion.toLowerCase();
                if (lower.contains("europe")) {
                    int temperature, welfare, medical;
                    if (lower.contains("southern")) {
                        temperature = 16;
                        medical = welfare = 7;
                    } else if (lower.contains("eastern")) {
                        temperature = 8;
                        medical = 7;
                        welfare = 6;
                    } else if (lower.contains("western")) {
                        temperature = 10;
                        medical = 8;
                        welfare = 8;
                    } else { // north
                        temperature = 5;
                        medical = 9;
                        welfare = 10;
                    }
                    country.setAttribute("temperature", String.valueOf(temperature));
                    country.setAttribute("welfare", String.valueOf(welfare));
                    country.setAttribute("medical", String.valueOf(medical));
                }

                //System.out.println("Country #" + (i + 1));

                if (!populationMap.containsKey(element.getAttribute("cca3"))) {
                    System.out.println("Country not existent: " + countryName);
                    continue;
                }

                country.setAttribute("population", String.valueOf(populationMap.get(element.getAttribute("cca3"))));

                //subregions.add(subregion);
                if (!borders[0].isEmpty())
                    country.setAttribute("borders", element.getAttribute("borders"));


                root.appendChild(country);



            }
        }

        newDoc.appendChild(root);

        // output to XML file
        TransformerFactory tFactory =
                TransformerFactory.newInstance();
        Transformer transformer =
                tFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(newDoc);
        StreamResult result = new StreamResult(new File("countries_parsed.xml"));
        transformer.transform(source, result);

    }


}
