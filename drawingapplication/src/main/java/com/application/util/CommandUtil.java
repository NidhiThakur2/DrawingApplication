package com.application.util;

import org.springframework.stereotype.Component;

@Component
public class CommandUtil {
	public  Integer[] parseIntArray(String[] arr) {
		System.out.println("666666"+arr[0]);
	    Integer[] intArr =new Integer[arr.length];
	    try {
	    	for(int i=0;i<arr.length;i++) {
	    		intArr[i]=Integer.parseInt(arr[i]);
			}
	    }catch(NumberFormatException e) {
	    	intArr=null;
	    }
	    return intArr;
	}
	
	public boolean isNumeric(String[] arr) {
		try {
			for(int i=0;i<arr.length;i++) {
				Integer intVal=Integer.parseInt(arr[i]);
			}
	
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}
