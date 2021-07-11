package com.application.drawing;

public interface ICanvas{
	public void setPixel(int x, int y,char ch);
	public char getPixel(int x, int y);
	public void drawPixel();
	public void resetCanvas(int width,int length);
	public boolean checkRange(Integer[] intParam);
}
