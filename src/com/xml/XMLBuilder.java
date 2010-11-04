/**
 * @脚本名 XMLBuilder.java
 * @功能 Xml构建
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @最后修改时间 10-11-4 15:00
 */
package com.xml;

public final class XMLBuilder {

	private XMLFactory  xmlFactory =  null;
	
	/**
	 * 
	 * @param xmlFactory
	 */
	private XMLBuilder( final XMLFactory xmlFactory ){
		this.xmlFactory  =  xmlFactory;
	}
	
	/**
	 * 
	 * @param xmlFactory
	 * @return
	 */
	public static synchronized XMLBuilder getBuilder( final XMLFactory xmlFactory ) throws Exception {
		if( xmlFactory == null ) throw new NullPointerException(" xmlFactory is not NULL ! ");
		return new XMLBuilder(xmlFactory);
	}

	public XMLFactory getXmlFactory() {
		return xmlFactory;
	}
	
	public void close(){
		if( xmlFactory!= null ){
			xmlFactory.close();
		}
		xmlFactory  =  null;
	}
	
}
