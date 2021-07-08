package com.application.drawingapplication;


import java.util.Map;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.application.constants.Constants;
import com.application.processor.CommandProcessorFactory;

import com.application.processor.IProcessor;

@SpringBootApplication
public class DrawingapplicationApplication {

	
	
	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(DrawingapplicationApplication.class, args);
		startup();
		IProcessor consoleCommandProcessor = CommandProcessorFactory.getCommandProcessor("console");
		String exit;
		String command;
		boolean result;
		consoleCommandProcessor.init();
		Scanner sc= new Scanner(System.in);
		do {
			
			System.out.println("Enter command to execute or Enter \'help\' : ");
			System.out.println(">");
			command=sc.nextLine();
			System.out.println("Calling command processor...");
			result=consoleCommandProcessor.executeCommand(command);
			if(result!=true)
				System.out.println("COMMAND EXECUTION FAILED");
			System.out.println("Do you want to continue executing [YES/NO] :" );
			exit=sc.nextLine();
		
		}while(!exit.toUpperCase().equals("YES"));
		System.out.println("Bye Bye!!!!!!");
	}
	private static void startup() {
		System.out.println("********************************************\n");
		System.out.println("             DRAWING APPLICATION            ");
		System.out.println("\n********************************************");
		
	}

}
