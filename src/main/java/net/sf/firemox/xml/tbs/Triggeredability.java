/*
 * Created on 27 f�vr. 2005
 * 
 *   Firemox is a turn based strategy simulator
 *   Copyright (C) 2003-2007 Fabrice Daugan
 *
 *   This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 *
 *   This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details.
 *
 *   You should have received a copy of the GNU General Public License along  
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sf.firemox.xml.tbs;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.firemox.clickable.ability.AbilityType;
import net.sf.firemox.event.Event;
import net.sf.firemox.xml.XmlEvent;
import net.sf.firemox.xml.XmlParser;
import net.sf.firemox.xml.XmlTbs;
import net.sf.firemox.xml.XmlTest;
import net.sf.firemox.xml.XmlToMDB;
import net.sf.firemox.xml.XmlTools;

/**
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.82
 */
public class Triggeredability implements XmlToMDB {

	/**
	 * <ul>
	 * Structure of stream : Data[size]
	 * <li>ability [Ability]</li>
	 * <li>event [Event]</li>
	 * <li>cost [Action[]]</li>
	 * <li>effect [Action[]]</li>
	 * </ul>
	 * 
	 * @param node
	 *          the XML ability structure
	 * @param out
	 *          output stream where the card structure will be saved
	 * @return the amount of written action in the output.
	 * @throws IOException
	 *           error while writing.
	 * @see net.sf.firemox.clickable.ability.TriggeredAbility
	 */
	public final int buildMdb(XmlParser.Node node, OutputStream out)
			throws IOException {
		XmlParser.Node when = node.get("when");
		// write the type
		if (when != null) {
			Ability.buildAbility(AbilityType.TRIGGERED_ABILITY_SET, node, out);
		} else {
			Ability.buildAbility(AbilityType.TRIGGERED_ABILITY, node, out);
		}

		// write the event
		if (when != null)
			Event.NEVER_ACTIVATED.write(out);
		else {
			XmlTools.defaultOnMeTag = false;
			for (Object obj : node) {
				if (obj instanceof XmlParser.Node) {
					XmlParser.Node child = (XmlParser.Node) obj;
					XmlEvent.getEvent(child.getTag()).buildMdb(child, out);
					break;
				}
			}
		}

		// write the actions cost+effects
		XmlTbs.currentInEffect = false;
		XmlTbs.writeActionList(node.get("cost"), out);
		XmlTbs.currentInEffect = true;
		XmlTbs.writeActionList(node.get("effects"), out);

		if (when != null) {
			// A triggered ability activated when some condition become true
			XmlTools.defaultOnMeTag = false;
			XmlTest.getTest("test").buildMdb(when, out);
		}
		return 0;
	}
}
