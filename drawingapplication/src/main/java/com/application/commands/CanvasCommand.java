package com.application.commands;

import org.springframework.stereotype.Component;

import com.application.drawing.ICanvas;
import com.application.drawing.IDrawing;
import com.application.util.CommandUtil;

@Component
public class CanvasCommand extends AbstractCommand implements IDrawing  {

	CommandUtil commandUtil=new CommandUtil();
	
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

	@Override
	public boolean drawCanvas(ICanvas canvas) {
		canvas.drawPixel();
		return true;
	}
	
	@Override
	public ICanvas getCanvas(ICanvas canvas,String[] cmdParam) {
		Integer[] intParam=commandUtil.parseIntArray(cmdParam);
		int width=intParam[0];
		int length=intParam[1]+2;
		canvas.resetCanvas(width, length);
		ICanvas canvasTemp=canvas;
		char ch;
		for(int i=0;i<=length-1;i++)
		{
			for(int j=0;j<=width-1;j++) {
				ch=' ';
				if(i==0||i==length-1)
					ch='-';
				else if(j==0||j==width-1)
						ch='|';
			
				canvasTemp.setPixel(j, i, ch);
				
			}
		}
		return canvasTemp;
	}

	private boolean canExecute(ICanvas canvas,String[] parameters)
	{
		return true;
	}
	
	
}
