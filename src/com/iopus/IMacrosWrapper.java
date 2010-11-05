/*
 * IMacrosWrapper
 *
 * Created on June 25, 2004, 1:26 PM
 *
 * This class uses software developed by the DevelopMentor
 * OpenSource Project (http://www.develop.com/OpenSource).
 * "The Java/Win32 integration project (Jawin) is a free, open
 * source architecture for interoperation between Java and
 * components exposed through Microsoft's Component Object
 * Model (COM) or through Win32 Dynamic Link Libraries (DLLs)."
 *
 * See below for how to obtain the Jawin distribution.
 *
 * The Jawin software is subject to license terms. The official URL
 * for the Jawin license is
 *   http://staff.develop.com/halloway/DMOpenSourceLicense.txt
 *
 * This class is written and Copyright (c) 2004 by John Grosberg.
 * Contact John Grosberg at: jgrosbergATyahooDOTcom (replace capital
 * letters to form proper email address)
 *
 * This software is released under the following license (the "MIT" license):
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *
 */

package com.iopus;

// These imports are available from:
// http://sourceforge.net/projects/jawinproject/
import org.jawin.*;
import org.jawin.win32.Ole32;
import org.jawin.win32.Registry;
import org.jawin.win32.RegistryConstants;

/**
 * This class allows the programmer access the iOpus iimInterface.dll directly
 * from Java without using JNI calls. The JNI code is hidden by using code
 * derived from the Jawin open source project and is subject to its license.
 * "The Java/Win32 integration project (Jawin) is a free, open source
 * architecture for interoperation between Java and components exposed through
 * Microsoft's Component Object Model (COM) or through Win32 Dynamic Link
 * Libraries (DLLs)."
 * <p>
 * The Jawin project is hosted at http://sourceforge.net/projects/jawinproject/.
 * An overview of Jawin can be found at
 * http://jawinproject.sourceforge.net/jawin.html. The Jawin license is at
 * http://jawinproject.sourceforge.net/LICENSE.txt.
 * <p>
 * It is not necessary to know anything about Jawin to use this class, however.
 * <p>
 * To use this class,
 * <ul>
 * <li> Download the Jawin distribution from
 * http://sourceforge.net/projects/jawinproject/
 * <li> From that download, extract jawin-1.0.19/bin/jawin.dll
 * <li> Put the jawin.dll in your program's working directory or in the
 * c:\windows\System32\ directory or wherever you need to put dlls. (Make sure
 * that directory is on your PATH.)
 * <li> Also from that download, extract jawin-1.0.19/lib/jawin.jar and put it
 * in a convenient directory.
 * <li> Include the jawin.jar in your classpath.
 * <li> Include in your classpath the directory containing this class's package
 * (com.iopus).
 * <li> import this class into your Java program's code.
 * <li> In your code, create an instance of this class, then use that instance
 * to invoke the methods ({@link #init() init()},
 * {@link com.iopus.IMacrosWrapper#play(String) play()}, etc.) that control the
 * internetMacros dll.
 * </ul>
 * A brief example of the usage of this class follows. The boldface code is the
 * portion directly accessing internetMacros. You can see that it is very
 * similar to that used by the scripting examples provided by iOpus on their
 * website. <CODE>
 * <PRE>
 * import com.iopus.IMacrosWrapper;
 * 
 * public class MyImacrosJavaProgram {
 * 	public static void main(String[] args)
 *   {
 *      try
 *      {
 *         IMacrosWrapper &lt;B&gt;iim&lt;/B&gt; = new IMacrosWrapper();
 *         int result;
 *         result = &lt;B&gt;iim.init()&lt;/B&gt;;
 *         System.out.println(&quot;iimInit result is: &quot; + result);
 *         System.out.println(&lt;B&gt;iim.getLastMessage()&lt;/B&gt;);
 *         result = &lt;B&gt;iim.play(&quot;demo-flash&quot;)&lt;/B&gt;;
 *         System.out.println(&quot;iimPlay demo-flash result is: &quot; + result);
 *         System.out.println(&lt;B&gt;iim.getLastMessage()&lt;/B&gt;);
 *         . . .
 *         result = &lt;B&gt;iim.exit()&lt;/B&gt;;
 *         System.out.println(&quot;iimExit result is: &quot; + result);
 *         iim.close();
 *      }
 *      catch(Exception e)
 *      {
 *         e.printStackTrace();
 *      }
 *   }
 * }
 * </PRE>
 * </CODE>
 * <p>
 * 
 * <PRE>
 * This product uses software developed by the DevelopMentor
 * OpenSource Project (http://www.develop.com/OpenSource).
 * This class is written and Copyright (c) 2004 by John Grosberg.
 * Contact John Grosberg at jgrosbergATyahooDOTcom (replace capital
 * letters to form proper email address).
 * This software is released under the following license (the &quot;MIT&quot; license):
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the &quot;Software&quot;), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED &quot;AS IS&quot;, WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 * </PRE>
 * 
 * <p>
 * The descriptions for some of the functions below are taken from the Internet
 * Macros Manual. For further details, see that manual at
 * http://www.iopus.com/iim/help/help_toc.htm.
 * 
 * @author John Grosberg
 * 
 * Thanks to Emil M黮ler for adding the two new methods, iimGetLastError and
 * iimGetLastExtract().
 * 
 * Thanks to Lasse Clausen for adding the dynamic retrieval of the CLSID from
 * the registry.
 */
/**
 * @修改者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @创建时间 09-12-10 14:30
 * @最后修改时间 09-12-15 14:00
 * @说明 做了少许修改，新增了一些方法的参数，新增了iimGetInterfaceVersion(),iimTakeBrowserScreenshot()
 */
public class IMacrosWrapper implements RegistryConstants {
	/*
	 * Instead of providing the CLSID, which might be difficult to obtain and
	 * might also change due to changes in the Scripting Interface, the Registry
	 * is now queried for the correct CLSID using the ProgID, which is
	 * (hopefully) easier to obtain 新版本修改了参数，standardProgID改为iMacros
	 */
	// private static final String standardProgID = "InternetMacros.iim";
	private static final String standardProgID = "iMacros";
	private static GUID CLSID;

	private DispatchPtr iimObj;

	/**
	 * Creates a new instance of ImacrosWrapper; initializes the internetMacros
	 * COM library using the <b>standard</b> ProgID. When finished with the
	 * instance of ImacrosWrapper, make sure you call {@link #close()}.
	 * 
	 * @throws Exception
	 */
	public IMacrosWrapper() throws Exception {
		CLSID = getGUID(standardProgID);
	}

	/**
	 * Creates a new instance of ImacrosWrapper; initializes the internetMacros
	 * COM library using a <b>user-defined</b> ProgID. When finished with the
	 * instance of ImacrosWrapper, make sure you call {@link #close()}.
	 * 
	 * @throws Exception
	 */
	public IMacrosWrapper(String progID) throws Exception {
		CLSID = getGUID(progID);
	}

	/**
	 * retrieves the correct CLSID for the given _progID from the Registry.
	 * 
	 * @throws Exception
	 */
	private GUID getGUID(String _progID) throws Exception {
		// 修改，这里改成HKEY_LOCAL_MACHINE\SOFTWARE\Classes\iMacros\CLSID
		int key = Registry.OpenKey(HKEY_LOCAL_MACHINE, "SOFTWARE\\Classes\\"
				+ "iMacros" + "\\CLSID");
		String value = Registry.QueryStringValue(key, "");
		Registry.CloseKey(key);
		return new GUID(value);
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimInit() throws COMException {
		Ole32.CoInitialize();
		iimObj = new DispatchPtr(CLSID);
		return ((Integer) iimObj.invoke("iimInit")).intValue();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @param cmd
	 *            String containing desired command line arguments.<br>
	 *            Specifies command line switches. All switches start with a
	 *            dash (-). Possible switches are: IE Plug-in -ie Starts
	 *            Internet Explorer instead of the iMacros Browser. Note that
	 *            the iMacros sidebar need to be open in IE so that it re-opens
	 *            automatically when IE is started. Firefox -fx Starts Mozilla
	 *            Firefox instead of the iMacros Browser. (iMacros for Firefox
	 *            needs to be installed). -tray Starts the iMacros Browser in
	 *            tray mode. -silent Starts the iMacros Browser in silent mode,
	 *            i.e. tray mode without a tray icon. -runner Starts iMacros via
	 *            the iimRunner tool. -iePrivate" (IE only, so "-ie" must be
	 *            given, too) "InPrivate" mode, i.e. seperate instances do not
	 *            share cookies -useragent Change the iMacros user agent string.
	 *            In Firefox, you can use the !USERAGENT variable instead. -key
	 *            Start iMacros with the specified license key, for example as
	 *            Player. This ignores any license key that the user might have
	 *            entered. Currently, this switch is supported in the iMacros
	 *            Browser and IE. For Firefox, a special registry tweak is
	 *            available instead (contact tech support for details).
	 *            Specifically for Firefox there are also the -fxVersion,
	 *            -fxLocale and -fxProfile switches. Typically these specialized
	 *            commands are "only" used for running automated tests with
	 *            various Firefox versions. For IE there is no version switch as
	 *            there can only be one IE version installed on a system.
	 * 
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimInit(String cmd) throws COMException {
		Ole32.CoInitialize();
		iimObj = new DispatchPtr(CLSID);
		return ((Integer) iimObj.invoke("iimInit", cmd)).intValue();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @param startBrowser
	 *            Specifies whether to open a new instance of the iMacros
	 *            Browser, Firefox or Internet Explorer. If set to false,
	 *            iMacros tries to connect to an existing instance. If none is
	 *            found, a new instance is created. Default is true.
	 * @param cmd
	 *            String containing desired command line arguments.<br>
	 *            Specifies command line switches. All switches start with a
	 *            dash (-). Possible switches are: IE Plug-in -ie Starts
	 *            Internet Explorer instead of the iMacros Browser. Note that
	 *            the iMacros sidebar need to be open in IE so that it re-opens
	 *            automatically when IE is started. Firefox -fx Starts Mozilla
	 *            Firefox instead of the iMacros Browser. (iMacros for Firefox
	 *            needs to be installed). -tray Starts the iMacros Browser in
	 *            tray mode. -silent Starts the iMacros Browser in silent mode,
	 *            i.e. tray mode without a tray icon. -runner Starts iMacros via
	 *            the iimRunner tool. -iePrivate" (IE only, so "-ie" must be
	 *            given, too) "InPrivate" mode, i.e. seperate instances do not
	 *            share cookies -useragent Change the iMacros user agent string.
	 *            In Firefox, you can use the !USERAGENT variable instead. -key
	 *            Start iMacros with the specified license key, for example as
	 *            Player. This ignores any license key that the user might have
	 *            entered. Currently, this switch is supported in the iMacros
	 *            Browser and IE. For Firefox, a special registry tweak is
	 *            available instead (contact tech support for details).
	 *            Specifically for Firefox there are also the -fxVersion,
	 *            -fxLocale and -fxProfile switches. Typically these specialized
	 *            commands are "only" used for running automated tests with
	 *            various Firefox versions. For IE there is no version switch as
	 *            there can only be one IE version installed on a system.
	 * @throws COMException
	 */
	public int iimInit(String cmd, boolean startBrowser) throws COMException {
		Ole32.CoInitialize();
		iimObj = new DispatchPtr(CLSID);
		String sb = startBrowser ? "TRUE" : "FALSE";
		return ((Integer) iimObj.invoke("iimInit", cmd, sb)).intValue();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @param startBrowser
	 *            Specifies whether to open a new instance of the iMacros
	 *            Browser, Firefox or Internet Explorer. If set to false,
	 *            iMacros tries to connect to an existing instance. If none is
	 *            found, a new instance is created. Default is true.
	 * @param user
	 *            user account for access to a Windows Service.
	 * @param password
	 *            Password for access to a Windows service.
	 * @param domain
	 * @param cmd
	 *            String containing desired command line arguments.<br>
	 *            Specifies command line switches. All switches start with a
	 *            dash (-). Possible switches are: IE Plug-in -ie Starts
	 *            Internet Explorer instead of the iMacros Browser. Note that
	 *            the iMacros sidebar need to be open in IE so that it re-opens
	 *            automatically when IE is started. Firefox -fx Starts Mozilla
	 *            Firefox instead of the iMacros Browser. (iMacros for Firefox
	 *            needs to be installed). -tray Starts the iMacros Browser in
	 *            tray mode. -silent Starts the iMacros Browser in silent mode,
	 *            i.e. tray mode without a tray icon. -runner Starts iMacros via
	 *            the iimRunner tool. -iePrivate" (IE only, so "-ie" must be
	 *            given, too) "InPrivate" mode, i.e. seperate instances do not
	 *            share cookies -useragent Change the iMacros user agent string.
	 *            In Firefox, you can use the !USERAGENT variable instead. -key
	 *            Start iMacros with the specified license key, for example as
	 *            Player. This ignores any license key that the user might have
	 *            entered. Currently, this switch is supported in the iMacros
	 *            Browser and IE. For Firefox, a special registry tweak is
	 *            available instead (contact tech support for details).
	 *            Specifically for Firefox there are also the -fxVersion,
	 *            -fxLocale and -fxProfile switches. Typically these specialized
	 *            commands are "only" used for running automated tests with
	 *            various Firefox versions. For IE there is no version switch as
	 *            there can only be one IE version installed on a system.
	 * @throws COMException
	 */
	public int iimInit(String cmd, boolean startBrowser, String user,
			String password, String domain) throws COMException {
		Ole32.CoInitialize();
		iimObj = new DispatchPtr(CLSID);
		String sb = startBrowser ? "TRUE" : "FALSE";
		return ((Integer) iimObj.invoke("iimInit", cmd, sb, user, password))
				.intValue();
	}

	/**
	 * Plays the given macro in the Internet Macros browser. If play command is
	 * not executed within 600 seconds, an error occurs.
	 * 
	 * @param macro
	 *            Name (without the .iim suffix) of macro to be played or
	 *            on-the-fly generated iMacros macro command preceeded by CODE:
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimPlay(String macro) throws COMException {
		return ((Integer) iimObj.invoke("iimPlay", macro)).intValue();
	}

	/**
	 * Plays the given macro in the Internet Macros browser.
	 * 
	 * @param macro
	 *            Name (without the .iim suffix) of macro to be played or
	 *            on-the-fly generated iMacros macro command preceeded by CODE:
	 * @param timeout
	 *            Timeout value in seconds, if command is not finished within
	 *            the timeout value, an error occurs
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimPlay(String macro, int timeout) throws COMException {
		return ((Integer) iimObj.invoke("iimPlay", macro, new Integer(timeout)))
				.intValue();
	}

	/**
	 * Displays the given text in a text box on the browser window. If the
	 * command is not completed within three seconds, an error occurs.
	 * 
	 * @param text
	 *            The text to be displayed in the browser.
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimDisplay(String text) throws COMException {
		return ((Integer) iimObj.invoke("iimDisplay", text)).intValue();
	}

	/**
	 * Displays the given text in a text box on the browser window.
	 * 
	 * @param text
	 *            The text to be displayed in the browser.
	 * @param timeout
	 *            Timeout value in seconds, if command is not finished within
	 *            the timeout value, an error occurs
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimDisplay(String text, int timeout) throws COMException {
		return ((Integer) iimObj.invoke("iimDisplay", text,
				new Integer(timeout))).intValue();
	}

	/**
	 * Returns the text associated the error resulting from the most recently
	 * invoked IMacrosWrapper function.
	 * 
	 * @throws COMException
	 * @return The text associated with the last error.
	 */
	public String iimGetLastError() throws COMException {
		return (String) iimObj.invoke("iimGetLastError");
	}

	/**
	 * Returns the text associated with the result of the last extraction
	 * resulting from the most recently invoked IMacrosWrapper function.
	 * 
	 * @throws COMException
	 * @return The text associated with the last error.
	 */

	public String iimGetLastExtract() throws COMException {
		return (String) iimObj.invoke("iimGetLastExtract");
	}

	/**
	 * Returns the text associated with the result of the last extraction
	 * resulting from the most recently invoked IMacrosWrapper function.
	 * 
	 * @param index
	 *            <br>
	 *            0： returns all extracted information at once<br>
	 *            1： returns 1st extracted data<br>
	 *            2： returns 2nd extracted data (and so on)<br>
	 * @throws COMException
	 * @return The text associated with the last error.
	 */

	public String iimGetLastExtract(int index) throws COMException {
		return (String) iimObj.invoke("iimGetLastExtract", index);
	}

	/**
	 * Stores values and settings that are used when Internet Macros is started.
	 * You can use exactly the same commands as for the command line interface.
	 * After IMacrosWrapper.play() is invoked, all settings are erased.
	 * 
	 * @param variable
	 *            Name of the variable whose value is to be set.
	 * @param value
	 *            Value to be assigned to the variable.
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimSet(String variable, String value) throws COMException {
		return ((Integer) iimObj.invoke("iimSet", variable, value)).intValue();
	}

	/**
	 * Closes the Internet Macros browser. If the command is not completed
	 * within three seconds, an error occurs.
	 * 
	 * @param timeout
	 *            Timeout value in seconds, if command is not finished within
	 *            the timeout value, an error occurs
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimExit(int timeout) throws COMException {
		int ret = ((Integer) iimObj.invoke("iimExit", new Integer(timeout)))
				.intValue();
		Ole32.CoUninitialize();
		return ret;
	}

	/**
	 * Closes the Internet Macros browser.
	 * 
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 */
	public int iimExit() throws COMException {
		int ret = ((Integer) iimObj.invoke("iimExit")).intValue();
		Ole32.CoUninitialize();
		return ret;
	}

	/**
	 * Releases the COM library on the current thread. Must be called when you
	 * no longer have need of this IMacrosWrapper instance.
	 * 
	 * @throws COMException
	 * @deprecated
	 */
	public void close() throws COMException {
		Ole32.CoUninitialize();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int init() throws COMException {
		return ((Integer) iimObj.invoke("iimInit")).intValue();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @param cmd
	 *            String containing desired command line arguments.
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int init(String cmd) throws COMException {
		return ((Integer) iimObj.invoke("iimInit", cmd)).intValue();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @param startBrowser
	 *            true to start new browser window; false to use an existing
	 *            browser window.
	 * @param cmd
	 *            String containing desired command line arguments.
	 * @throws COMException
	 * @deprecated
	 */
	public int init(String cmd, boolean startBrowser) throws COMException {
		String sb = startBrowser ? "TRUE" : "FALSE";
		return ((Integer) iimObj.invoke("iimInit", cmd, sb)).intValue();
	}

	/**
	 * Initializes the Internet Macros scripting interface.
	 * 
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @param startBrowser
	 *            true to start new browser window; false to use an existing
	 *            browser window.
	 * @param user
	 *            user account for access to a Windows Service.
	 * @param password
	 *            Password for access to a Windows service.
	 * @param domain
	 * @param cmd
	 *            String containing desired command line arguments.
	 * @throws COMException
	 * @deprecated
	 */
	public int init(String cmd, boolean startBrowser, String user,
			String password, String domain) throws COMException {
		String sb = startBrowser ? "TRUE" : "FALSE";
		return ((Integer) iimObj.invoke("iimInit", cmd, sb, user, password))
				.intValue();
	}

	/**
	 * Plays the given macro in the Internet Macros browser. If play command is
	 * not executed within 600 seconds, an error occurs.
	 * 
	 * @param macro
	 *            Name (without the .iim suffix) of macro to be played or
	 *            on-the-fly generated iMacros macro command preceeded by CODE:
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int play(String macro) throws COMException {
		return ((Integer) iimObj.invoke("iimPlay", macro)).intValue();
	}

	/**
	 * Displays the given text in a text box on the browser window. If the
	 * command is not completed within three seconds, an error occurs.
	 * 
	 * @param text
	 *            The text to be displayed in the browser.
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int display(String text) throws COMException {
		return ((Integer) iimObj.invoke("iimDisplay", text)).intValue();
	}

	/**
	 * Returns the text associated with the status or error condition resulting
	 * from the most recently invoked IMacrosWrapper function.
	 * 
	 * @throws COMException
	 * @return The text associated with the last error.
	 * @deprecated
	 */
	public String getLastMessage() throws COMException {
		return (String) iimObj.invoke("iimGetLastMessage");
	}

	/**
	 * Returns the text associated the error resulting from the most recently
	 * invoked IMacrosWrapper function.
	 * 
	 * @throws COMException
	 * @return The text associated with the last error.
	 * @deprecated
	 */
	public String getLastError() throws COMException {
		return (String) iimObj.invoke("iimGetLastError");
	}

	/**
	 * Returns the text associated with the result of the last extraction
	 * resulting from the most recently invoked IMacrosWrapper function.
	 * 
	 * @throws COMException
	 * @return The text associated with the last error.
	 * @deprecated
	 */
	public String getLastExtracted() throws COMException {
		return (String) iimObj.invoke("iimGetLastExtract");
	}

	/**
	 * Stores values and settings that are used when Internet Macros is started.
	 * You can use exactly the same commands as for the command line interface.
	 * After IMacrosWrapper.play() is invoked, all settings are erased.
	 * 
	 * @param variable
	 *            Name of the variable whose value is to be set.
	 * @param value
	 *            Value to be assigned to the variable.
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int set(String variable, String value) throws COMException {
		return ((Integer) iimObj.invoke("iimSet", variable, value)).intValue();
	}

	/**
	 * Closes the Internet Macros browser.
	 * 
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int exit() throws COMException {
		return ((Integer) iimObj.invoke("iimExit")).intValue();
	}

	/**
	 * Closes ALL running instances of Internet Macros instantly
	 * 
	 * @throws COMException
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @deprecated
	 */
	public int exitAllBrowsers() throws COMException {
		return ((Integer) iimObj.invoke("iimExitAllBrowsers")).intValue();
	}

	/**
	 * 返回版本号 This command is mainly used for debugging purposes, for example if
	 * you create your own iMacros installer package. It retrieves the version
	 * (built) number of the installed Scripting Interface (iimInterface.dll).
	 * 
	 * It returns a number in LONG format such as 80066532. Note that the
	 * iMacros main version number (as shown by VERSION) and the Scripting
	 * Interface version number as shown by iimGetInterfaceVersion are not
	 * identical.
	 * 
	 * V6.65+: The last two digits of the returned version number indicate if
	 * you are using the 32bit or x64 Scripting Interface. Example: 32bit:
	 * 80066532, 64bit: 80066564
	 * 
	 * @return 版本号
	 * @throws COMException
	 */
	public String iimGetInterfaceVersion() throws COMException {
		return (String) iimObj.invoke("iimGetInterfaceVersion").toString();
	}

	/**
	 * 保存图片 V.6.80 and later 可用
	 * 
	 * @param FILEPATH
	 *            保存图片全路径 e.g. "c:\myscreenshots\testpage.png"
	 * @param Code
	 *            0：take screenshot of browser (including browser GUI itself and
	 *            browser error messages) 1：take screenshot of complete webpage
	 *            (webpage only, no browser GUI)
	 * @return a value > 0 if it succeeds, and an error code < 0 in case of
	 *         problems. You can use {@link #getLastMessage()} to retrieve the
	 *         text associated with the last error.
	 * @throws COMException
	 */
	public int iimTakeBrowserScreenshot(String FILEPATH, int Code)
			throws COMException {
		return ((Integer) iimObj.invoke("iimTakeBrowserScreenshot", FILEPATH,
				Code)).intValue();
	}

	/**
	 * 该功能暂时无法实现，注释
	 * 
	 * @param index
	 *            The index of the measurement to get
	 * @param name
	 *            The name of the STOPWATCH label
	 * @param value
	 *            The value of the STOPWATCH time
	 * @return
	 * @throws COMException
	 */
	/*
	 * public int iimGetLastPerformance(int index) throws COMException {
	 * Object[] value = new Object[3]; value[0]=index;
	 * iimObj.invokeN("iimGetLastPerformance", value);
	 * System.out.println("STOPWATCH:" + value[0]+value[1]+value[2]); return
	 * ((Integer) iimObj.invokeN("iimGetLastPerformance", value)).intValue(); }
	 */
}
