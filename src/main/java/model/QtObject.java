package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QtObject {
	
	private String name;
	private String textContent;
	private Map<String,String> properties;
	private List<QtObject> additionals;
	
	public QtObject(String name, boolean hasAdd){
		this.name = name;
		textContent = null;
		properties = new HashMap<String, String>();
		if(hasAdd){
			additionals = new ArrayList<QtObject>();
		}
		else{
			additionals = null;
		}
	}
	
	public QtObject(String name, String textContent, boolean hasAdd){
		this.name = name;
		this.textContent = textContent;
		properties = new HashMap<String, String>();
		if(hasAdd){
			additionals = new ArrayList<QtObject>();
		}
		else{
			additionals = null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public void addProperty(String key, String value){
		properties.put(key, value);
	}
	
	public Set<String> getPropertySet(){
		return properties.keySet();
	}
	
	public String getProperty(String key){
		return properties.containsKey(key) == true ? properties.get(key) : null;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public List<QtObject> getAdditionals() {
		return additionals;
	}

	public void setAdditionals(List<QtObject> additionals) {
		this.additionals = additionals;
	}
	
}
