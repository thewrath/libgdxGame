package fr.monolog.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Constants {
	private static boolean isConstruct = false;

	//CODE CONSTANTS
	public static final Vector3 CLEAR_COLOR = new Vector3(0,0,0);
	public static final Color DEBUG_COLOR = new Color(0,255,0, 1);

	//DEBUG CONSTANTS
	public static final boolean DEBUG = true;

	//GAME CONSTANTS
	public static String SPRITES_SHEET = "sprite.pngs";
	public static long SEED = 123456789;

	/**
	 * Build the constants class with XML file settings
	 * @param settings
	 */
	public Constants(Document settings){

		if(!isConstruct){
			final Element racine = settings.getDocumentElement();

			SPRITES_SHEET = racine.getElementsByTagName("sprites_sheet").item(0).getTextContent();
			SEED = Long.parseLong(racine.getElementsByTagName("seed").item(0).getTextContent());

		}

		isConstruct = true;
	}
}
