package com.application.drawingapplication;



import java.util.Scanner;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.application.processor.CommandProcessorFactory;
import com.application.processor.IProcessor;

@Configuration
@ComponentScan(basePackages="com.application")
@SpringBootApplication
public class DrawingapplicationApplication {
	

	public static ApplicationContext ac;
	// added for testing 
	private static void checkBeansPresence(String... beans) {
	        for (String beanName : beans) {
	            System.out.println("Is " + beanName + " in ApplicationContext: " + 
	              ac.containsBean(beanName));
	        }
	    }
	public static void main(String[] args) throws JAXBException {
		ac=SpringApplication.run(DrawingapplicationApplication.class, args);
	//	checkBeansPresence("xmlParser");
		startup();
		IProcessor consoleCommandProcessor = CommandProcessorFactory.getCommandProcessor("console");
	//	System.out.println("!!!!"+consoleCommandProcessor);
		String exit;
		String command;
		boolean result;
		consoleCommandProcessor.init();
		Scanner sc= new Scanner(System.in);
		do {
			
			System.out.println("Enter command to execute or Enter \'help\' : ");
			System.out.println(">");
			command=sc.nextLine();
			if(command.toUpperCase().equals("Q"))
				break;
			System.out.println("Calling command processor...");
			result=consoleCommandProcessor.executeCommand(command);
			if(result!=true)
				System.out.println("COMMAND EXECUTION FAILED");
			System.out.println("Do you want to exit????[YES/NO] :" );
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
