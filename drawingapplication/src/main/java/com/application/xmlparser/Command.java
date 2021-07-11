package com.application.xmlparser;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.stereotype.Component;

import com.application.util.CommandUtil;

import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlAccessType;

@Component
@XmlRootElement(name = "Command")
@XmlAccessorType(XmlAccessType.FIELD)
public class Command {
	String commandString;
	Integer parameters;
	boolean isDrawable;
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

	public String getDescription() {
		return description;
	}
	public boolean isDrawable() {
		return isDrawable;
	}
	public void setDrawable(boolean isDrawable) {
		this.isDrawable = isDrawable;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
