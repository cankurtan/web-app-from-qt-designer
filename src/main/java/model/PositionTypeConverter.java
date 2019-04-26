package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Utils;

public class PositionTypeConverter {
	
public static void createElement(Document htmlDoc, Element div, Element eElement){
		
		String qClassName = eElement.getAttribute("class");
		
		if(qClassName.equals("QPushButton")){
			convertQPushButtonToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QToolButton")){
			convertQToolButtonToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QRadioButton")){
			convertQRadioButtonToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QCheckBox")){
			convertQCheckBoxToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QCommandLinkButton")){
			convertQCommandLinkButtonToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QDialogButton")){
			convertQDialogButtonToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QLineEdit")){
			convertQLineEditToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QPlainTextEdit")){
			convertQPlainTextEditToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QLabel")){
			convertQLabelToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QProgressBar")){
			convertQProgressBarToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QSpinBox")){
			convertQSpinBoxToHtmlElement(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QTextBrowser")){
			convertQTextBrowserToDiv(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QVBoxLayout")){
			convertVerticalLayout(htmlDoc, div, eElement);
		}
		else if(qClassName.equals("QWidget")){
			convertQWidgetToDiv(htmlDoc, div, eElement);
		}
		else{
			System.out.println(qClassName + " is not found");
		}
	}
	
	public static void convertQPushButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		String style = getStyle(eElement);
		String fontSize = Utils.getFontProperty(eElement);
		Element pElement = htmlDoc.createElement("button");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "button");
		pElement.setAttribute("class", "btn btn-default btn-sm");
		if(bName != null){
			bName = bName.replaceAll("(\\r|\\n|\\r\\n)+", "<br />");
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}
		if(style != null){
			if(fontSize != null){
				style = style + " font-size: " + fontSize + "pt;";
			}
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public static void convertQCommandLinkButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("button");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "button");
		pElement.setAttribute("class", "btn btn-default btn-sm");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);
	}

	public static void convertQToolButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("button");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "button");
		pElement.setAttribute("class", "btn btn-default btn-sm");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public static void convertQRadioButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		Element div = htmlDoc.createElement("div");
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "radio");
		pElement.setAttribute("aria-label", "Default");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}
		if(style != null){
			div.setAttribute("style", style);
		}
		div.appendChild(pElement);
		bodyElement.appendChild(div);
	}

	public static void convertQCheckBoxToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "checkbox");
		pElement.setAttribute("aria-label", "DEFAULT");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);
	}

	public static void convertQDialogButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("div");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("role", "group");
		pElement.setAttribute("class", "btn-group");
		pElement.setAttribute("aria-label", "DEFAULT");
		if(style != null){
			pElement.setAttribute("style", style);
		}

		Element firstElement = htmlDoc.createElement("button");
		firstElement.setAttribute("id", "default");
		firstElement.setAttribute("type", "button");
		firstElement.setAttribute("class", "btn btn-default btn-sm");
		firstElement.setTextContent("Ok");
		pElement.appendChild(firstElement);

		Element secondElement = htmlDoc.createElement("button");
		secondElement.setAttribute("id", "default");
		secondElement.setAttribute("type", "button");
		secondElement.setAttribute("class", "btn btn-default btn-sm");
		secondElement.setTextContent("Cancel");
		pElement.appendChild(secondElement);

		bodyElement.appendChild(pElement);

	}

	public static void convertQLineEditToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		String placeholder = Utils.getStringProperty(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "text");
		pElement.setAttribute("class", "form-control input-sm");
		pElement.setAttribute("placeholder", placeholder);
		pElement.setAttribute("aria-describedby", "basic-addon1");
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public static void convertQPlainTextEditToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "text");
		pElement.setAttribute("class", "form-control input-sm");
		pElement.setAttribute("placeholder", "placeholder");
		pElement.setAttribute("aria-describedby", "basic-addon1");
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public static void convertQLabelToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		String sName = Utils.getStringProperty(eElement);
		String fontSize = Utils.getFontProperty(eElement);
		String link = Utils.getBuddyProperty(eElement);
		
		if(link == null){
			Element pElement = htmlDoc.createElement("span");
			pElement.setAttribute("id", id);
			pElement.setAttribute("class", "label label-default");
			if(sName != null){
				sName = sName.replaceAll("(\\r|\\n|\\r\\n)+", "<br />");
				pElement.setTextContent(sName);
			}
			else{
				pElement.setTextContent("Default");
			}
			if(style != null){
				if(fontSize != null){
					style = style + " font-size: " + fontSize + "pt;";
				}
				pElement.setAttribute("style", style);
			}
			bodyElement.appendChild(pElement);
		}
		/* 
		 * if there is photo in the label, takes link from its properties
		 * 
		 */
		else{
			Element pElement = htmlDoc.createElement("div");
			pElement.setAttribute("id", id);
			if(style != null){
				pElement.setAttribute("style", style);
			}
			Element imgElement = htmlDoc.createElement("img");
			imgElement.setAttribute("src", link);
			imgElement.setAttribute("alt", "");
			pElement.appendChild(imgElement);
			bodyElement.appendChild(pElement);
		}

	}

	public static void convertQProgressBarToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("div");
		pElement.setAttribute("id", id);
		pElement.setAttribute("class", "progress");
		if(style != null){
			pElement.setAttribute("style", style);
		}

		Element firstElement = htmlDoc.createElement("div");
		firstElement.setAttribute("class", "progress-bar");
		firstElement.setAttribute("role", "progressbar");
		firstElement.setAttribute("aria-valuenow", "60");
		firstElement.setAttribute("aria-valuemin", "100");
		firstElement.setAttribute("aria-valuemax", "progress-bar");
		firstElement.setAttribute("style", "width:60px");
		pElement.appendChild(firstElement);

		bodyElement.appendChild(pElement);

	}
	
	public static void convertQSpinBoxToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		//Max ve min deðerleri çekilerek buraya eklenecek
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "number");
		pElement.setAttribute("class", "form-control input-sm");
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);
	}
	
	public static void convertQTextBrowserToDiv(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		String content = Utils.getHtmlProperty(eElement);
		Element div = htmlDoc.createElement("div");
		div.setAttribute("id", id);
		if(style != null){
			div.setAttribute("style", style);
		}
		if(content != null){
			div.setTextContent(content);
		}
		bodyElement.appendChild(div);
	}
	
	public static void convertQWidgetToDiv(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element div = htmlDoc.createElement("div");
		//Max ve min deðerleri çekilerek buraya eklenecek
		div.setAttribute("id", id);
		if(style != null){
			div.setAttribute("style", style);
		}
		bodyElement.appendChild(div);
		Element el = (Element) Utils.getFirstElementTypeChildNode(eElement);
		while(el != null){
			PositionTypeConverter.createElement(htmlDoc, div, el);
			el = (Element) Utils.getFirstElementTypeSiblingNode(el);
		}
	}
	
	public static void convertVerticalLayout(Document htmlDoc, Element div, Element eElement){
		Node item = eElement.getFirstChild();

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
	
	public static String getStyle(Element eElement){
		StringBuilder sb = new StringBuilder();
		NodeList nList = eElement.getElementsByTagName("property");
		sb.append("position: absolute; ");
		try{
			for(int i = 0; i < nList.getLength(); i++){
				Element el = (Element) nList.item(i);
				String prop = el.getAttribute("name");
				if(prop.equals("geometry")){
					sb.append("left: ");
					sb.append(el.getElementsByTagName("x").item(0).getTextContent());
					sb.append("px; top: ");
					sb.append(el.getElementsByTagName("y").item(0).getTextContent());
					sb.append("px; width: ");
					sb.append(el.getElementsByTagName("width").item(0).getTextContent());
					sb.append("px;");
					return sb.toString();
				}			
			}
		}
		catch(Exception e){
			return null;
		}
		return null;
	}
}
