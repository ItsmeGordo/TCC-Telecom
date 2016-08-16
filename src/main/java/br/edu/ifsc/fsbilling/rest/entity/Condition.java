package br.edu.ifsc.fsbilling.rest.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Condition {

	String field;
	String expression;
	List<Action> actions;

	@XmlAttribute
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@XmlAttribute
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@XmlElement(name = "action")
	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

}
