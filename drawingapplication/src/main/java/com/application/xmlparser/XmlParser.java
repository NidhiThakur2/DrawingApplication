package com.application.xmlparser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
@Component
public class XmlParser {
	public CommandMap unmarshalXml(File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandMap.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		System.out.println("!!!!!!!" +file);
		CommandMap cmdMap = (CommandMap) jaxbUnmarshaller.unmarshal(file);
		return cmdMap;
	}
}
