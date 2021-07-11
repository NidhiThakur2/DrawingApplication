package com.application.commands;

import org.springframework.stereotype.Component;

import com.application.drawing.ICanvas;
import com.application.util.CommandUtil;
import com.application.xmlparser.Command;
@Component
public abstract class AbstractCommand extends Command {
//	public abstract boolean validate(String[] arrParameters);
//	public abstract AbstractCommand intialize(Command initalizedCommand);
	

	public  boolean validate(String[] arrParameters) {
		boolean isValid=false;
		if(this.getParameters()==arrParameters.length) {
			CommandUtil commandUtil=new CommandUtil();
			isValid=commandUtil.isNumeric(arrParameters);
		}
		return isValid;
	}
	
	public AbstractCommand intialize(Command initalizedCommand) {
		
		this.setCommandString(initalizedCommand.getCommandString());	
		this.setDescription(initalizedCommand.getDescription());
		this.setDrawable(initalizedCommand.isDrawable());
		this.setParameters(initalizedCommand.getParameters());
		
		return this;
	}
	
	public abstract boolean execute(ICanvas canvas,String[] parameters);
	
}
