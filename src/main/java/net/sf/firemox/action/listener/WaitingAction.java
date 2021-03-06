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
package net.sf.firemox.action.listener;

import net.sf.firemox.clickable.action.JChosenAction;

/**
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.86
 */
public interface WaitingAction extends Waiting {

	/**
	 * Called to specify the triggered card chosen for the current action by the
	 * handed player
	 * 
	 * @param action
	 *          the clicked action
	 * @return true if this click has been managed. Return false if this click has
	 *         been ignored
	 */
	boolean clickOn(JChosenAction action);

	/**
	 * This function should be called by the 'clickOn' caller in case of the
	 * specified action has been handled during the checking validity of this
	 * click in the <code>clickOn(JChosenAction)</code> function. <br>
	 * <ul>
	 * The calls chain is :
	 * <li>actionListener call clickOn(JChosenAction)
	 * <li>if returned value is false we give hand to the player and exit, else
	 * we continue
	 * <li>actionListener call succeedClickOn(JChosenAction)
	 * </ul>
	 * 
	 * @param action
	 *          the action that was clicked and successfully handled by the
	 *          <code>clickOn(JChosenAction)</code> function.
	 * @return true if this action is completed
	 */
	boolean succeedClickOn(JChosenAction action);

	void finished();

	boolean manualSkip();
}
