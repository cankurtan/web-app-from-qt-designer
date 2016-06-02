package main.model;

import main.util.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GridTypeConverter {
	
	public static Element createElement(Document htmlDoc, Element bodyElement, Element eElement,int column, int colSpan, int max){
		
		String qClassName = eElement.getAttribute("class");
		Element div = createDiv(htmlDoc, bodyElement, column, colSpan, max);
		
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
		else if(qClassName.equals("QDialogButtonBox")){
			convertQDialogButtonBoxToHtmlElement(htmlDoc, div, eElement);
		}
		else{
			System.out.println(qClassName);
		}

		return div;
	}
	
	private static void convertQDialogButtonBoxToHtmlElement(Document htmlDoc, 
			Element div, Element eElement) {
		
		NodeList nodeList = eElement.getElementsByTagName("property");
		boolean isHorizontal = true;
		String buttonNames[] = null;
		for(int i = 0; i < nodeList.getLength(); i++){
			Element el = (Element) nodeList.item(i);
			if(el.getAttribute("name").equals("orientation")){
				if(!el.getElementsByTagName("enum").item(0).getTextContent().equals("Qt::Horizontal"))
					isHorizontal = false;
			}
			else if(el.getAttribute("name").equals("standardButtons")){
				String buttons = el.getElementsByTagName("set").item(0).getTextContent();
				buttonNames = buttons.split("\\|");
			}
			else{
				System.out.println("Dialog button box has: " + el.getAttribute("name"));
			}
		}
		for(String buttonName : buttonNames){
			Element buttonElement = htmlDoc.createElement("button");
			buttonElement.setAttribute("class", "btn btn-default");
			buttonElement.setTextContent(buttonName.replaceAll(".*::", ""));
			div.appendChild(buttonElement);
			if(!isHorizontal){
				div.appendChild(htmlDoc.createElement("br"));
			}
		}
	}

	public static void convertQPushButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		Element pElement = htmlDoc.createElement("button");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "button");
		pElement.setAttribute("class", "btn btn-default");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}

		bodyElement.appendChild(pElement);

	}

	public static void convertQCommandLinkButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		Element pElement = htmlDoc.createElement("button");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "button");
		pElement.setAttribute("class", "btn btn-default");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}

		bodyElement.appendChild(pElement);
	}

	public static void convertQToolButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		Element pElement = htmlDoc.createElement("button");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "button");
		pElement.setAttribute("class", "btn btn-default btn-block");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}

		bodyElement.appendChild(pElement);

	}

	public static void convertQRadioButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "radio");
		pElement.setAttribute("aria-label", "Default");
		if(bName != null){
			pElement.setTextContent(bName);
		}
		else{
			pElement.setTextContent("Default");
		}

		bodyElement.appendChild(pElement);
	}

	public static void convertQCheckBoxToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String bName = Utils.getStringProperty(eElement);
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

		bodyElement.appendChild(pElement);
	}

	public static void convertQDialogButtonToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		Element pElement = htmlDoc.createElement("div");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("role", "group");
		pElement.setAttribute("class", "btn-group");
		pElement.setAttribute("aria-label", "DEFAULT");


		Element firstElement = htmlDoc.createElement("button");
		firstElement.setAttribute("id", "default");
		firstElement.setAttribute("type", "button");
		firstElement.setAttribute("class", "btn btn-default");
		firstElement.setTextContent("Ok");
		pElement.appendChild(firstElement);

		Element secondElement = htmlDoc.createElement("button");
		secondElement.setAttribute("id", "default");
		secondElement.setAttribute("type", "button");
		secondElement.setAttribute("class", "btn btn-default");
		secondElement.setTextContent("Cancel");
		pElement.appendChild(secondElement);

		bodyElement.appendChild(pElement);

	}

	public static void convertQLineEditToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		Element pElement = htmlDoc.createElement("input");
		String placeholder = Utils.getStringProperty(eElement);
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "text");
		pElement.setAttribute("class", "form-control");
		pElement.setAttribute("placeholder", placeholder);
		pElement.setAttribute("aria-describedby", "basic-addon1");

		bodyElement.appendChild(pElement);

	}

	public static void convertQPlainTextEditToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "text");
		pElement.setAttribute("class", "form-control");
		pElement.setAttribute("placeholder", "placeholder");
		pElement.setAttribute("aria-describedby", "basic-addon1");

		bodyElement.appendChild(pElement);

	}

	public static void convertQLabelToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		String sName = Utils.getStringProperty(eElement);

		Element pElement = htmlDoc.createElement("span");
		pElement.setAttribute("id", id);
		pElement.setAttribute("class", "label label-default");
		if(sName != null){
			pElement.setTextContent(sName);
		}
		else{
			pElement.setTextContent("Default");
		}
		bodyElement.appendChild(pElement);

	}

	public static void convertQProgressBarToHtmlElement(Document htmlDoc, Element bodyElement, Element eElement){
		
		String id = eElement.getAttribute("name");
		Element pElement = htmlDoc.createElement("div");
		pElement.setAttribute("id", id);
		pElement.setAttribute("class", "progress");

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
		Element pElement = htmlDoc.createElement("input");
		//Max ve min deðerleri çekilerek buraya eklenecek
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "number");
		pElement.setAttribute("class", "form-control");

		bodyElement.appendChild(pElement);
	}
	
	
//	public static Element createDiv(Document htmlDoc, Element bodyElement, Node node, int x, int y, int width, int maxColumn){
//		
//		int col = 0;
//		int colSpan = 1;
//		Element element = null;
//		if(node.getNodeName().equals("item")){
//			if (node.getNodeType() == Node.ELEMENT_NODE) {
//				element = (Element) node;
//				col = Integer.parseInt(element.getAttribute("column"));
//				if(element.hasAttribute("colspan")){
//					colSpan = Integer.parseInt(element.getAttribute("colspan"));
//				}					
//			}
//		}
//		if(element != null){
//			Element div = htmlDoc.createElement("div");
//			String style = getStyle(x, y, width, maxColumn, col, colSpan);
//			if(style != null){
//				div.setAttribute("style", style);
//			}
//		}
//		return element;
//	}
	
	public static Element createDiv(Document htmlDoc, Element bodyElement, int col, int colSpan, int maxColumn){

		Element div = htmlDoc.createElement("div");
		int divSpan = Math.round((12/maxColumn)*colSpan);
		String classAttr = "col-sm-" + divSpan;
		String style="padding: 0 !important; margin: 0 !important;";
		div.setAttribute("class", classAttr);
		div.setAttribute("style", style);
		return div;
	}
	
	public static int getColumnNumber(Node node){
		
		int max = 0;
		while(node != null){
			if(node.getNodeName().equals("item")){
				Element element;
				int temp;
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;
					temp = Integer.parseInt(element.getAttribute("column"));
					if(element.hasAttribute("colspan")){
						temp += Integer.parseInt(element.getAttribute("colspan"));
					}
					else{
						temp++;
					}
					if(temp>max){
						max = temp;
					}						
				}
			}
			node = node.getNextSibling();
		}
		return max;
	}
	
//	public static String getStyle(int x, int y, int width, int maxColumn, int col, int colSpan){
//		StringBuilder sb = new StringBuilder();
//		sb.append("position: absolute; ");
//		try{
//			int colSize = (int) (width/maxColumn);
//			x += colSize*col;
//			sb.append("left: ");
//			sb.append(x);
//			sb.append("px; width: ");
//			sb.append(colSize*colSpan);
//			sb.append("px;");
//			return sb.toString();		
//		}
//		catch(Exception e){
//			return null;
//		}
//	}
}
