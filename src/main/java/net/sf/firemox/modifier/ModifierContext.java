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
package net.sf.firemox.modifier;

import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.card.MCard;
import net.sf.firemox.modifier.model.ModifierModel;

/**
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.95
 */
public class ModifierContext {

	/**
	 * Create a new instance of this class.
	 * 
	 * @param modifierModel
	 *          the modifier model.
	 * @param to
	 *          the component to be attached to
	 * @param ability
	 *          is the ability owning this test. The card component of this
	 *          ability should correspond to the card owning this test too.
	 */
	public ModifierContext(ModifierModel modifierModel, MCard to, Ability ability) {
		this.modifierModel = modifierModel;
		this.to = to;
		this.ability = ability;
	}

	/**
	 * Target attached to.
	 */
	private final MCard to;

	/**
	 * The ability having created this modifier.
	 */
	private Ability ability;

	/**
	 * The modifier model.
	 */
	private ModifierModel modifierModel;

	/**
	 * Return The modifier model.
	 * 
	 * @return The modifier model.
	 */
	public ModifierModel getModel() {
		return modifierModel;
	}

	/**
	 * Return the target component.
	 * 
	 * @return the target component.
	 */
	public MCard getTo() {
		return this.to;
	}

	/**
	 * Return The ability having created this modifier.
	 * 
	 * @return The ability having created this modifier.
	 */
	public Ability getAbility() {
		return this.ability;
	}
}
