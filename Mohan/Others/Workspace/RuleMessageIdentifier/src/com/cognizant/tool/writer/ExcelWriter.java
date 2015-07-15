package com.cognizant.tool.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	public File file;

	public File getFile() {
		return file;
	}



	public void setFile(File file) {
		this.file = file;
	}



	public <S,L> void  rulewriter(Map<S,L> map) throws IOException
	{
		FileOutputStream fos=new FileOutputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xsheet=workbook.createSheet();
		int count=0;
		XSSFRow row=xsheet.createRow(count++);
		row.createCell(0).setCellValue("Error ID");
		row.createCell(1).setCellValue("Rule ID");
		
		for(Entry<S,L> entry:map.entrySet())
		{
			row=xsheet.createRow(count);
			row.createCell(0).setCellValue((String)entry.getKey());
			row.createCell(1).setCellValue(entry.getValue().toString());
			
			count++;
		}
		workbook.write(fos);
		fos.close();
	}
	
}
