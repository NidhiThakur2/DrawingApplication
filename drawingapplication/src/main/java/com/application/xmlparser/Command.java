package com.application.xmlparser;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.application.util.CommandUtil;

import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name = "Command")
@XmlAccessorType(XmlAccessType.FIELD)
public class Command {
	String commandString;
	Integer parameters;
	String isDrawable;
	String description;
	public String getCommandString() {
		return commandString;
	}
	public void setCommandString(String commandString) {
		this.commandString = commandString;
	}
	
	public Integer getParameters() {
		return parameters;
	}
	public void setParameters(Integer parameters) {
		this.parameters = parameters;
	}
	public String getIsDrawable() {
		return isDrawable;
	}
	public void setIsDrawable(String isDrawable) {
		this.isDrawable = isDrawable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean validate(String[] arrParameters) {
		boolean isValid=false;
		if(this.getParameters()==arrParameters.length) {
			CommandUtil commandUtil=new CommandUtil();
			isValid=commandUtil.isNumeric(arrParameters);
		}
		return isValid;
	}
	
	
}
