/*******************************************************************************
 * Copyright (c) 2001, 2007 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Mathew A. Nelson
 *     - Initial API and implementation
 *     Flemming N. Larsen
 *     - Code cleanup
 *     - Removed check for the system property "SINGLEBUFFER", as it is not used
 *       anymore
 *     - Replaced the noDisplay with manager.setEnableGUI() and isGUIEnabled()
 *     - Replaced the -fps option with the -tps option
 *     - Added -nosound option and disables sound i the -nogui is specified
 *     - Updated to use methods from WindowUtil, FileUtil, Logger, which replaces
 *       methods that has been (re)moved from the robocode.util.Utils class
 *     - Moved the printRunningThreads() from robocode.util.Utils into this class
 *       and added javadoc for it
 *     - Added playing theme music at the startup, if music is provided
 *     - Changed to use FileUtil.getRobotsDir()
 *******************************************************************************/
package robocode;




import java.net.URL;
import java.util.Properties;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import netscape.javascript.JSObject;
import appletComponentArch.DynamicTreePanel;
import robocode.battle.BattleProperties;
import robocode.dialog.WindowUtil;
import robocode.io.FileUtil;
import robocode.io.Logger;
import robocode.manager.RobocodeManager;
import robocode.render.ImageUtil;
import robocode.security.RobocodeSecurityManager;
import robocode.security.RobocodeSecurityPolicy;
import robocode.security.SecureInputStream;
import robocode.security.SecurePrintStream;
import robocode.util.LogUtil;


/**
 * Robocode - A programming game involving battling AI tanks.<br>
 * Copyright (c) 2001, 2007 Mathew A. Nelson and Robocode contributors
 *
 * @see <a target="_top" href="http://robocode.sourceforge.net">robocode.sourceforge.net</a>
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
/**
 * 
 * @author Maneesh Abraham (contributor)
 *
 */
public class Robocode extends JApplet{

	private RobocodeManager manager;

	public static String selected_robotsx;

	/**
	 * Use the command-line to start Robocode.
	 * The command is:
	 * <pre>
	 *    java -Xmx512M -Dsun.io.useCanonCaches=false -jar libs/robocode.jar
	 * </pre>
	 *
	 * @param args an array of command-line arguments
	 */
	public static void main(String[] args) {

		Robocode robocode = new Robocode();
		robocode.initialize(selected_robotsx);
	}
	@Override
	public void init() {
		setSize( 800, 600);
		//Execute a job on the event-dispatching thread; creating this applet's GUI.
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {


				}
			});
		} catch (Exception e) { 
			System.err.println("createGUI didn't complete successfully");
		}
	}
	public Robocode() {}
	@Override
	public void start() {
		Robocode robocode = new Robocode();

		URL url = getDocumentBase();
		FileUtil.setUrl(getCodeBase());

		selected_robotsx = getParameter("sel_robots"); 

		JSObject window = JSObject.getWindow(this);
		String summary = "Robocode Battle";
		LogUtil.setWindow(window);
		//LogUtil.log("codebase url:: " + url.getFile());
		LogUtil.log(summary);
		
		Properties prop = System.getProperties(); 
		System.out.println(prop.getProperty("java.class.path"));
		System.out.println(url.getFile());
		JPanel newContentPane = robocode.initialize(selected_robotsx); 
		setContentPane(newContentPane); 
	}
	public JPanel initialize(String selected_robots) {
		try {
			manager = new RobocodeManager(false, null);

			Thread.currentThread().setName("Application Thread");


			BattleProperties battleProperties = manager.getBattleManager().getBattleProperties();
			//battleProperties.setSelectedRobots("test.mtest12,test.mtest14");
			battleProperties.setSelectedRobots(selected_robots);
			manager.getBattleManager().startNewBattle(battleProperties, true, false);
			manager.getBattleManager().getBattle().setDesiredTPS(20);


			JPanel panel = manager.getWindowManager().getRobocodeFrame().getRobocodeContentPane();
			panel.setBounds(100, 100, 500, 600);
			panel.setOpaque(true); 
			return panel;
		} catch (Throwable e) {
			Logger.log(e);
			return null;
		}
	}


}
