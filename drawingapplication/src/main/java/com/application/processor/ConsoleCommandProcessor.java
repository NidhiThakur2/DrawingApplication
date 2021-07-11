package com.application.processor;

import java.io.File;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;


import org.springframework.stereotype.Service;

import com.application.commands.AbstractCommand;
import com.application.commands.CommandFactory;
import com.application.drawing.CanvasFactory;
import com.application.drawing.ICanvas;
import com.application.util.CommandUtil;
import com.application.xmlparser.Command;
import com.application.xmlparser.CommandMap;
import com.application.xmlparser.XmlParser;

@Service
public class ConsoleCommandProcessor implements IProcessor {

	
	public CommandMap cmdMap;
	public Map<String,String> helpMap;
	public ICanvas consoleCanvas;
	CommandUtil commandUtil=new CommandUtil();
	public void init() throws JAXBException {
		File file = new File("D:\\Workspace\\drawingapplication\\src\\main\\resources\\commands.xml");
		XmlParser xmlParser=new XmlParser();
		consoleCanvas=null;
		cmdMap=xmlParser.unmarshalXml(file);
		helpMap=generateHelpMap(cmdMap);
		
	}
	
	@Override
	public  Map<AbstractCommand, String[]> parseCommand(String command) {
	//	System.out.println("Inside Paser");
		Map<AbstractCommand,String[]> commandMap=new HashMap<AbstractCommand, String[]>();
		String[] arrSplitedCommand=command.split(" ");
		String strCommand=arrSplitedCommand[0];
		String[] parameters=new String[arrSplitedCommand.length-1];
		parameters=Arrays.copyOfRange(arrSplitedCommand, 1, arrSplitedCommand.length);
		AbstractCommand inputCommand=CommandFactory.getCommand(strCommand.toUpperCase());
		Command initalizedCommand=cmdMap.getCommandMap().get(strCommand.toUpperCase());
		if(inputCommand!=null) {
			inputCommand=inputCommand.intialize(initalizedCommand);
			commandMap.put(inputCommand,parameters);
			return commandMap;
		}		
		return null;
	}
	
	@Override
	public boolean executeCommand(String command) {
		Map<AbstractCommand,String[]> parsedCommand;
		boolean status=false;
		boolean validationStatus=false;
		if(command.toUpperCase().equals("HELP")) {
			status=processHelp();
		}else {
			parsedCommand=parseCommand(command);
			if(parsedCommand==null) {
				
				System.out.println("INVALID COMMAND : Not Defined");
				return false;
			}
			
			AbstractCommand inputCommand=parsedCommand.keySet().iterator().next();
			//System.out.println("Command String"+ inputCommand.getCommandString());
			//System.out.println("Command Parameters" + parsedCommand.get(inputCommand).length);
			validationStatus=inputCommand.validate(parsedCommand.get(inputCommand));
			if(validationStatus) {
				if(inputCommand.getCommandString().toUpperCase().equals("C")) 
					consoleCanvas=createCanvas(parsedCommand.get(inputCommand));
				status=inputCommand.execute(consoleCanvas, parsedCommand.get(inputCommand));
			}else {
				//Handle Invalid Command Exception
				System.out.println("INVALID COMMAND : Invalid Arguments");
			}
		}
		
		return status;
	}

	private ICanvas createCanvas(String[] paramArr) {
		Integer[] intParam=commandUtil.parseIntArray(paramArr);
		ICanvas canvas=CanvasFactory.getCanvas("console",intParam[0],intParam[1]);
		Arrays.asList(intParam).stream().forEach(i->System.out.println(i));
		return canvas;
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
