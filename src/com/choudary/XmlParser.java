package com.choudary;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {

    private final String COST_CENTER = "([0-9]{2}[a-zA-Z0-9]{5})";
    private final String AUTH_CODE = "([a-zA-Z]{3})";
    private final String PROVIDER_ID = "([a-zA-Z])";
    private final String PROVIDER_USER_ID = "([a-zA-Z0-9]\\{1})";
    private final String STUDENT_ID = "([\\d]{2}[a-zA-Z0-9]{10,23}[_]?)";

    private List<XmlConfig> xmlConfigList = new ArrayList<>();

    public boolean loadConfig(File xmlFile, String real) throws ParserConfigurationException, IOException, SAXException {
        XmlConfig xmlConfig = new XmlConfig();
        boolean validEndpoint = false;

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
                boolean b = validatePath(real, path);
                if (b){
                    System.out.println(eElement.getElementsByTagName("serviceName").item(0).getTextContent());
                    System.out.println(eElement.getElementsByTagName("internalPath").item(0).getTextContent());
                    validEndpoint = true;
                }else {
                    System.out.println("NO MATCHING FOUND");
                    validEndpoint = false;
                }
//                xmlConfig.setPath(eElement.getAttribute("path"));
//                xmlConfig.setServiceName(eElement.getElementsByTagName("serviceName").item(0).getTextContent());
//                xmlConfig.setInternalPath(eElement.getElementsByTagName("internalPath").item(0).getTextContent());
//                xmlConfigList.add(xmlConfig);
            }
        }
        return validEndpoint;
    }

    public boolean validatePath(String realEndPoint, String path){
//        compare realEndPoint and path for a pattern match
        boolean pattern = checkPattern(realEndPoint, path);
        if (pattern){
            return verifyRegex(realEndPoint, path);
        }
        return false;
    }


    private boolean checkPattern(String realEndPoint, String path) {
        return realEndPoint.matches(Pattern.quote(path));
    }

    private boolean verifyRegex(String real, String path) {

        boolean match = true;
        Pattern costPattern = Pattern.compile(COST_CENTER);
        Pattern authPattern = Pattern.compile(AUTH_CODE);
        Pattern providerPattern = Pattern.compile(PROVIDER_ID);
        Pattern userPattern = Pattern.compile(PROVIDER_USER_ID);
        Pattern studentPattern = Pattern.compile(STUDENT_ID);

        if (path.contains("costCentre")){
//            int index0 = path.indexOf("costCentre") - 1;
//            System.out.println(index0);
            String[] split = real.split("/");
            match = costPattern.matcher(split[2]).matches();

//            for (int i = 0; i < split.length; i++) {
//                System.out.println(split[i]);
//            }
        }

        if (path.contains("authCode")){
            String[] split = real.split("/");
            match = authPattern.matcher(split[0]).matches();
        }

        if (path.contains("identityProviderId")){
            String[] split = real.split("/");
            match = providerPattern.matcher(split[4]).matches();
        }

        if (path.contains("identityProviderUserId")){
            String[] split = real.split("/");
            match = userPattern.matcher(split[5]).matches();
        }

        if (path.contains("studentId")){
            String[] split = real.split("/");
            match = studentPattern.matcher(split[7]).matches();
        }
        return match;
    }

}
