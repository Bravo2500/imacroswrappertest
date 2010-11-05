package com.xml;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * XML工厂
 * @author 
 */
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
	 * @param xmlFile  xml文件
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
