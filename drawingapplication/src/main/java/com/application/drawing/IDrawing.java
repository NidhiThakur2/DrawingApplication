package com.application.drawing;

public interface IDrawing {
	public ICanvas getCanvas(ICanvas canvas,String[] cmdParam);
	public boolean drawCanvas(ICanvas canvas);
	
}
