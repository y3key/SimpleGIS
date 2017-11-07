package simplegis;

import extern.*;
import java.awt.*;
import java.security.InvalidParameterException;

/**
 * City class for handling map objects of type City.
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */

public class City implements IMapObject {

	/**
	 * Instantiates array with DPoint objects for City coordinates.
	 * 
	 * @param num
	 *            number of coordinates
	 */
	public City(int num) {
		coords = new DPoint[num];
	}

	/**
	 * Returns coordinates for test/debug.
	 * 
	 * @return String ID and die coordinates of one object.
	 */
	public String toString() {
		String answer = "City ID" + this.getId() + "\n";
		for (int i = 0; i < coords.length; i++) {
			answer = answer + "Coord" + (i + 1) + " x: " + this.getCoords(i).getX() + " y: " + this.getCoords(i).getY()
					+ " \n";
		}

		return answer;
	}

	/**
	 * Draws a City object.
	 * 
	 * @param myFactor
	 *            defines transformation
	 * @param canvas
	 *            Graphics to draw
	 */
	public void draw(WorldToScreenTransform myFactor, Graphics canvas) {
		if (myFactor == null || canvas == null) {
			throw new InvalidParameterException();
		}
		int[] xVal = new int[getCoords().length];
		int[] yVal = new int[getCoords().length];
		DPoint[] coords = new DPoint[getCoords().length];
		for (int i = 0; i < getCoords().length; i++) {
			coords[i] = myFactor.getDestPoint(this.getCoords(i));
			xVal[i] = (int) coords[i].x;
			yVal[i] = (int) coords[i].y;

		}

		canvas.setColor(this.getLineColor());
		canvas.fillPolygon(xVal, yVal, this.getCoords().length);
	}

	public DPoint getCoords(int i) {
		if (i>coords.length || i<0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return coords[i]; // TODO error handling
	}

	public void setCoords(DPoint coord, int nr) {
		if (nr>coords.length || nr<0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		coords[nr] = coord;
	}

	public int getId() {
		return id;

	}

	public void setId(int myId) {
		id = myId;
	}

	public DPoint[] getCoords() {
		return coords;
	}

	/**
	 * sets line color with red green blue values.
	 *
	 * @param R:
	 *            red value (0 - 255).
	 * @param G:
	 *            green value (0 - 255).
	 * @param B:
	 *            blue value (0 - 255).
	 */
	public void setLineColor(int R, int G, int B) {
		try {
			lineColor = new Color(R, G, B);
		} catch (IllegalArgumentException e) {
			// ignore (don't change color)
			System.err.println("City: Color parameters out of range (0-155): R=" + R + ", G=" + G + ", B=" + B);
		}
	}

	public Color getLineColor() {
		return lineColor;
	}

	private int id;

	private DPoint[] coords;

	private Color lineColor = new Color (150,100,0);
/*	{
		this.setLineColor(150, 100, 0);
	}*/
}
