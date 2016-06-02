package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.util.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class NodeConverter {
	
	private Node mainNode;
	private Document htmlDoc;
	
	public NodeConverter(Document htmlDoc, Node mainNode){
		this.mainNode = mainNode;
		this.htmlDoc = htmlDoc;
	}
	
	public void visitNodes(){
		Node next = Utils.getFirstElementTypeChildNode(mainNode);
		int x = 0;
		int y = 0;
		int width = 0;
		String style = null;
		while(next != null){
			String name = next.getNodeName();
			//properties of the layout or widget
			if(name.equals("property")){
				if (next.getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) next;
					if(el.getAttribute("name").equals("geometry")){						
						StringBuilder sb = new StringBuilder();
						x = Integer.parseInt(el.getElementsByTagName("x").item(0).getTextContent());
						y = Integer.parseInt(el.getElementsByTagName("y").item(0).getTextContent());
						width = Integer.parseInt(el.getElementsByTagName("width").item(0).getTextContent());
						sb.append("left: " + x);
						sb.append("px; top: " + y);
						sb.append("px; width: " + width + "px");
						style = sb.toString();
						System.out.println(x + " " + y + " " + width);
					}
				}
			}
			//from layout grid type to division positions
			else if(name.equals("layout")){
				if (next.getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) next;
					String clName = el.getAttribute("name");
					if(clName.equals("QVBoxLayout")){
						
					}
					else if(clName.equals("QHBoxLayout")){
						convertHorizontalLayout(next, style);
					}
					else{
						convertGridLayout(next, style);
					}
				}
			}
			else if(name.equals("widget")){
				if (next.getNodeType() == Node.ELEMENT_NODE) {
					Element widget = (Element) next;
					Element div = htmlDoc.createElement("div");
					//QWidget has geometry property before elements
					if(widget.getAttribute("class").equals("QWidget")){
						style = Utils.getStyleFromGeometry(next);
						if(style != null){
							div.setAttribute("style", style);
						}
					}
					Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
					bodyElement.appendChild(div);
					Element el = (Element) Utils.getFirstElementTypeChildNode(next);
					while(el != null){
						PositionTypeConverter.createElement(htmlDoc, div, el);
						el = (Element) Utils.getFirstElementTypeSiblingNode(el);
					}

				}
			}
			else{
				System.out.println("Node name is: " + name);
			}
			next = Utils.getFirstElementTypeSiblingNode(next);
		}
	}
	public void convertGridLayout(Node next, String style){
		int max = GridTypeConverter.getColumnNumber(next.getFirstChild());
		System.out.println(max);
		Node item = next.getFirstChild();
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		Element div = htmlDoc.createElement("div");
		if(style != null){
			div.setAttribute("style", style);
		}
		bodyElement.appendChild(div);
		List<GridObject> list = new ArrayList<GridObject>();
		while(item != null){
			if(item.getNodeName().equals("item")){
				Element el = (Element) item;
				int nextRow = Integer.parseInt(el.getAttribute("row"));
				int column = Integer.parseInt(el.getAttribute("column"));
				int colSpan = 1;
				if(el.hasAttribute("colspan")){
					colSpan = Integer.parseInt(el.getAttribute("colspan"));
				}
				Element elementToSend = (Element) Utils.getFirstElementTypeChildNode(item);
				if(elementToSend != null){
					Element createdElement = GridTypeConverter.createElement(htmlDoc, div, elementToSend, column, colSpan, max);
					GridObject gridObject = new GridObject(createdElement, nextRow, column, colSpan);
					list.add(gridObject);
				}
			}
			item = item.getNextSibling();
		}
		//sort the list and append elements to body
		if(list != null && list.size() > 0){
			Collections.sort(list, new Comparator<GridObject>(){
				@Override
				public int compare(GridObject o1, GridObject o2) {
					if(o1.getRow() == o2.getRow()){
						return o1.getColumn()-o2.getColumn();
					}
					else{
						return o1.getRow()-o2.getRow();
					}
				}
				
			});
			for(GridObject gridObject : list){
				div.appendChild(gridObject.getElement());
			}
		}
	}
	
	public void convertHorizontalLayout(Node next, String style){

	}
	public void convertVerticalLayout(Node next, String style){
		Node item = next.getFirstChild();
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		Element div = htmlDoc.createElement("div");
		if(style != null){
			div.setAttribute("style", style);
		}
		bodyElement.appendChild(div);
		while(item != null){
			if(item.getNodeName().equals("item")){
				Element el = (Element) item;

				Element elementToSend = (Element) el.getElementsByTagName("widget").item(0);
				if(elementToSend != null){
					Element createdElement = GridTypeConverter.createElement(htmlDoc, div, elementToSend, 0, 1, 1);
					div.appendChild(createdElement);
				}
			}
			item = item.getNextSibling();
		}
	}
}
