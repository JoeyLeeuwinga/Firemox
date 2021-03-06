/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is the Kowari Metadata Store.
 *
 * The Initial Developer of the Original Code is Plugged In Software Pty
 * Ltd (http://www.pisoftware.com, mailto:info@pisoftware.com). Portions
 * created by Plugged In Software Pty Ltd are Copyright (C) 2001,2002
 * Plugged In Software Pty Ltd. All Rights Reserved.
 *
 * Contributor(s): N/A.
 *
 * [NOTE: The text of this Exhibit A may differ slightly from the text
 * of the notices in the Source Code files of the Original Code. You
 * should use the text of this Exhibit A rather than the text found in the
 * Original Code Source Code for Your Modifications.]
 *
 */
package net.sf.firemox.tools;

/**
 * Class used to launch a WebBrowser.
 * <p>
 * Extracted from method: EmbeddedKowariServer.LaunchBrowser()
 * </p>
 * 
 * @since 2004-07-23
 * @author <a href="mailto:robert.turner@tucanatech.com">Robert Turner</a>
 */
public final class WebBrowser {

	// Windows config
	private static final String ID_WIN = "Windows";

	private static final String PATH_WIN = "rundll32";

	private static final String FLAG_WIN = "url.dll,FileProtocolHandler";

	// MAC config
	private static final String ID_MACOSX = "Mac OS X";

	// UNIX Config
	private static final String FLAG_UNIX = "-remote openURL";

	private static final String[] PATHES_UNIX = { "firefox", "opera",
			"konqueror", "epiphany", "mozilla", "netscape" };

	/**
	 * Constructor. This class uses static methods.
	 */
	private WebBrowser() {
		super();
	}

	/**
	 * Launch a browser to display the specified URL.
	 * 
	 * @param url
	 *          url to open.
	 * @throws Exception
	 */
	public static void launchBrowser(String url) throws Exception {

		// validate URL
		if (url != null && !"".equals(url)) {

			// determine OS
			String os = System.getProperty("os.name");
			boolean windows = os != null && os.startsWith(ID_WIN);
			boolean macosx = os != null && os.startsWith(ID_MACOSX);

			// execute launch command depending on the OS
			if (windows) {

				WebBrowser.launchBrowserWindows(url);
			} else if (macosx) {

				WebBrowser.launchBrowserMac(url);
			} else {

				// default OS is Unix (eg. Linux, BSD, Solaris)
				WebBrowser.launchBrowserUnix(url);
			}
		}
	}

	/**
	 * Executes a Windows command to launch the Browser.
	 * 
	 * @param url
	 *          String
	 * @throws Exception
	 */
	private static void launchBrowserWindows(String url) throws Exception {

		// command = 'rundll32 url.dll,FileProtocolHandler http://...'
		Runtime.getRuntime().exec(PATH_WIN + " " + FLAG_WIN + " " + url);
	}

	/**
	 * Executes a Mac command to launch the Browser.
	 * 
	 * @param url
	 *          String
	 * @throws Exception
	 */
	private static void launchBrowserMac(String url) throws Exception {

		// command = 'open http://...'
		Runtime.getRuntime().exec("open " + url);
	}

	/**
	 * Executes an Unix command to launch the Browser.
	 * 
	 * @param url
	 *          String
	 * @throws Exception
	 */
	private static void launchBrowserUnix(String url) throws Exception {
		for (String path : PATHES_UNIX) {
			try {
				WebBrowser.launchGeckoBrowserUnix(path, url);
				return;
			} catch (Exception ex) {
				// Ignore this error and continue
			}
		}
	}

	/**
	 * Executes an Unix command to launch the specified Gecko-based Browser.
	 * 
	 * @param url
	 *          String
	 * @throws Exception
	 */
	private static void launchGeckoBrowserUnix(String browser, String url)
			throws Exception {

		// command = '*browser* -remote openURL(http://www.javaworld.com)'
		String command = browser + " " + FLAG_UNIX + "(" + url + ")";

		// Under Unix, Browser has to be running for the "-remote" flag to work.
		Process process = Runtime.getRuntime().exec(command);

		// exit code 0, indicates command worked,
		boolean success = (process.waitFor()) == 0;

		// Command failed, start up the browser
		if (!success) {

			// command = '*browser* http://www.javaworld.com'
			command = browser + " " + url;
			Runtime.getRuntime().exec(command);
		}
	}

}
