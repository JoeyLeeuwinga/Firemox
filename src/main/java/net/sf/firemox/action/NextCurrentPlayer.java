/*
 * Created on Nov 9, 2004 
 * Original filename was NextCurrentPlayer.java
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
package net.sf.firemox.action;

import java.io.IOException;
import java.io.InputStream;

import net.sf.firemox.action.handler.StandardAction;
import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.player.Player;
import net.sf.firemox.event.context.ContextEventListener;
import net.sf.firemox.stack.EventManager;
import net.sf.firemox.test.TestOn;

/**
 * Set the current player after the current phase <br>
 * 
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.80
 */
class NextCurrentPlayer extends UserAction implements StandardAction {

	/**
	 * Create an instance of NextCurrentPlayer by reading a file Offset's file
	 * must pointing on the first byte of this action <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>nextPlayer [TestOn]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          file containing this action
	 * @throws IOException
	 *           if error occurred during the reading process from the specified
	 *           input stream
	 */
	NextCurrentPlayer(InputStream inputFile) throws IOException {
		super(inputFile);
		nextPlayer = TestOn.deserialize(inputFile);
	}

	@Override
	public final Actiontype getIdAction() {
		return Actiontype.NEXT_CURRENT_PLAYER;
	}

	public boolean play(ContextEventListener context, Ability ability) {
		EventManager.nextCurrentPlayer = ((Player) nextPlayer.getTargetable(
				ability, context, null)).idPlayer;
		return true;
	}

	@Override
	public String toString(Ability ability) {
		return "set current player";
	}

	/**
	 * Player who would lose the play
	 */
	private TestOn nextPlayer;

}