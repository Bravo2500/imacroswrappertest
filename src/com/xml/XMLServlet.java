package com.xml;

import java.io.File;


import com.xml.Manager;

/**
 * XML
 * 
 * @author
 */
public class XMLServlet {

	/**
	 * 
	 * @return
	 */
	public static boolean initConfig() {
		try {

			XMLFactory xmlFactory = XMLFactory.getDefaultXMLFactory(new File(
					System.getProperty("user.dir") + "./Config.xml"));
			XMLBuilder xmlBuilder = XMLBuilder.getBuilder(xmlFactory);
			XMLDocument xmlDocument = XMLDocument.getDocument(xmlBuilder);

			Manager.Path = xmlDocument.getElementByTaName("Path").toString();

			Manager.LogPath = Manager.Path
					+ xmlDocument.getElementByTaName("LogPath").toString();

			Manager.ResourcesPath = Manager.Path
					+ xmlDocument.getElementByTaName("ResourcesPath")
							.toString();

			Manager.ScriptPath = Manager.Path
					+ xmlDocument.getElementByTaName("ScriptPath").toString();
			
			Manager.Download = Manager.Path
			+ xmlDocument.getElementByTaName("Download").toString();

			xmlDocument.close();
			xmlDocument = null;
			xmlBuilder = null;
			xmlFactory = null;

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
