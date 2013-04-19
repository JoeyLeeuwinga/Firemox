/*
 * SupEqual.java 
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
package net.sf.firemox.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import net.sf.firemox.annotation.XmlTestElement;
import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.Target;
import net.sf.firemox.expression.Expression;

/**
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.86
 */
@XmlTestElement(id = IdTest.SUP_EQUAL)
public class SupEqual extends TestExpr {

	/**
	 * Create an instance of SupEqual by reading a file. Offset's file must
	 * pointing on the first byte of this test <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>left value,idToken [2]</li>
	 * <li>right value, idToken [2]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          is the file containing this event
	 * @throws IOException
	 *           if error occurred during the reading process from the specified
	 *           input stream
	 */
	SupEqual(InputStream inputFile) throws IOException {
		super(inputFile);
	}

	/**
	 * Create a new instance of SupEqual given left and right expression.
	 * 
	 * @param leftExpression
	 *          the left expression of this test.
	 * @param rightExpression
	 *          the right expression of this test.
	 */
	private SupEqual(Expression leftExpression, Expression rightExpression) {
		super(leftExpression, rightExpression);
	}

	@Override
	public Test getConstraintTest(Map<String, Expression> values) {
		return new SupEqual(leftExpression.getConstraintExpression(values),
				rightExpression.getConstraintExpression(values));
	}

	@Override
	public boolean test(Ability ability, Target tested) {
		return getLeftValue(ability, tested) >= getRightValue(ability, tested);
	}

	@Override
	public String toString() {
		return "(" + leftExpression.toString() + " >= "
				+ rightExpression.toString() + ")";
	}
}