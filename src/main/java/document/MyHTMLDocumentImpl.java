package document;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.w3c.dom.*;

//a base class somewhere in the hierarchy implements org.w3c.dom.Document
public class MyHTMLDocumentImpl extends DocumentImpl {

  private static final long serialVersionUID = 1658286253541962623L;


  /**
   * Creates an Document with basic elements
   * 
   * @param title desired text content for title tag. If null, no text will be added.
   * @return basic HTML Document. 
   */
  public static Document makeBasicHtmlDoc(String title) {
      Document htmlDoc = new MyHTMLDocumentImpl();
      DocumentType docType = new DocumentTypeImpl(null, "html",
              "-//W3C//DTD XHTML 1.0 Strict//EN",
              "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd");
      htmlDoc.appendChild(docType);
      Element htmlElement = htmlDoc.createElementNS("http://www.w3.org/1999/xhtml", "html");
      htmlDoc.appendChild(htmlElement);
      Element headElement = htmlDoc.createElement("head");
      htmlElement.appendChild(headElement);
      Element titleElement = htmlDoc.createElement("title");
      if(title != null)
          titleElement.setTextContent(title);
      headElement.appendChild(titleElement);
      Element bodyElement = htmlDoc.createElement("body");
      htmlElement.appendChild(bodyElement);
      
      Element linkElement = htmlDoc.createElement("link");
      linkElement.setAttribute("crossorigin", "anonymous");
      linkElement.setAttribute("integrity", "sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7");
      linkElement.setAttribute("href", "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css");
      linkElement.setAttribute("rel", "stylesheet");
      headElement.appendChild(linkElement);
      
      Element linkElement2 = htmlDoc.createElement("link");
      linkElement2.setAttribute("crossorigin", "anonymous");
      linkElement2.setAttribute("integrity", "sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r");
      linkElement2.setAttribute("href", "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css");
      linkElement2.setAttribute("rel", "stylesheet");
      headElement.appendChild(linkElement2);
      
      Element scriptElement = htmlDoc.createElement("script");
      scriptElement.setAttribute("crossorigin", "anonymous");
      scriptElement.setAttribute("integrity", "sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS");
      scriptElement.setAttribute("src", "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js");
      scriptElement.setTextContent("script");
      headElement.appendChild(scriptElement);
      
      return htmlDoc;
  }

  /**
   * This method will allow us to create a our
   * MyHTMLDocumentImpl from an existing Document.
   */
  public static Document createFrom(Document doc) {
      Document htmlDoc = new MyHTMLDocumentImpl();
      DocumentType originDocType = doc.getDoctype();
      if(originDocType != null) {
          DocumentType docType = new DocumentTypeImpl(null, originDocType.getName(),
                  originDocType.getPublicId(),
                  originDocType.getSystemId());
          htmlDoc.appendChild(docType);
      }
      Node docElement = doc.getDocumentElement();
      if(docElement != null) {
          Node copiedDocElement = docElement.cloneNode(true);
          htmlDoc.adoptNode(copiedDocElement);
          htmlDoc.appendChild(copiedDocElement);
      }
      return htmlDoc;
  }

  private MyHTMLDocumentImpl() {
      super();
  }

  @Override
  public Element createElement(String tagName) throws DOMException {
      return super.createElement(tagName.toLowerCase());
  }

  @Override
  public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
      return super.createElementNS(namespaceURI, qualifiedName.toLowerCase());
  }

  @Override
  public NodeList getElementsByTagName(String tagname) {
      return super.getElementsByTagName(tagname.toLowerCase());
  }

  @Override
  public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
      return super.getElementsByTagNameNS(namespaceURI, localName.toLowerCase());
  }

  @Override
  public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
      return super.renameNode(n, namespaceURI, qualifiedName.toLowerCase());
  }
}