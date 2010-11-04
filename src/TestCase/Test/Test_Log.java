/**
 * @脚本名 Test_Log.java
 * @功能 测试脚本
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @最后修改时间 10-11-4 15:00
 */
package TestCase.Test;
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
		XMLServlet.initWebServerConfig();
		iMacrosTestScript = new iMacrosAdapter();
		iMacrosTestScript.iimInit();
		path = System.getProperty("user.dir");
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testLogin() {
		iMacrosTestScript.iimSet("USERNAME", "LWTest001");
		iMacrosTestScript.iimSet("PASSWORD", "111111");
		success = iMacrosTestScript.iimPlay(Manager.ScriptPath+ "\\Test\\TestLogin.iim");
		if (success != "success") {
			Assert.fail("登入系统出错，错误信息：" + success);
			return;
		}
		lastEx=iMacrosTestScript.iimGetLastExtract();
		if(lastEx!="LWTest001@126.com"){
			Assert.fail("登入系统出错，登入用户不正确");
			iMacrosTestScript.iimTakeBrowserScreenshot(path+"\\download\\err_page.png", 0);
			return;
		}
		
	}
}
