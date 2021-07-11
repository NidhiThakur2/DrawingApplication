package com.application.xmlparser;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
@Component
@XmlRootElement (name="Commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandMap {
	 private Map<String, Command> commandMap = new HashMap<String, Command>();

	public Map<String, Command> getCommandMap() {
		return commandMap;
	}

	public void setCommandMap(Map<String, Command> commandMap) {
		this.commandMap = commandMap;
	}

}
