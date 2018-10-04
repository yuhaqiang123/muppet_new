package com.muppet.data.util;

import com.muppet.data.core.XMLConfig;
import com.muppet.util.reflect.ReflectUtil;
import com.muppet.util.testframework.TestUnitStorageException;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class XMLUtil {

    public static void test1() {

    }

    public static List<Node> convertNodeList(NodeList nodes) {
        List<Node> list = new ArrayList<>(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            list.add(nodes.item(i));
        }
        return list;
    }

    public static List<Node> convertNodeList(NamedNodeMap namedNodeMap) {
        List<Node> list = new ArrayList<>(namedNodeMap.getLength());
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            list.add(namedNodeMap.item(i));
        }
        return list;
    }


    public static Document parseDoc(String path) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        DocumentBuilder builder = null;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            try {
                document = builder.parse(new File(URLDecoder.decode(path, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                throw new TestUnitStorageException(e.getMessage());
            } catch (SAXException e) {
                throw new TestUnitStorageException(e);
            } catch (IOException e) {
                throw new TestUnitStorageException(e);
            }
        } catch (ParserConfigurationException e) {
            throw new TestUnitStorageException(e);
        }
        return document;
    }


    private static void parseNode(Map<String, List<Node>> map, Node node, String parentKey) {
        assert node != null;
        String nodeKey = parentKey + "#" + node.getNodeName();
        if (map.containsKey(nodeKey)) {
            List<Node> nodes = map.get(nodeKey);
            nodes.add(node);
        } else {
            List<Node> nodes = new LinkedList<>();
            nodes.add(node);
            map.put(nodeKey, nodes);
        }
        if (node.hasChildNodes()) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                Node childNode = node.getChildNodes().item(i);
                parseNode(map, childNode, nodeKey);
            }
        }
    }


    public static Map<String, List<Node>> parse(String xmlpath) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        DocumentBuilder builder = null;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            String classPath = ReflectUtil.getClassPath();
            try {
                document = builder.parse(new ClassPathResource(xmlpath).getInputStream());
                NodeList nodeList = document.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeName().equals(XMLConfig.XML_ROOT_KEY)) {
                        HashMap<String, List<Node>> map = new HashMap<>();
                        parseNode(map, node, "");
                        return map;
                    }
                }

            } catch (UnsupportedEncodingException e) {
                throw ExceptionUtil.getRuntimeException(e);
            } catch (SAXException e) {
                throw ExceptionUtil.getRuntimeException(e);
            } catch (IOException e) {
                throw ExceptionUtil.getRuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw ExceptionUtil.getRuntimeException(e);
        }
        return null;

    }


    public static void main(String[] args) {


    }

}
