package main.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utils {
	
	public static Node getFirstElementTypeChildNode(Node node){
		Node child = node.getFirstChild();
		while(child != null && child.getNodeType() != Node.ELEMENT_NODE){
			child = child.getNextSibling();
		}
		return child;
	}
	
	public static Node getFirstElementTypeSiblingNode(Node child){
		child = child.getNextSibling();
		while(child != null && child.getNodeType() != Node.ELEMENT_NODE){
			child = child.getNextSibling();
		}
		return child;
	}
	
	public static String getStyleFromGeometry(Node next){
		try{
			Element geometry = (Element) getFirstElementTypeChildNode(next);
			while(geometry != null && !geometry.getNodeName().equals("property") 
					&& !geometry.getAttribute("name").equals("geometry")){
				geometry = (Element) getFirstElementTypeSiblingNode(geometry);
			}
			if(geometry != null){
				StringBuilder sb = new StringBuilder();
				sb.append("left: " + geometry.getElementsByTagName("x").item(0).getTextContent());
				sb.append("px; top: " + geometry.getElementsByTagName("y").item(0).getTextContent());
				sb.append("px; width: " + geometry.getElementsByTagName("width").item(0).getTextContent() + "px");
				return sb.toString();
			}
			return null;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static String getStringProperty(Element eElement){
		NodeList nList = eElement.getElementsByTagName("property");
		for(int i = 0; i < nList.getLength(); i++){
			Element el = (Element) nList.item(i);
			String prop = el.getAttribute("name");
			if(prop.equals("text")){
				return el.getElementsByTagName("string").item(0).getTextContent();
			}			
		}
		return null;
	}
	
	public static String getHtmlProperty(Element eElement){
		NodeList nList = eElement.getElementsByTagName("property");
		for(int i = 0; i < nList.getLength(); i++){
			Element el = (Element) nList.item(i);
			String prop = el.getAttribute("name");
			if(prop.equals("html")){
				return el.getElementsByTagName("string").item(0).getTextContent();
			}			
		}
		return null;
	}
	
	public static String getFontProperty(Element eElement){
		NodeList nList = eElement.getElementsByTagName("property");
		for(int i = 0; i < nList.getLength(); i++){
			Element el = (Element) nList.item(i);
			String prop = el.getAttribute("name");
			if(prop.equals("font")){
				return el.getElementsByTagName("pointsize").item(0).getTextContent();
			}			
		}
		return null;
	}
	
	public static String getBuddyProperty(Element eElement){
		NodeList nList = eElement.getElementsByTagName("property");
		for(int i = 0; i < nList.getLength(); i++){
			Element el = (Element) nList.item(i);
			String prop = el.getAttribute("name");
			if(prop.equals("buddy")){
				return el.getElementsByTagName("cstring").item(0).getTextContent();
			}			
		}
		return null;
	}
	
}
