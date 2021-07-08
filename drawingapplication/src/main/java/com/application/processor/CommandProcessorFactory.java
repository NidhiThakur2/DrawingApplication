package com.application.processor;

public class CommandProcessorFactory {

	public static IProcessor getCommandProcessor(String processorType){
		IProcessor iProcessor=null;
		switch(processorType) {
		case "console":
			iProcessor= new ConsoleCommandProcessor();
			break;
		case "graphics":
			iProcessor= null;
			break;
		
		}
		return iProcessor;
	}
	
}
