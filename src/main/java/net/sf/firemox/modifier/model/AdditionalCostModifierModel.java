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
 * 
 */
package net.sf.firemox.modifier.model;

import java.io.IOException;
import java.io.InputStream;

import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.card.MCard;
import net.sf.firemox.event.context.ContextEventListener;
import net.sf.firemox.modifier.AdditionalCostModifier;
import net.sf.firemox.modifier.ModifierContext;
import net.sf.firemox.stack.AdditionalCost;

/**
 * An additional-cost modifier activated in the stack zone.
 * 
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.93
 */
public class AdditionalCostModifierModel extends ModifierModel {

	/**
	 * The additional cost attached to this modifier.
	 */
	private final AdditionalCost additionalCost;

	/**
	 * Creates a new instance of AdditionalCostModifierModel <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>[super]</li>
	 * <li>additional cost [AdditionalCost]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          is the file containing this event
	 * @throws IOException
	 *           if error occurred during the reading process from the specified
	 *           input stream
	 */
	AdditionalCostModifierModel(InputStream inputFile) throws IOException {
		super(inputFile);
		additionalCost = new AdditionalCost(inputFile);
	}

	@Override
	public ModifierModel clone() {
		throw new InternalError("not yet implemented");
	}

	@Override
	protected void addModifierFromModelPriv(Ability ability, MCard target) {
		final AdditionalCostModifier newModifier = new AdditionalCostModifier(
				new ModifierContext(this, target, ability), additionalCost);
		newModifier.refresh();
	}

	@Override
	public String toHtmlString(Ability ability, ContextEventListener context) {
		return additionalCost.toHtmlString(ability, context);
	}
}
