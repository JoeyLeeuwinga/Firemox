/*
 * Created on 4 f�vr. 2005
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
package net.sf.firemox.action.intlist;

import java.io.IOException;
import java.io.InputStream;

import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.event.context.ContextEventListener;
import net.sf.firemox.expression.Expression;
import net.sf.firemox.expression.ExpressionFactory;
import net.sf.firemox.stack.StackManager;
import net.sf.firemox.tools.IntegerList;

/**
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.82
 */
class IntListSaveIndex extends IntListSave {

	/**
	 * Create an instance of IntListSaveIndex by reading a file Offset's file must
	 * pointing on the first byte of this action <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>idAction [1]</li>
	 * <li>idType [1]</li>
	 * <li>index : Expression [...]</li>
	 * <li>list-index : Expression [...]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          file containing this action
	 * @throws IOException
	 *           If some other I/O error occurs
	 */
	IntListSaveIndex(InputStream inputFile) throws IOException {
		super(inputFile);
		index = ExpressionFactory.readNextExpression(inputFile);
		listIndex = ExpressionFactory.readNextExpression(inputFile);
	}

	@Override
	public boolean play(ContextEventListener context, Ability ability) {
		final int listIndex = this.listIndex.getValue(ability, null, context);
		final int index = this.index.getValue(ability, null, context);
		if (listIndex < 0) {
			IntegerList toInsert = StackManager.intList.clone();
			if (index < 0) {
				StackManager.intList.addAll(toInsert);
			} else {
				StackManager.intList.addAll(index, toInsert);
			}
		} else {
			if (index < 0) {
				StackManager.SAVED_INT_LISTS.get(listIndex).removeLastInt();
			} else {
				StackManager.SAVED_INT_LISTS.get(listIndex).remove(index);
			}
		}
		return true;
	}

	@Override
	public String toString(Ability ability) {
		return "int-list.save-insertListAtIndex";
	}

	/**
	 * The index of target to save.
	 */
	private Expression index;

	/**
	 * The index of list where this operation would be applied on. If this index
	 * is negative ie. -1, the operation would be applied on current target-list.
	 */
	private Expression listIndex;

}
