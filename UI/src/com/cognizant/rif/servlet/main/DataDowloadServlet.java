package com.cognizant.rif.servlet.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.rif.constants.RIFInputConstants;

/**
 * Servlet implementation class DataDowloadServlet
 */
@WebServlet("/dataDownload")
public class DataDowloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	byte[] buffer = new byte[1024];       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataDowloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		/*String userID="user1";
		String ruleID="PRV0830.0001.01";
		String msgID="9-3110-0010";*/
		/*
		String userID="user1";
		String ruleID="PRV0313.0001.01";
		String msgID="9-2010-0019";*/
		
		String userID="user1";
		String ruleID="EPD0002.0001.01";
		String msgID="9-4030-0005";
				
		System.out.println("Data Download Called...");
		response.setContentType("text/html");   
		
		ServletOutputStream outStream = response.getOutputStream();
		String filename = "PRV0830_0001_01.java";    
		//String filepath = UIConstants.OUTPUT_POJO_FILEPATH;   
		response.setContentType("APPLICATION/OCTET-STREAM");    
 
		
		File file=getOutputZip(userID,ruleID,msgID);
		response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() + "\"");   
		
		System.out.println(file.getAbsolutePath());

		
		FileInputStream inputStream = new FileInputStream(file);
		int len;
		while ((len = inputStream.read(buffer)) > 0) {			
				
			outStream.write(buffer, 0, len);	
		}
		
		
		inputStream.close();		
		//out.close();    
		outStream.close();
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Data Download Called... POST");

	}
	
	private File getOutputZip(String userID, String ruleID,String messageID) throws IOException{		
		List<File> rulesList=new ArrayList<File>();
		List<File> patternsList=new ArrayList<File>();
		File file=null;
		getValidFiles(ruleID,messageID,rulesList,patternsList);					
			FileOutputStream fos=null;
			try {
				file=new File("D:\\"+userID+"\\");
				if(!file.exists()){
					file.mkdir();
				}
				file =new File("D:\\"+userID+"\\"+ruleID +".zip");
				if(!file.exists()){
					file.createNewFile();
				}
				
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ZipOutputStream zos = new ZipOutputStream(fos);	
			
			Iterator<File> iteratorPojo=rulesList.iterator();
			while(iteratorPojo.hasNext()){
				File filePojo=iteratorPojo.next();
					ZipEntry ze= new ZipEntry(filePojo.getName());
						zos.putNextEntry(ze);
						FileInputStream inputStream = new FileInputStream(filePojo);
						int len;
						while ((len = inputStream.read(buffer)) > 0) {
							zos.write(buffer, 0, len);
						
						}
						inputStream.close();
			    		zos.closeEntry();
			}
			
			Iterator<File> iteratorPattern=patternsList.iterator();
			while(iteratorPattern.hasNext()){
				File filePattern=iteratorPattern.next();
					ZipEntry ze= new ZipEntry(filePattern.getName());
						zos.putNextEntry(ze);
						FileInputStream inputStream = new FileInputStream(filePattern);
						int len;
						while ((len = inputStream.read(buffer)) > 0) {
							zos.write(buffer, 0, len);
						}
						inputStream.close();
			    		zos.closeEntry();
			}
    		zos.close();		
			return file;			
	}

	private void getValidFiles(String ruleID,String messageID, List<File> rulesList,List<File> patternsList){
		String replacement = Matcher.quoteReplacement("_");
		String searchString = Pattern.quote(".");
		ruleID = ruleID.replaceAll(searchString, replacement);	
		File rulesPath=new File(RIFInputConstants.OUTPUT_POJO_FILEPATH);
		File patternsPath=new File(RIFInputConstants.OUTPUT_PATTERN_FILEPATH);		
		File[] pojoFiles=rulesPath.listFiles();
		
		for(int i=0;i<pojoFiles.length;i++){
			if(pojoFiles[i].getName().indexOf(ruleID)!=-1){
				rulesList.add(pojoFiles[i]);
			}
		}
		
		File[] patternFiles=patternsPath.listFiles();	
		
		for(int i=0;i<patternFiles.length;i++){
			if(patternFiles[i].getName().indexOf(messageID)!=-1){
				patternsList.add(patternFiles[i]);
			}
		}
	}
}

