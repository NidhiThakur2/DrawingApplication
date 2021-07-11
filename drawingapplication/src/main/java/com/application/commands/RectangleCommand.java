package com.application.commands;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.application.drawing.ICanvas;
import com.application.drawing.IDrawing;
import com.application.util.CommandUtil;

@Component
public class RectangleCommand extends AbstractCommand implements IDrawing{
	CommandUtil commandUtil=new CommandUtil();
	IDrawing line=new LineCommand();
	
	@Override
	public ICanvas getCanvas(ICanvas canvas, String[] cmdParam) {
		Map<Integer,String[]> lines=getLinesCoordinates(cmdParam);
		for(Integer key: lines.keySet()) {
			canvas=line.getCanvas(canvas, lines.get(key));
		}
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
	private Map<Integer,String[]> getLinesCoordinates(String[] parameters){
		Map<Integer,String[]> lines=new HashMap<Integer, String[]>();
		String[] line1= {parameters[0],parameters[1],parameters[0],parameters[3]};
		String[] line2= {parameters[2],parameters[1],parameters[2],parameters[3]};
		String[] line3= {parameters[0],parameters[1],parameters[2],parameters[1]};
		String[] line4= {parameters[0],parameters[3],parameters[2],parameters[3]};
		lines.put(1,line1);
		lines.put(2,line2);
		lines.put(3,line3);
		lines.put(4,line4);
		return lines;
	}
	private boolean canExecute(ICanvas canvas,String[] parameters)
	{
		boolean canExecute=false;
		if(canvas!=null) {
			Integer[] intParam=commandUtil.parseIntArray(parameters);
			canExecute=canvas.checkRange(intParam);
	}else {
			
			System.out.println("No Canvas Present to execute this command");
		}
		return canExecute;
	}
}
	

