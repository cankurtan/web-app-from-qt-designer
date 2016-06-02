package main.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class QObject {

	public void convertNodeToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		Element pElement = htmlDoc.createElement("p");
        pElement.setAttribute("id", "default");
        pElement.setTextContent("Here is some text.");
        bodyElement.appendChild(pElement);
	}
}
