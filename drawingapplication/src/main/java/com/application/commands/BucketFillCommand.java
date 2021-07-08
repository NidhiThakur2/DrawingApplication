package com.application.commands;

import java.util.Arrays;

import com.application.util.CommandUtil;
import com.application.xmlparser.Command;

public class BucketFillCommand extends Command {
	@Override
	public boolean validate(String[] arrParameters) {
		boolean isValid=false;
		if(this.getParameters()==arrParameters.length) {
			CommandUtil commandUtil=new CommandUtil();
			String[] parameters=Arrays.copyOfRange(arrParameters, 0, arrParameters.length-1);
			isValid=commandUtil.isNumeric(parameters);
			if(isValid) {
				String color=arrParameters[arrParameters.length-1];
				System.out.println("Fill Color" +color);
				if(color.length()>1)
					isValid=false;
			}
		}
		return isValid;
	}
	
}
