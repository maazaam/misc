package xmlconfig;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {

    private static final String ROOT = "//config";

    private DocumentBuilder builder;
    private XPath path;

    private Node node;

    public Config(String file) {
        try {
            this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.path = XPathFactory.newInstance().newXPath();
            this.node = this.builder.parse(file);
            this.node = (Node) this.path.compile(ROOT).evaluate(this.node, XPathConstants.NODE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Config getConfig(String path) {
        try {
            this.node = (Node) this.path.compile(path).evaluate(this.node, XPathConstants.NODE);
            return this;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getString(String path) {
        try {
            Node node = (Node) this.path.compile(path).evaluate(this.node, XPathConstants.NODE);
            return node.getTextContent();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<String> getList(String path) {
        try {
            List<String> list = new ArrayList<String>();
            NodeList nodes = (NodeList) this.path.compile(path).evaluate(this.node, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                list.add(nodes.item(i).getTextContent());
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
