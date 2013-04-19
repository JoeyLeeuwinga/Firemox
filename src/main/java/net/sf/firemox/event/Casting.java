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
package net.sf.firemox.event;

import java.io.IOException;
import java.io.InputStream;

import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.card.MCard;
import net.sf.firemox.event.context.MContextTarget;
import net.sf.firemox.test.Or;
import net.sf.firemox.test.Test;

/**
 * MEventCasting.java Created on 29 janv. 2004
 * 
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.54
 */
public class Casting extends TriggeredEvent {

	/**
	 * Create an instance of MEventCasting by reading a file Offset's file must
	 * pointing on the first byte of this event <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>idZone [1]</li>
	 * <li>test [...]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          is the file containing this event
	 * @param card
	 *          is the card owning this event
	 * @throws IOException
	 *           if error occurred during the reading process from the specified
	 *           input stream
	 */
	Casting(InputStream inputFile, MCard card) throws IOException {
		super(inputFile, card);
	}

	/**
	 * Creates a new instance of MEventCasting specifying all attributes of this
	 * class. All parameters are copied, not cloned. So this new object shares the
	 * card and the specified codes
	 * 
	 * @param idZone
	 *          the place constraint to activate this event
	 * @param test
	 *          the test of this event
	 * @param card
	 *          is the card owning this card
	 */
	private Casting(int idPlace, Test test, MCard card) {
		super(idPlace, test, card);
	}

	/**
	 * Return a copy of this with the specified owner
	 * 
	 * @param card
	 *          is the card of the ability of this event
	 * @return copy of this event
	 */
	@Override
	public MEventListener clone(MCard card) {
		return new Casting(idZone, test, card);
	}

	/**
	 * Tell if the current event matches with this event. If there is an
	 * additional code to check, it'would be checked if the main event matches
	 * with the main event
	 * 
	 * @param ability
	 *          is the ability owning this test. The card component of this
	 *          ability should correspond to the card owning this test too.
	 * @param castCard
	 *          the casting card
	 * @return true if the current event match with this event
	 */
	public boolean isMatching(Ability ability, MCard castCard) {
		return test(ability, castCard);
	}

	/**
	 * Dispatch this event to all active event listeners able to understand this
	 * event. The listening events able to understand this event are <code>this
	 * </code>
	 * and other multiple event listeners. For each event listeners having
	 * responded they have been activated, the corresponding ability is added to
	 * the triggered buffer zone of player owning this ability
	 * 
	 * @param castCard
	 *          cast card
	 * @see #isMatching(Ability, MCard)
	 */
	public static void dispatchEvent(MCard castCard) {
		for (Ability ability : TRIGGRED_ABILITIES.get(EVENT)) {
			if (((Casting) ability.eventComing()).isMatching(ability, castCard)
					&& ability.isMatching()) {
				ability.triggerIt(new MContextTarget(castCard));
			}
		}
	}

	@Override
	public final Event getIdEvent() {
		return EVENT;
	}

	/**
	 * The event type.
	 */
	public static final Event EVENT = Event.CASTING;

	/**
	 * Create and returns an union of this event and the specified one. Both event
	 * must have the same type. Test(s) and events attributes may be groupped
	 * depending instance of this event. If no possible append is possible
	 * <code>null</code> is returned.
	 * 
	 * @param other
	 *          the event to append with 'or' operator.
	 * @return a new event reprensenting 'this' or 'other'
	 */
	@Override
	public MEventListener appendOr(MEventListener other) {
		return new Casting(idZone, Or.append(this.test, other.test), card);
	}
}