package com.choudary;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        File xmlFile = new File("passThrough.xml");
        XmlParser xmlParser = new XmlParser();
        try {
            String real = "/site/99ED001/contacts/data-sharing/HudsuckerIndustries/A1USER/students/00U16011ED260";

            xmlParser.loadConfig(xmlFile, real);
//            xmlParser.getLiteralValue("[0-9]{2}[a-zA-Z0-9]{5}");

//            xmlParser.validateConfig(real);
//            StringTokenizer st = new StringTokenizer(xmlParser.getXmlConfigList().get(0).getPath(), "{");

//            while (st.hasMoreTokens()) {
//                String token = st.nextToken();
//                System.out.println(token);
//            }
//            System.out.println(xmlParser.getXmlConfigList().get(0).getPath().split("{"));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
