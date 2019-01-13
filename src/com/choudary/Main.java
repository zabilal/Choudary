package com.choudary;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File xmlFile = new File("passThrough.xml");
        XmlParser xmlParser = new XmlParser();
        try {
            xmlParser.loadConfig(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
