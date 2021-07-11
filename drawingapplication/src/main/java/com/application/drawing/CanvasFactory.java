package com.application.drawing;

import org.springframework.stereotype.Component;

@Component
public class CanvasFactory {
	public static ICanvas getCanvas(String canvas,int w,int l) {
		ICanvas canvasObj=null;
		switch(canvas) {
			case "console":
				canvasObj=ConsoleCanvas.getInstance(w,l+2);
				break;
			case "graphics":
				canvasObj=null;
		}
		return canvasObj;
	}
}
