package com.application.processor;

import java.util.Map;

import javax.xml.bind.JAXBException;

import com.application.commands.AbstractCommand;
import com.application.xmlparser.Command;

public interface IProcessor {
	public void init() throws JAXBException;
	public boolean executeCommand(String command);
	public Map<AbstractCommand,String[]> parseCommand(String command);


}
