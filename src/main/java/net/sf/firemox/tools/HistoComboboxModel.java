/*
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
package net.sf.firemox.tools;

import javax.swing.DefaultComboBoxModel;

import net.sf.firemox.token.IdTokens;
import net.sf.firemox.token.MCommonVars;

/**
 * HistoComboboxModel.java Created on 17 d�c. 2003
 * 
 * @author Fabrice Daugan
 * @since 0.53
 */
public class HistoComboboxModel extends DefaultComboBoxModel {

	/**
	 * redefines this
	 * 
	 * @param obj
	 */
	@Override
	public void addElement(Object obj) {
		super.addElement("" + MCommonVars.registers[IdTokens.TURN_ID] + ": " + obj);
		this.setSelectedItem(obj);
	}
}