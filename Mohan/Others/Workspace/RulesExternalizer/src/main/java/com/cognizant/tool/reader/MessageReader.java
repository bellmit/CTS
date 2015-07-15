package com.cognizant.tool.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;




import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MessageReader {
	
	private File fname;
	
	public MessageReader(File fileName) {
		// TODO Auto-generated constructor stub
		fname =fileName;
	}

	public void readMessage() throws IOException
	{
		if(fname==null)
		{
			System.out.println("File Object is null");
			throw new FileNotFoundException("File name==>"+fname);
		}
		
		FileInputStream fis=new FileInputStream(fname);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 
		//Get first sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		 
		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();
		 
		//Get iterator to all cells of current row
		//Iterator<Cell> cellIterator = row.cellIterator();
		while(rowIterator.hasNext()) {
	        Row row = rowIterator.next();
	         
	        //For each row, iterate through each columns
	        Iterator<Cell> cellIterator = row.cellIterator();
	        while(cellIterator.hasNext()) {
	             
	            Cell cell = cellIterator.next();
	             
	            switch(cell.getCellType()) {
	                case Cell.CELL_TYPE_BOOLEAN:
	                    System.out.print(cell.getBooleanCellValue() + "\t\t");
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    System.out.print(cell.getNumericCellValue() + "\t\t");
	                    break;
	                case Cell.CELL_TYPE_STRING:
	                    System.out.print(cell.getStringCellValue() + "\t\t");
	                    break;
	            }
	        }
	        System.out.println("");
	    }
	}
	
}
