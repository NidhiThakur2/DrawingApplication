package com.application.commands;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.drawing.ICanvas;
import com.application.drawing.IDrawing;
import com.application.util.CommandUtil;
import com.application.xmlparser.Command;

@Component
public class BucketFillCommand extends AbstractCommand implements IDrawing{
	CommandUtil commandUtil=new CommandUtil();
	@Override
	public boolean validate(String[] arrParameters) {
		CommandUtil commandUtil=new CommandUtil();
		boolean isValid=false;
		if(this.getParameters()==arrParameters.length) {
			
			String[] parameters=Arrays.copyOfRange(arrParameters, 0, arrParameters.length-1);
			isValid=commandUtil.isNumeric(parameters);
			if(isValid) {
				String color=arrParameters[arrParameters.length-1];
				System.out.println("Fill Color" +color);
				if(color.length()>1)
					isValid=false;
			}
		}
		System.out.println("!!!!"+isValid);
		return isValid;
	}
	
	@Override
	public ICanvas getCanvas(ICanvas canvas, String[] cmdParam) {
		String[] param=Arrays.copyOfRange(cmdParam, 0, cmdParam.length-1);
		Integer[] intParam=commandUtil.parseIntArray(param);
		char newC=cmdParam[cmdParam.length-1].charAt(0);
		char currentC=canvas.getPixel(intParam[0], intParam[1]);
		canvas=fill(canvas,intParam[0], intParam[1],currentC,newC);
		return canvas;
	}

	@Override
	public boolean drawCanvas(ICanvas canvas) {
		canvas.drawPixel();
		return true;
	}

	@Override
	public boolean execute(ICanvas canvas, String[] parameters) {
		boolean isExecutable=canExecute(canvas,parameters);
		boolean status=false;
		if(isExecutable) {
			canvas=getCanvas(canvas,parameters);
			status=drawCanvas(canvas);
		}
		return status;
	}
	
	private ICanvas fill(ICanvas canvas,int x,int y,char cc,char nc) {
		if(canvas.getPixel(x, y)!=cc)
			return canvas;
		canvas.setPixel(x, y, nc);
		canvas=fill(canvas,x-1,y,cc,nc);
		canvas=fill(canvas,x+1,y,cc,nc);
		canvas=fill(canvas,x,y-1,cc,nc);
		canvas=fill(canvas,x,y+1,cc,nc);
		return canvas;
	}

	private boolean canExecute(ICanvas canvas,String[] parameters)
	{
		boolean canExecute=false;
		if(canvas!=null) {
			String[] param=Arrays.copyOfRange(parameters, 0, parameters.length-1);
			Integer[] intParam=commandUtil.parseIntArray(param);
			canExecute=canvas.checkRange(intParam);
		}else {
			
			System.out.println("No Canvas Present to execute this command");
		}
		return canExecute;
	}
	
}
