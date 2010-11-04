/**
 * @脚本名 XMLFactory.java
 * @功能 XML工厂
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @最后修改时间 10-11-4 15:00
 */
package com.xml;

import java.io.File;
import java.io.FileNotFoundException;


public final class XMLFactory {

	//
	// 
	//
	private File  xmlFile  =  null;
	
	/**
	 * 
	 * @param xmlFile
	 */
	private XMLFactory( final File xmlFile ){
		this.xmlFile =  xmlFile;
	}
	
	/**
	 * 
	 * @param xmlFile  xml�ļ�
	 * @return
	 * @throws Exception
	 */
	public static synchronized XMLFactory getDefaultXMLFactory( final File xmlFile ) throws Exception {
		if( xmlFile == null ) throw new NullPointerException(" xmlFile is not NULL ! ");
		if( !xmlFile.exists() ) throw new FileNotFoundException(" xmlFile is not FOUND ! "+xmlFile.getAbsolutePath());
		//System.err.println(xmlFile.getAbsolutePath());
		return new XMLFactory( xmlFile );
	}

	public File getXmlFile() {
		return xmlFile;
	}
	
	public void close(){
		xmlFile  =  null;
	}
}
