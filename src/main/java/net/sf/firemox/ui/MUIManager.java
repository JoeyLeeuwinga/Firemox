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
package net.sf.firemox.ui;

/**
 * @author Fabrice Daugan
 * @since 0.3
 */
public interface MUIManager {

	/**
	 * The class name associated to the Metal L&F
	 */
	String LF_METAL_CLASSNAME = "javax.swing.plaf.metal.MetalLookAndFeel";

	/**
	 * The substance Look&Feel class name
	 */
	String LF_SUBSTANCE_CLASSNAME = "org.jvnet.substance.SubstanceLookAndFeel";
}