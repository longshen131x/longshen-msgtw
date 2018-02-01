package com.longshen.msgtw.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class FileUtils {
	
	private static final int DEFAULT_BUFFER_SIZE=1024*4;
	
	/**
	 * 
	 * @描述:从classpath目录下获取文件内容
	 * @方法名: readFileToString
	 * @param filename
	 * @param encoding
	 * @return
	 * @throws IOException
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年2月1日上午11:15:10
	 * @修改人 longshen
	 * @修改时间 2018年2月1日上午11:15:10
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static String readFileToString(String filename,Charset encoding) throws IOException{
		
		InputStream input=null;
		StringWriter sw=new StringWriter();
		try {
			input=FileUtils.class.getResourceAsStream("/"+filename);
			InputStreamReader in =new InputStreamReader(input, encoding);
			char[] buffer =new char[DEFAULT_BUFFER_SIZE];
			int n=0;
			while ((n=in.read(buffer)) != -1) {

				sw.write(buffer, 0, n);
			}
			return sw.toString();
		} finally {
			if(input!=null) {
				input.close();
			}
			if(sw !=null) {
				sw.close();
			}
		}
		
	}

}
