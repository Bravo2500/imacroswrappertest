/**
 * @脚本名 Test_Log.java
 * @功能 测试脚本
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @最后修改时间 10-11-4 15:00
 */
package TestCase.Test;
import org.jawin.COMException;

import com.iopus.iMacrosAdapter;
import com.xml.Manager;
import com.xml.XMLServlet;
import junit.framework.Assert;
import junit.framework.TestCase;
public class Test_Log extends TestCase {
	iMacrosAdapter iMacrosTestScript;
	String path;
	String success;
	String lastEx;
	public Test_Log() {
		super();
	}
	protected void setUp() throws Exception {
		super.setUp();
		XMLServlet.initConfig();
		iMacrosTestScript = new iMacrosAdapter();
		iMacrosTestScript.iimInit();
		path = System.getProperty("user.dir");
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testLogin()  {
		//iMacrosTestScript.iimSet("USERNAME", "LWTest001");
		//iMacrosTestScript.iimSet("PASSWORD", "111111");
		try{
			iMacrosTestScript.iimSet("log", Manager.LogPath+"\\TestLog.txt");
			iMacrosTestScript.iimFileSet(Manager.ResourcesPath+"\\Test\\Test.txt");
			success = iMacrosTestScript.iimPlay(Manager.ScriptPath+ "\\Test\\TestLogin.iim");
			lastEx=iMacrosTestScript.iimGetLastExtract();
		}
		catch(Exception e){
			Assert.fail("系统出错:"+e.getMessage());
			return;
		}
		if (success != "success") {
			Assert.fail("登入系统出错，错误信息：" + success);
			return;
		}

		if(lastEx != "LWTest001@126.com"){
			Assert.fail("登入系统出错，登入用户不正确:"+lastEx);
			try {
				iMacrosTestScript.iimTakeBrowserScreenshot(path+"\\download\\err_page.png", 0);
			} catch (COMException e) {
				// TODO Auto-generated catch block
				Assert.fail("保存当前页面图像出错:"+e.getMessage());
			}
			return;
		}
		
	}
}
