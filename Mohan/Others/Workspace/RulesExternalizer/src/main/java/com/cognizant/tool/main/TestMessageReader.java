package com.cognizant.tool.main;

import java.io.File;
import java.io.IOException;

import com.cognizant.tool.reader.MessageReader;

public class TestMessageReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fileName=new File("C:\\Users\\396662\\Downloads\\Clarification Tracker.xlsx");
		
		MessageReader msgRdr=new MessageReader(fileName);
		try {
			msgRdr.readMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
