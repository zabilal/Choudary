package test;

import com.choudary.XmlParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XmlParserTest {

    private XmlParser xmlParser;

    @Before
    public void setup() throws IOException, SAXException, ParserConfigurationException {
        xmlParser = new XmlParser();
    }

    @Test
    public void endPointMatch_False() throws IOException, SAXException, ParserConfigurationException {

        String endpoint = "/site/99ED001/contacts/data-sharing/HudsuckerIndustries/A1USER/students/00U16011ED260";

        boolean isValidxmlParser;
        if (xmlParser.loadConfig(new File("passThrough.xml"), endpoint)) isValidxmlParser = true;
        else isValidxmlParser = false;
        Assert.assertFalse("Endpoint is not Valid", isValidxmlParser);
    }

    @Test
    public void endPointMatch_OK() throws IOException, SAXException, ParserConfigurationException {

        String endpoint = "/site/99ED001/contacts/data-sharing/ZakLtd/A33USER/students/00U16011ED260";

        boolean isValidxmlParser;
        if (xmlParser.loadConfig(new File("passThrough.xml"), endpoint)) isValidxmlParser = true;
        else isValidxmlParser = false;
        Assert.assertFalse("Endpoint is not Valid", isValidxmlParser);
    }
}
