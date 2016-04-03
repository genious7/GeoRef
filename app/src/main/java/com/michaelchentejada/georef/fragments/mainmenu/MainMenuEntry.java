package com.michaelchentejada.georef.fragments.mainmenu;

/**
 * <p> An object that represents an entry in the Main Menu</p> Created by Michael Chen on
 * 04/02/2016.
 */
public class MainMenuEntry {
	public int icon;

	/**
	 * The text to display in the menu; this entry also doubles as the MenuEntry id and must be
	 * unique.
	 */
	public int text;

	public MainMenuEntry(int text, int icon) {
		this.text = text;
		this.icon = icon;
	}
}
