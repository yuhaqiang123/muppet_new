package com.muppet.data.core;

import com.muppet.data.util.XMLUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Map;

public abstract class AbstractConfig implements XMLConfig {

    private XMLConfigResource resource;
    private String xmlPath;
    private Map<String, List<Node>> map = null;
    protected final String XML_MAP = "xml_map";
    private Logger logger = LogManager.getLogger(this.getClass());

    public AbstractConfig(XMLConfigResource resource) {
        this.xmlPath = resource.getXmlpath();
        this.resource = resource;
        setXMLConfigResource(resource);

    }

    public AbstractConfig(String xmlPath) {
        this.xmlPath = xmlPath;
        config();
        resource = new XMLConfigResource();
        resource.setProp(XML_MAP, map);
    }

    private void merge(Map<String, List<Node>> map1, Map<String, List<Node>> map2) {
        for (Map.Entry<String, List<Node>> nodes : map2.entrySet()) {
            if (map1.containsKey(nodes.getKey())) {
                List<Node> list = map1.get(nodes.getKey());
                list.addAll(nodes.getValue());
            } else {
                map1.put(nodes.getKey(), nodes.getValue());
            }
        }
    }


    public void config() {
        map = XMLUtil.parse(this.xmlPath);
        logger.info("map:{}", map);
        if (map.containsKey(XML_LINKS)) {
            List<Node> nodes = map.get(XML_LINK);
            for (Node node : nodes) {
                String xml = node.getNodeValue();
                merge(map, XMLUtil.parse(xml));
            }
        }
    }


    @Override
    public XMLConfigResource getXMLConfigResource() {
        return resource;
    }

    private void setXMLConfigResource(XMLConfigResource resource) {
        this.resource = resource;
    }

}
