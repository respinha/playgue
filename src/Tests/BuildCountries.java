package Tests;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by espinha on 1/22/17.
 */
public class BuildCountries {

    public static void main(String[] args) throws Exception {

        String path = "countries.xml";

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path));
        Document newDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = newDoc.createElement("countries");

        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expression = xPath.compile("/countries//country");

        Set<String> subregions = new HashSet<>();

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
            String countryName = element.getAttribute("name").split(",")[0];
            String capital = element.getAttribute("capital");
            String[] borders = element.getAttribute("borders").split(";");

            String region = element.getAttribute("worldregion");
            String subregion = element.getAttribute("subregion");

            System.out.println(countryName);
            System.out.println("Code: " + element.getAttribute("cca3"));
            System.out.println("Region: " + region);
            System.out.println("Sub-worldregion: " + subregion);

            Element country = newDoc.createElement("country");
            country.setAttribute("name", countryName);
            country.setAttribute("cca3", element.getAttribute("cca3"));
            country.setAttribute("worldregion", region);
            country.setAttribute("subregion", subregion);
            country.setAttribute("capital", capital);
            country.setAttribute("latlng", element.getAttribute("latlng"));

            String lower = subregion.toLowerCase();
            if(lower.contains("europe")) {
                int temperature, welfare, medical;
                if(lower.contains("southern"))  {
                    temperature = 16;
                    medical = welfare = 7;
                } else if(lower.contains("eastern")) {
                    temperature = 8;
                    medical = 7;
                    welfare = 6;
                } else if(lower.contains("western")) {
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

            System.out.println("Country #" + (i+1));

            if(!populationMap.containsKey(element.getAttribute("cca3"))) {
                System.out.println("Country not existent: " + countryName);
                continue;
            }

            country.setAttribute("population", String.valueOf(populationMap.get(element.getAttribute("cca3"))));

            subregions.add(subregion);

            boolean borderExists = !borders[0].isEmpty();
            if(borderExists) {
                country.setAttribute("borders", element.getAttribute("borders"));

                System.out.print("Borders: ");
                for(int j = 0; j < borders.length; j++)
                    System.out.print(borders[j]);
                System.out.println();
            }
            System.out.println();

            root.appendChild(country);
        }

        newDoc.appendChild(root);

        Iterator<String> it = subregions.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // Use a Transformer for output
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
