package fr.monolog.game.utils;

import java.util.Random;
import java.io.File;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Helpers {

    /**
     * Génère un réel pseudo-aléatoire compris dans l'interval min et max exclus
     * @param min
     * @param max
     * @return
     */
    static public float randomFloat(float min, float max){
        Random r = new Random();
        return min + r.nextFloat() * (max-min);
    }

    /**
     * Génère un entier pseudo-aléatoire compris dans l'interval min et max exclus
     * @param min
     * @param max
     * @return
     */
    static public int randomInt(int min, int max){
        Random r= new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Génère un réel pseudo-aléatoire compris dans l'interval min et max exclus, à l'aide d'une graîne
     * @param seed
     * @param min
     * @param max
     * @return
     */
    static public float randomFloatSeed(long seed, float min, float max) {
        if (seed == 0) {
            seed = Constants.SEED;
        }
        Random r = new Random(seed);
        return min + r.nextFloat() * (max - min);
    }

    /**
     *  Génère un entier pseudo-aléatoire compris dans l'interval min et max exclus, à l'aide d'une graîne
     * @param seed
     * @param min
     * @param max
     * @return
     */
    static public int randomIntSeed(long seed, int min, int max){
        if(seed == 0){
            seed = Constants.SEED;
        }
        Random r= new Random(seed);
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Simple XML file reader
     * @param path
     * @return
     */
    static public Document xmlReader(String path){

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            final DocumentBuilder builder = factory.newDocumentBuilder();

            final Document document= builder.parse(new File(path));

            return document;
        }

        catch (final ParserConfigurationException e) {

            e.printStackTrace();

        }

        catch (final SAXException e) {

            e.printStackTrace();

        }

        catch (final IOException e) {

            e.printStackTrace();

        }

        return null;

        /*
        final Document document =  Helpers.xmlReader("../datas/player.xml");

        System.out.println("XML DOCUMENT VERSION");
        System.out.println(document.getXmlVersion());

        final Element racine = document.getDocumentElement();
        final NodeList racineNoeuds = racine.getChildNodes();
        final int nbRacineNoeuds = racineNoeuds.getLength();

        System.out.println(racine.getNodeName());
        System.out.println(racine.getElementsByTagName("name").item(0).getTextContent());

        for (int i = 0; i<nbRacineNoeuds; i++) {

            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

                final Element personne = (Element) racineNoeuds.item(i);
                System.out.println(personne.getAttribute("name"));
            }
        }*/
    }
}
