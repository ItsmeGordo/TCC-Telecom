package br.edu.ifsc.fsbilling.rest.entity;

import javax.xml.bind.annotation.XmlAttribute;

public class Action {

	String application;
	String data;

	public Action() {
		super();
	}

	public Action(String application, String data) {
		super();
		this.application = application;
		this.data = data;
	}

	@XmlAttribute
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	@XmlAttribute
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
