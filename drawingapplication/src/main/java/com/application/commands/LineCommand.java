package com.application.commands;

import org.springframework.stereotype.Component;

import com.application.drawing.ICanvas;
import com.application.drawing.IDrawing;
import com.application.util.CommandUtil;
@Component
public class LineCommand extends AbstractCommand implements IDrawing{
	CommandUtil commandUtil=new CommandUtil();
	
	@Override
	public ICanvas getCanvas(ICanvas canvas, String[] cmdParam) {
		char fillChar='X';
		ICanvas canvasTemp=canvas;
		Integer[] intParam=commandUtil.parseIntArray(cmdParam);
		if(intParam[0]==intParam[2]) {
			for(int i=intParam[1];i<=intParam[3];i++)
				canvasTemp.setPixel(intParam[0], i, fillChar);
		}else if(intParam[1]==intParam[3]) {
			for(int i=intParam[0];i<=intParam[2];i++)
				canvasTemp.setPixel(i, intParam[1], fillChar);
		}
		return canvasTemp;
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

	private boolean canExecute(ICanvas canvas,String[] parameters)
	{
		boolean canExecute=false;
		if(canvas!=null) {
			Integer[] intParam=commandUtil.parseIntArray(parameters);
			if(checkVerticalAndHorizontal(intParam)) {
				if(canvas.checkRange(intParam))
					canExecute=true;
				else
					System.out.println("Parameter Range out of Canvas");
			}else {
				System.out.println("Please draw Horizontal/Vertical line Hint: either X1=X2(Horizontal) or Y1=Y2(Vertical)");
			}
						
		}else {
			
			System.out.println("No Canvas Present to execute this command");
		}
		return canExecute;
	}
	
	private boolean checkVerticalAndHorizontal(Integer[] intParam) {
		if(intParam[0]==intParam[2] || intParam[1]==intParam[3])
			return true;
		return false;
	}
	

}
