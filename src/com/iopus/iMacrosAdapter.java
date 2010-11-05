/** 
 * @脚本名 iMacrosAdapter.java
 * @功能 iMacros测试脚本范例
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @创建时间 09-12-10 14:30
 * @最后修改时间 09-12-23 15:00
 */
package com.iopus;

// import java.util.ArrayList;

import org.jawin.COMException;

import com.iopus.IMacrosWrapper;

public class iMacrosAdapter {
	/**
	 * @param imacros
	 *            imacros播放器
	 */

	private IMacrosWrapper imacros;
	/**
	 * @param result
	 *            执行返回结果
	 */
	private int result;
	/**
	 * @param err_msg
	 *            脚本运行错误信息
	 */
	private String err_msg = "success";
	/**
	 * @param extract
	 *            脚本返回数据
	 */
	private String extract = "";
	/**
	 * @param version
	 *            版本号
	 */
	private String version = "";
	/**
	 * @param rfile
	 *            读取文件
	 */
	private RFile rfile;
	/**
	 * @param array
	 *            文件内容
	 */
	private String txtString;
	/**
	 * @param separator
	 *            分隔符
	 */
	private String separator = " ";

	/**
	 * 初始化脚本
	 * 
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws Exception 
	 */
	public String iimInit() throws Exception {
		try {
			imacros = new IMacrosWrapper();
			result = imacros.iimInit();
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (Exception e) {
			throw e;
		}
		return err_msg;
	}

	/**
	 * 初始化脚本
	 * 
	 * @param command
	 *            cmd String containing desired command line arguments.<br>
	 *            例如imacros1.iimInit("-silent"),imacros1.iimInit("-runner"),imacros1.iimInit("-ie"),imacros1.iimInit("-fx
	 *            -runner")<br>
	 *            imacros1.iimInit("-useragent
	 *            \"Nokia6230/2.0+(04.43)+Profile/MIDP-2.0+Configuration/CLDC-1.1+UP.Link/6.3.0.0.0\" ")
	 * 
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws Exception 
	 * 
	 */
	public String iimInit(String command) throws Exception {
		try {
			imacros = new IMacrosWrapper();
			result = imacros.iimInit(command);
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	/**
	 * 初始化脚本
	 * 
	 * @param command
	 *            cmd String containing desired command line arguments.<br>
	 *            例如imacros1.iimInit("-silent"),imacros1.iimInit("-runner"),imacros1.iimInit("-ie"),imacros1.iimInit("-fx
	 *            -runner")
	 * @param startBrowser
	 *            是否新开浏览窗口，true将新开一个browser，false则使用当前存在的browser
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws Exception 
	 */
	public String iimInit(String command, boolean startBrowser) throws Exception {
		try {
			imacros = new IMacrosWrapper();
			result = imacros.iimInit(command, startBrowser);
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	/**
	 * 初始化脚本
	 * 
	 * @param command
	 *            cmd String containing desired command line arguments.<br>
	 *            例如imacros1.iimInit("-silent"),imacros1.iimInit("-runner"),imacros1.iimInit("-ie"),imacros1.iimInit("-fx
	 *            -runner")
	 * @param startBrowser
	 *            是否新开浏览窗口，true将新开一个browser，false则使用当前存在的browser
	 * @param user
	 *            user account for access to a Windows Service.
	 * @param password
	 *            Password for access to a Windows service.
	 * @param domain
	 *            Domain in which the username given in run_as_user is valid.
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws Exception 
	 */
	public String iimInit(String command, boolean startBrowser, String user,
			String password, String domain) throws Exception {
		try {
			imacros = new IMacrosWrapper();
			result = imacros.iimInit(command, startBrowser, user, password,
					domain);
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	/**
	 * 设置iim内部参数
	 * 
	 * @param variable
	 *            参数名
	 * @param value
	 *            参数值
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimSet(String variable, String value) throws COMException {
		try {
			result = imacros.iimSet(variable, value);

			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	/**
	 * 批量设置iim内部参数<br>
	 * 默认使用<空格>分割参数名和参数值<br>
	 * 文件格式：<br>
	 * 参数名1 参数值1<br>
	 * 参数名2 参数值2<br>
	 * ......
	 * 
	 * @param path
	 *            文件路径
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimFileSet(String path) throws COMException {
		rfile = new RFile(path, false);
		txtString = rfile.readTxtFile();
		if (txtString.length() < 1) {

			return "文件" + path + "数据为空!";
		}
		// 分割文件内容
		String[] txt = txtString.split("\\r\\n");
		for (int temp = 0; temp < txt.length; temp++) {
			if (txt[temp].indexOf(separator) == -1) {

				return "文件" + path + "第" + (temp + 1) + "行出错,分割点是"
						+ txt[temp].indexOf(separator) + ",请检查!";
			}
			try {
				result = imacros.iimSet(txt[temp].substring(0,
						txt[temp].indexOf(separator)).trim(), txt[temp]
						.substring(txt[temp].indexOf(separator),
								txt[temp].length()).trim());
				if (result < 0) {
					err_msg = imacros.iimGetLastError();

				}
			} catch (COMException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		return err_msg;
	}

	/**
	 * 批量设置iim内部参数<br>
	 * 使用自定义分割参数名和参数值<br>
	 * 文件格式：<br>
	 * 参数名1 参数值1<br>
	 * 参数名2 参数值2<br>
	 * ......
	 * 
	 * @param path
	 *            文件路径
	 * @param separator
	 *            分隔符
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimFileSet(String path, String separator) throws COMException {
		if (separator.length() != 0) {
			this.separator = separator;
		}
		rfile = new RFile(path, false);
		txtString = rfile.readTxtFile();
		if (txtString.length() < 1) {

			return "文件" + path + "数据为空!";
		}
		String[] txt = txtString.split("\\r\\n");
		for (int temp = 0; temp < txt.length; temp++) {
			if (txt[temp].indexOf(separator) == -1) {

				return "文件" + path + "第" + (temp + 1) + "行出错,分割点是"
						+ txt[temp].indexOf(separator) + ",请检查!";
			}
			try {
				result = imacros.iimSet(txt[temp].substring(0,
						txt[temp].indexOf(separator)).trim(), txt[temp]
						.substring(txt[temp].indexOf(separator),
								txt[temp].length()).trim());
				if (result < 0) {
					err_msg = imacros.iimGetLastError();

				}
			} catch (COMException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		return err_msg;
	}

	/**
	 * 显示提示框
	 * 
	 * @param text
	 *            提示框显示的内容
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimDisPlay(String text) throws COMException {
		try {

			result = imacros.iimDisplay(text);
			// 如果出错打印错误信息
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;

	}

	/**
	 * 显示提示框
	 * 
	 * @param text
	 *            提示框显示的内容
	 * @param timeout
	 *            超时时间
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimDisPlay(String text, int timeout) throws COMException {
		try {

			result = imacros.iimDisplay(text, timeout);
			// 如果出错打印错误信息
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;

	}

	/**
	 * 播放脚本
	 * 
	 * @param iimScriptName
	 *            脚本名
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */

	public String iimPlay(String iimScriptName) throws COMException {
		try {

			result = imacros.iimPlay(iimScriptName);
			// 如果出错打印错误信息
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block

			throw e;
		}
		return err_msg;

	}

	/**
	 * 播放脚本
	 * 
	 * @param iimScriptName
	 *            脚本名
	 * @param timeout
	 *            超时时间
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */

	public String iimPlay(String iimScriptName, int timeout) throws COMException {
		try {

			result = imacros.iimPlay(iimScriptName, timeout);
			// 如果出错打印错误信息
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block

			throw e;
		}
		return err_msg;

	}

	/**
	 * 播放脚本代码<br>
	 * 
	 * @param code
	 *            脚本代码
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */

	public String iimCodePlay(String code) throws COMException {
		try {

			result = imacros.iimPlay(code);
			// 如果出错打印错误信息
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block

			throw e;
		}
		return err_msg;

	}

	/**
	 * 播放脚本代码
	 * 
	 * @param code
	 *            脚本代码
	 * @param timeout
	 *            超时时间
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */

	public String iimCodePlay(String code, int timeout) throws COMException {
		try {

			result = imacros.iimPlay(code, timeout);
			// 如果出错打印错误信息
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block

			throw e;
		}
		return err_msg;

	}

	/**
	 * 取得iim脚本最后截取到的返回信息
	 * 
	 * @return 返回信息内容
	 * @throws COMException 
	 */

	public String iimGetLastExtract() throws COMException {
		try {

			extract = imacros.iimGetLastExtract();

		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return extract;

	}

	/**
	 * 取得iim脚本截取到的返回信息
	 * 
	 * @param index
	 *            <br>
	 *            0： 返回全部信息<br>
	 *            1： 返回第一个截取到的信息<br>
	 *            2： 返回第二个截取到的信息<br>
	 *            ......<br>
	 * @return 返回信息内容
	 * @throws COMException 
	 */
	public String iimGetLastExtract(int index) throws COMException {
		try {

			extract = imacros.iimGetLastExtract(index);

		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return extract;

	}

	/**
	 * 退出脚本
	 * 
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimExit() throws COMException {
		try {
			result = imacros.iimExit();
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	/**
	 * 退出脚本
	 * 
	 * @param timeout
	 *            超时时间
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimExit(int timeout) throws COMException {
		try {
			result = imacros.iimExit(timeout);
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	/**
	 * 取得版本
	 * 
	 * @return 版本号 <br>
	 *         例如:<br>
	 *         32bit:<B>80066532</B>, 64bit: <B>80066564</B><br>
	 * @throws COMException 
	 */
	public String iimGetInterfaceVersion() throws COMException {
		try {
			version = imacros.iimGetInterfaceVersion();
			if (result < 0) {
				err_msg = imacros.iimGetLastError();
				System.err.println(err_msg);
			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return version;
	}

	/**
	 * 保存浏览器界面截屏 V.6.80版本以后可用
	 * 
	 * @param FILEPATH
	 *            保存图片全路径 e.g. "c:\myscreenshots\testpage.png"
	 * @param Code
	 *            0：保存整个截图，包括浏览器UI 1：只保存WebPage截图
	 * @return 执行返回信息：success成功其他返回错误信息
	 * @throws COMException 
	 */
	public String iimTakeBrowserScreenshot(String FILEPATH, int Code) throws COMException {
		try {
			result = imacros.iimTakeBrowserScreenshot(FILEPATH, Code);
			if (result < 0) {
				err_msg = imacros.iimGetLastError();

			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return err_msg;
	}

	// public void iimGetLastPerformance(int index) {
	// try {
	// result = imacros.iimGetLastPerformance(index);
	// if (result < 0) {
	// err_msg = imacros.iimGetLastError();
	// System.err.println(err_msg);
	// }
	// } catch (COMException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

}
