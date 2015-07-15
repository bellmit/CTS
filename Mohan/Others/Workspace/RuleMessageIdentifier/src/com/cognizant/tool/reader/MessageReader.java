package com.cognizant.tool.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MessageReader {
	
	private File fname;
	
	public File getFname() {
		return fname;
	}

	public void setFname(File fname) {
		this.fname = fname;
	}

	public MessageReader(File fileName) {
		// TODO Auto-generated constructor stub
		fname =fileName;
	}

	public Set<String> readMessageSheet() throws IOException
	{
		
		if(fname==null)
		{
			System.out.println("File Object is null");
			throw new FileNotFoundException("File name==>"+fname);
		}
		Set<String> errorList=new HashSet<>();
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
			Cell cell=row.getCell(0);
			switch(cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				//System.out.print(cell.getBooleanCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				//System.out.print((int)cell.getNumericCellValue() + "\t\t");
				errorList.add(Integer.toString((int)cell.getNumericCellValue()));                
				break;
			case Cell.CELL_TYPE_STRING:
				//System.out.print(cell.getStringCellValue() + "\t\t");
				errorList.add(cell.getStringCellValue());

				break;
			}

			//For each row, iterate through each columns
			/*Iterator<Cell> cellIterator = row.cellIterator();
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
	        }*/

		}
		return errorList;
	}

	public Map<String, String> readRuleSheet() throws IOException
	{
		if(fname==null)
		{
			System.out.println("File Object is null");
			throw new FileNotFoundException("File name==>"+fname);
		}
		Map<String,String> rulesMap=new HashMap<>();
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
			Cell ruleIdCell=row.getCell(0);
			Cell actionCell=row.getCell(2);
			String ruleId=getCellData(ruleIdCell);
			String action=getCellData(actionCell);
			if(action !=null && !action.isEmpty())
			{
				rulesMap.put(ruleId, action);
			}
		}

		return rulesMap;
	}
	
	private String getCellData(Cell cell)
	{
		String data=null;
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			//System.out.print(cell.getBooleanCellValue() + "\t\t");
			data=Boolean.toString((boolean)cell.getBooleanCellValue());  
			break;
		case Cell.CELL_TYPE_NUMERIC:
			//System.out.print((int)cell.getNumericCellValue() + "\t\t");
			data=Integer.toString((int)cell.getNumericCellValue());                
			break;
		case Cell.CELL_TYPE_STRING:
			//System.out.print(cell.getStringCellValue() + "\t\t");
			data=cell.getStringCellValue();

			break;
		}
		return data;
	}
}
