/* 
 * SkipPhase.java 
 * Created on 20 f�vr. 2004
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
import net.sf.firemox.expression.Expression;
import net.sf.firemox.expression.ExpressionFactory;
import net.sf.firemox.stack.MPhase;
import net.sf.firemox.stack.StackManager;

/**
 * Skip a specified phase following it's identifier. The specified phase name
 * must have been previously defined in the source file or include. The target
 * list is used to known the player(s) phase to skip.
 * 
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.1
 */
class SkipPhase extends UserAction implements StandardAction {

	/**
	 * Create an instance of SkipPhase by reading a file Offset's file must
	 * pointing on the first byte of this action <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>phase [Expression]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          file containing this action
	 * @throws IOException
	 *           if error occurred during the reading process from the specified
	 *           input stream
	 */
	SkipPhase(InputStream inputFile) throws IOException {
		super(inputFile);
		phase = ExpressionFactory.readNextExpression(inputFile);
	}

	@Override
	public final Actiontype getIdAction() {
		return Actiontype.SKIP_PHASE;
	}

	public boolean play(ContextEventListener context, Ability ability) {
		int idPhase = phase.getValue(ability, null, context);
		for (int i = StackManager.getInstance().getTargetedList().size(); i-- > 0;) {
			int idPlayer = ((Player) StackManager.getInstance().getTargetedList()
					.get(i)).idPlayer;
			for (int j = MPhase.phases[idPlayer].length; j-- > 0;) {
				if (MPhase.phases[idPlayer][j].phaseType.id == idPhase) {
					MPhase.phases[idPlayer][j].skipThisPhase = true;
				}
			}
		}
		return true;
	}

	@Override
	public String toString(Ability ability) {
		return "skip phase";
	}

	/**
	 * The phase index of the targeted player to skip
	 */
	private Expression phase;

}