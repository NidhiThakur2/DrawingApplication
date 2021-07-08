package com.application.processor;

import java.io.File;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import com.application.commands.CommandFactory;
import com.application.xmlparser.Command;
import com.application.xmlparser.CommandMap;
import com.application.xmlparser.XmlParser;

@Service
public class ConsoleCommandProcessor implements IProcessor {
	public CommandMap cmdMap;
	public Map<String,String> helpMap;
	
	public void init() throws JAXBException {
		File file = new File("D:\\Workspace\\drawingapplication\\src\\main\\resources\\commands.xml");
		XmlParser xmlParser=new XmlParser();
		cmdMap=xmlParser.unmarshalXml(file);
		helpMap=generateHelpMap(cmdMap);
		
	}
	
	@Override
	public  Map<Command, String[]> parseCommand(String command) {
		System.out.println("Inside Paser");
		Map<Command,String[]> commandMap=new HashMap<Command, String[]>();
		String[] arrSplitedCommand=command.split(" ");
		String strCommand=arrSplitedCommand[0];
		String[] parameters=new String[arrSplitedCommand.length-1];
		parameters=Arrays.copyOfRange(arrSplitedCommand, 1, arrSplitedCommand.length);
		Command inputCommand=CommandFactory.getCommand(strCommand);
		Command initalizedCommand=cmdMap.getCommandMap().get(strCommand);
		if(inputCommand!=null) {
			inputCommand.setCommandString(initalizedCommand.getCommandString());	
			inputCommand.setDescription(initalizedCommand.getDescription());
			inputCommand.setIsDrawable(initalizedCommand.getIsDrawable());
			inputCommand.setParameters(initalizedCommand.getParameters());
			commandMap.put(inputCommand,parameters);
			return commandMap;
		}		
		return null;
	}
	
	@Override
	public boolean executeCommand(String command) {
		Map<Command,String[]> parsedCommand;
		boolean status=false;
		if(command.toUpperCase().equals("HELP")) {
			status=processHelp();
		}else {
			parsedCommand=parseCommand(command);
			if(parsedCommand==null) {
				
				System.out.println("INVALID COMMAND : Not Defined");
				return false;
			}
			
			Command inputCommand=parsedCommand.keySet().iterator().next();
			System.out.println("Command String"+ inputCommand.getCommandString());
			System.out.println("Command Parameters" + parsedCommand.get(inputCommand).length);
			status=inputCommand.validate(parsedCommand.get(inputCommand));
		}
		
		return status;
	}


	private boolean processHelp() {
		System.out.println("Process Help Command");
		return true;
	}
	private Map<String,String> generateHelpMap(CommandMap cmdMap){
		Map<String,String> helpMap=new HashMap<String, String>();
		helpMap=cmdMap.getCommandMap()
				.entrySet()
				.stream()
				.collect(Collectors.toMap(element->element.getValue().getCommandString(),element->element.getValue().getDescription()));
		System.out.println("HelpMap" + helpMap.entrySet());
		return helpMap;
	}

	
	

}
