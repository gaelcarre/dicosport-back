package fr.gaelcarre.dicosport.pojo.utils;

import java.awt.Color;

public class ColorUtils {

	public static String getHexa(Color c) {
		return (Integer.toHexString(c.getRed()) + Integer.toHexString(c.getGreen()) + Integer.toHexString(c.getBlue()));
	}

}
