
package com.application.commands;

import org.springframework.stereotype.Component;

@Component
public class CommandFactory {
	public static AbstractCommand getCommand(String command) {
		AbstractCommand commandObj=null;
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
			default:
				commandObj=null;
				break;
		}
		return commandObj;
	}

}
