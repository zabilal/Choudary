package com.choudary;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {

    private List<XmlConfig> xmlConfigList = new ArrayList<>();

    public void loadConfig(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        XmlConfig xmlConfig = new XmlConfig();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("endpoint");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                //Pick the Path
                String path = eElement.getAttribute("path");
                xmlConfig.setPath(eElement.getAttribute("path"));
                xmlConfig.setServiceName(eElement.getElementsByTagName("serviceName").item(0).getTextContent());
                xmlConfig.setServiceName(eElement.getElementsByTagName("internalPath").item(0).getTextContent());
                xmlConfigList.add(xmlConfig);
            }
        }
    }

    public void validateConfig(){
        
    }

}
