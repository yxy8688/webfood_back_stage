package com.yc.foods.utils;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

public class FileUploadUtil {
	//public static String PATH="../pics";
	private static final String ALLOWED="doc,txt,jpg,png,gif,xls";
	private static final String DENIED="exe,bat,jsp,html,htm";
	private static final int MAXFILESIZE=10*1024*1024;
	private static final int TOTALMAXFILESIZE=100*1024*1024;
	
	public Map<String,String> fileUpload(PageContext pageContext){
		Map<String,String> map=new HashMap<String,String>();
		
		//新建一个SmartUpload对象
		SmartUpload su=new SmartUpload();
		
		try{
			//初始化su对象
			su.initialize(pageContext);
			//单个文件最大大小
			su.setMaxFileSize(MAXFILESIZE);
			
			su.setTotalMaxFileSize(TOTALMAXFILESIZE);
			
			su.setAllowedFilesList(ALLOWED);
			
			su.setDeniedFilesList(DENIED);
			
			su.setCharset("UTF-8");
			
			su.upload();
			
			Request request=su.getRequest();
			
			Enumeration<String> names=request.getParameterNames();
			
			String key;
			while(names.hasMoreElements()){
				key=names.nextElement();
				map.put(key, request.getParameter(key));
		
			}
			
			Files files=su.getFiles();
			
			String fieldName="photo";
			
			if(files!=null  && files.getCount()>0){
				String filePath=pageContext.getServletContext().getRealPath("/../pics/");
				
				java.io.File f=new java.io.File(filePath);
				
				if(!f.exists()){
					f.mkdirs();
				}
				
				Collection<File> collection=files.getCollection();
				
				String fileName="";
				String picPath="";
				
				for(File fl:collection){
					if(!fl.isMissing()){
						fileName=new Date().getTime()+""+new Random().nextInt(9999)+"."+fl.getFileExt();
						
						fl.saveAs(filePath+"/"+fileName);
						
						picPath+=fileName+",";
					}
					fieldName=fl.getFieldName();
				}
				if(picPath.contains(",")){
					picPath=picPath.substring(0,picPath.lastIndexOf(","));
				}
				map.put(fieldName, picPath);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
