package xmlentity;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Helper {

    private static final String YES = "yes";
    private static final String SPLIT = "/";

    private DocumentBuilder builder;
    private Transformer transformer;

    public Helper() {
        try {
            this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.transformer = TransformerFactory.newInstance().newTransformer();
            this.transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, YES);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Document read(String string) {
        try {
            if (string != null && !string.isEmpty()) {
                return this.builder.parse(new ByteArrayInputStream(string.getBytes()));
            } else {
                return this.builder.newDocument();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String write(Document document) {
        try {
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new StringWriter());
            this.transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void insert(Node node, String name, String value) {
        Document document = node.getOwnerDocument();
        if (document == null) {
            document = (Document) node;
        }
        Element element = document.createElement(name);
        element.setTextContent(value);
        node.appendChild(element);
    }

    private void update(Node node, String value) {
        node.setTextContent(value);
    }

    private void delete(Node node, Node item) {
        node.removeChild(item);
        NodeList list = node.getChildNodes();
        if (list.getLength() == 0) {
            Node parent = node.getParentNode();
            if (parent != null) {
                delete(parent, node);
            }
        }
    }

    private Node select(Node node, String name) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            if (item.getNodeName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    private String value(Node node) {
        return node.getTextContent();
    }

    private String name(String path) {
        if (path.contains(SPLIT)) {
            return path.substring(0, path.indexOf(SPLIT));
        }
        return path;
    }

    private String next(String path) {
        if (path.contains(SPLIT)) {
            return path.substring(path.indexOf(SPLIT) + 1, path.length());
        }
        return null;
    }

    private String search(Node node, String path) {
        String name = name(path);
        String next = next(path);
        Node item = select(node, name);
        if (item != null) {
            if (next != null) {
                return search(item, next);
            } else {
                return value(item);
            }
        }
        return null;
    }

    private void search(Node node, String path, String value) {
        String name = name(path);
        String next = next(path);
        Node item = select(node, name);
        if (item != null) {
            if (next != null) {
                search(item, next, value);
            } else {
                if (value != null) {
                    update(item, value);
                } else {
                    delete(node, item);
                }
            }
        } else {
            if (next != null) {
                insert(node, name, null);
                item = select(node, name);
                search(item, next, value);
            } else {
                insert(node, name, value);
            }
        }
    }

    public String parse(String details, String field) {
        if (details != null && !details.isEmpty() && field != null && !field.isEmpty()) {
            Document document = read(details);
            return search(document, field);
        }
        return null;
    }

    public String build(String details, String field, String value) {
        if (field != null && !field.isEmpty()) {
            Document document = read(details);
            search(document, field, value);
            return write(document);
        }
        return null;
    }
}
