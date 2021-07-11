package com.application.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		System.out.println("@@@@" + iProcessor);
		return iProcessor;
	}
	
}
