/**
 * @脚本名 Manager.java
 * @功能 全局变量管理器
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @最后修改时间 10-11-4 15:00
 */
package com.xml;

public class Manager {

	

	public static String Path;
	public static String LogPath;
	public static String ResourcesPath;
	public static String ScriptPath;
	public static String Download;

	public static void initWebServerConfig() {
		XMLServlet.initWebServerConfig();
		
	}

}
