package br.edu.ifsc.fsbilling.rest.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "param")
public class Param {

	String name;
	String value;

	public Param() {
		super();
	}

	public Param(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
