package com.application.processor;

import java.util.Map;

import javax.xml.bind.JAXBException;

import com.application.xmlparser.Command;

public interface IProcessor {
	public void init() throws JAXBException;
	public boolean executeCommand(String command);
	public Map<Command,String[]> parseCommand(String command);


}
