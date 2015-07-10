package com.cognizant.rif.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	
	static byte[] buffer = new byte[1024];    
	
	public static File getZipFile(List<File> fileList)
	{
	
		
		try
		{
			if(fileList.size()>0)
			{			
				System.out.println("Parent File==>"+fileList.get(0).getParent());
				File file=new File(fileList.get(0).getParent()+"\\POJO.zip");
				FileOutputStream fos = new FileOutputStream(file);
				ZipOutputStream zos = new ZipOutputStream(fos);	
				
				Iterator<File> iteratorPojo=fileList.iterator();
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
				zos.close();		
				return file;
			}
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		return null;
	}

}
