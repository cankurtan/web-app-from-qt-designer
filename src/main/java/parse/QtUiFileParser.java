package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import document.MyHTMLDocumentImpl;
import model.NodeConverter;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class QtUiFileParser {

	public QtUiFileParser(){

	}

	public void parse(File fXmlFile){
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("widget");
			Node mainNode = doc.getFirstChild();
			NodeConverter nodeConverter = null;
			
			Document htmlDoc = MyHTMLDocumentImpl.makeBasicHtmlDoc("My Title");
			
			if(mainNode.getNodeName().equals("ui")){
				NodeList uiChildren =  mainNode.getChildNodes();
				for(int i = 0 ; i < uiChildren.getLength(); i++){
					Node tempNode = uiChildren.item(i);
					if(tempNode.getNodeName().equals("widget")){
						nodeConverter = new NodeConverter(htmlDoc, tempNode);
						break;
					}
					else{
						System.out.println("Node name is: " + tempNode.getNodeName());
					}					
				}
			}
			else{
				System.out.println("Main node is not ui");
			}
			
			if(nodeConverter != null){
				nodeConverter.visitNodes();
			}
			
//			System.out.println("----------------------------");
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//
//				Node nNode = nList.item(temp);
//
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//					Element eElement = (Element) nNode;
//
//					System.out.print("Widget Class : " + eElement.getAttribute("class"));
//					addQObject(htmlDoc, eElement);
//
//					//					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//					//					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//					//					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//					//					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//
//				}
//			}
			//to serialize
	        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
	        DOMImplementationLS domImplLS = (DOMImplementationLS) registry.getDOMImplementation("LS");

	        LSSerializer lsSerializer = domImplLS.createLSSerializer();
	        DOMConfiguration domConfig = lsSerializer.getDomConfig();
	        domConfig.setParameter("format-pretty-print", true);  //if you want it pretty and indented

	        LSOutput lsOutput = domImplLS.createLSOutput();
	        lsOutput.setEncoding("UTF-8");

	        //to write to file
	        JFileChooser chooser = new JFileChooser();
	        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        int result = chooser.showSaveDialog(null);
	        String selectedFileName = fXmlFile.getName().replaceAll("\\.ui", ".html");
	        File outFile = new File(chooser.getSelectedFile(), selectedFileName);
	        try (OutputStream os = new FileOutputStream(outFile)) {
	            lsOutput.setByteStream(os);
	            lsSerializer.write(htmlDoc, lsOutput);
	        }
	        changeBreakLines(outFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void changeBreakLines(File outFile){
		Scanner scanner;
		try {
			scanner = new Scanner(outFile, "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			text = text.replaceAll("\\&lt;", "<");
			text = text.replaceAll("\\&gt;", ">");
			scanner.close();
			try( PrintWriter out = new PrintWriter(outFile)){
			    out.println(text);
			    out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addQObject(Document htmlDoc, Element eElement){
		String qClassName = eElement.getAttribute("class");
		if(qClassName.equals("QPushButton")){
			convertQPushButtonToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QToolButton")){
			convertQToolButtonToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QRadioButton")){
			convertQRadioButtonToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QCheckBox")){
			convertQCheckBoxToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QCommandLinkButton")){
			convertQCommandLinkButtonToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QDialogButton")){
			convertQDialogButtonToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QLineEdit")){
			convertQLineEditToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QPlainTextEdit")){
			convertQPlainTextEditToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QLabel")){
			convertQLabelToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QProgressBar")){
			convertQProgressBarToHtmlElement(htmlDoc, eElement);
		}
		else if(qClassName.equals("QSpinBox")){
			convertQSpinBoxToHtmlElement(htmlDoc, eElement);
		}
		else{

		}

	}


	public void convertQPushButtonToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String bName = getButtonName(eElement);
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

	public void convertQCommandLinkButtonToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String bName = getButtonName(eElement);
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

	public void convertQToolButtonToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String bName = getButtonName(eElement);
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

	public void convertQRadioButtonToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String bName = getButtonName(eElement);
		String style = getStyle(eElement);
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
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);
	}

	public void convertQCheckBoxToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String bName = getButtonName(eElement);
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

	public void convertQDialogButtonToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
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
		firstElement.setAttribute("class", "btn btn-default  btn-sm");
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

	public void convertQLineEditToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "text");
		pElement.setAttribute("class", "form-control");
		//pElement.setAttribute("placeholder", "placeholder");
		pElement.setAttribute("aria-describedby", "basic-addon1");
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public void convertQPlainTextEditToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "text");
		pElement.setAttribute("class", "form-control");
		pElement.setAttribute("placeholder", "placeholder");
		pElement.setAttribute("aria-describedby", "basic-addon1");
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public void convertQLabelToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		String sName = getButtonName(eElement);

		Element pElement = htmlDoc.createElement("span");
		pElement.setAttribute("id", id);
		pElement.setAttribute("class", "label label-default");
		if(sName != null){
			pElement.setTextContent(sName);
		}
		else{
			pElement.setTextContent("Default");
		}
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);

	}

	public void convertQProgressBarToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
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
	
	public void convertQSpinBoxToHtmlElement(Document htmlDoc, Element eElement){
		Element bodyElement = (Element) htmlDoc.getElementsByTagName("body").item(0);
		String id = eElement.getAttribute("name");
		String style = getStyle(eElement);
		Element pElement = htmlDoc.createElement("input");
		//Max ve min de�erleri �ekilerek buraya eklenecek
		pElement.setAttribute("id", id);
		pElement.setAttribute("type", "number");
		pElement.setAttribute("class", "form-control");
		if(style != null){
			pElement.setAttribute("style", style);
		}
		bodyElement.appendChild(pElement);
	}
	
	public String getButtonName(Element eElement){
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
	
	public String getStyle(Element eElement){
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
					sb.append("px; height: ");
					sb.append(el.getElementsByTagName("height").item(0).getTextContent());
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
