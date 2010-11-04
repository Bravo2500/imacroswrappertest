/**
 * @脚本名 XMLServlet.java
 * @功能 XML服务
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @最后修改时间 10-11-4 15:00
 */
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
	public static boolean initWebServerConfig() {
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
