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
package net.sf.firemox.action.targetlist;

import java.io.IOException;
import java.io.InputStream;

import net.sf.firemox.action.context.ActionContextWrapper;
import net.sf.firemox.action.context.Wrapper;
import net.sf.firemox.action.handler.FollowAction;
import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.event.context.ContextEventListener;
import net.sf.firemox.stack.StackManager;
import net.sf.firemox.stack.TargetedList;

/**
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.82
 */
class TargetListClear extends net.sf.firemox.action.targetlist.TargetList
		implements FollowAction {

	/**
	 * Create an instance of TargetListClear by reading a file Offset's file must
	 * pointing on the first byte of this action <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>[super]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          file containing this action
	 * @throws IOException
	 *           If some other I/O error occurs
	 */
	TargetListClear(InputStream inputFile) throws IOException {
		super(inputFile);
	}

	@Override
	public boolean play(ContextEventListener context, Ability ability) {
		final int listIndex = getlistIndex(ability, context);
		if (listIndex < 0) {
			StackManager.getInstance().getTargetedList().clear();
		} else {
			StackManager.SAVED_TARGET_LISTS.get(listIndex).clear();
		}
		return true;
	}

	@Override
	public String toString(Ability ability) {
		return "";
	}

	public void simulate(ActionContextWrapper actionContext,
			ContextEventListener context, Ability ability) {
		final Wrapper<TargetedList> targetContext = new Wrapper<TargetedList>();
		actionContext.actionContext = targetContext;
		final int listIndex = getlistIndex(ability, context);
		if (listIndex < 0) {
			targetContext.setObject(StackManager.getInstance().getTargetedList()
					.cloneList());
		}
		play(context, ability);
	}

	public void rollback(ActionContextWrapper actionContext,
			ContextEventListener context, Ability ability) {
		final TargetedList targetContext = (TargetedList) ((Wrapper<?>) actionContext.actionContext)
				.getObject();
		final int listIndex = getlistIndex(ability, context);
		if (listIndex < 0) {
			StackManager.getInstance().setTargetedList(targetContext);
		}
	}

}
