package com.application.drawing;

import org.springframework.stereotype.Component;

@Component
public class ConsoleCanvas implements ICanvas {

	public int width;
	public int length;
	public char[][]canvas;
	private static ConsoleCanvas instance;
	
	public ConsoleCanvas() {
		
	}
	private ConsoleCanvas(int w,int l) {
		this.width=w;
		this.length=l;
		this.canvas=new char[l][w];
		
	}
	
	public static ICanvas getInstance(int w,int l) {
		if(instance==null)
			instance=new ConsoleCanvas(w,l);
		return instance;
	}
	
	@Override
	public void setPixel(int w, int l,char ch) {
		this.canvas[l][w]=ch;
	}
	@Override
	public void drawPixel() {
		for(int i=0;i<=this.length-1;i++) {
			for(int j=0;j<=this.width-1;j++) {
				System.out.print(this.canvas[i][j]);
			}
			System.out.println();
		}
	}
	@Override
	public void resetCanvas(int w,int l) {
		this.width=w;
		this.length=l;
		this.canvas=new char[l][w];
	}
	@Override
	public char getPixel(int w,int l) {
		return this.canvas[l][w];
	}
	
	@Override
	public boolean checkRange(Integer[] intParam) {
		char[][] canvasPixels=this.canvas;
		int length=canvasPixels.length;
		int width=canvasPixels[0].length;
		System.out.println("Canvas Length"+length);
		System.out.println("Canvas Width"+ width);
		boolean inRangeX=false;
		boolean inRangeY=false;
		boolean status=false;
		for(int i=0;i<=intParam.length-1;i++) {
			if(i==0 || i==2) {
				if(intParam[i]>0 && intParam[i]<=width-1)
					inRangeX=true;
			}
			if(i==1 || i==3) {
				if(intParam[i]>0 && intParam[i]<=length-1)
					inRangeY=true;
			}
		}
		if(inRangeX && inRangeY)
			status=true;
		return status;
	}
	

}
