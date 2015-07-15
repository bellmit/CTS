package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class ReadFile {

	/**
	 * @param args
	 */
	public static Logger log;
	
	static
	{
		System.out.println("Static block");
		log=Logger.getLogger(ReadFile.class); 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			File file=new File("D:\\Study_Workspace\\TestFiles");
			System.out.println(file.isFile());
			if(file.isDirectory())
			{
				file.list(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						System.out.println(dir.getPath());
						if(dir.isFile())
						{
							System.out.println("TEST");
						}
						return true;
					}
				});
			}
			
			Scanner sc=new Scanner(new File("D:\\Study_Workspace\\TestFiles\\TEST.txt"));
			log.warn(sc.next());
			
		}
		catch(FileNotFoundException fx)
		{
			
		}
		finally
		{
			log.warn("Finally Block");
		}
	}

}
