package com.application.commands;

import com.application.xmlparser.Command;

public class CommandFactory {
	public static Command getCommand(String command) {
		Command commandObj=null;
		switch(command) {
			case "C" :
				commandObj=new CanvasCommand();
				break;
			case "L" :
				commandObj=new LineCommand();
				break;
			case "R" :
				commandObj=new RectangleCommand();
				break;
			case "B" :
				commandObj=new BucketFillCommand();
				break;
			case "Q":
				commandObj=new QuitCommand();
				break;
			default:
				commandObj=null;
				break;
		}
		return commandObj;
	}

}
