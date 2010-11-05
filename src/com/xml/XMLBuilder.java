package com.xml;

/**
 * xml构造
 * @author 
 */
public final class XMLBuilder {

	//
	//
	//
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
