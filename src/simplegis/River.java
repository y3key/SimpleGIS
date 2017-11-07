package simplegis;

import extern.*;
import java.awt.*;
import java.security.InvalidParameterException;

/**
 * /** River class for handling map objects of type River. 
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public class River implements IMapObject {

	public River(int num) {
		coords = new DPoint[num];
	}

	/**
	 * Returns coordinates for test/debug.
	 * 
	 * @return String ID and die coordinates of one object.
	 */
	public String toString() {
		String answer = "River ID=" + this.getId() + "\n";
		for (int i = 0; i < coords.length; i++) {
			answer = answer + "Coord" + (i + 1) + " x=" + this.getCoords(i).getX() + " y=" + this.getCoords(i).getY()
					+ " \n";
		}

		return answer;
	}

	/**
	 * Draws a River object.
	 * 
	 * @param factor
	 *            defines transformation
	 * @param canvas
	 *            Graphics to draw
	 */
	public void draw(WorldToScreenTransform factor, Graphics canvas) {
		if (factor == null || canvas == null) {
			throw new InvalidParameterException();
		}
		int[] xVal = new int[getCoords().length];
		int[] yVal = new int[getCoords().length];
		DPoint[] point = new DPoint[getCoords().length];

		for (int i = 0; i < getCoords().length; i++) {
			point[i] = factor.getDestPoint(this.getCoords(i));
			xVal[i] = (int) point[i].x;
			yVal[i] = (int) point[i].y;

		}

		canvas.setColor(this.lineColor);
		canvas.drawPolyline(xVal, yVal, this.getCoords().length);

	}

	public DPoint getCoords(int i) {
		if (i > coords.length || i < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return coords[i];
	}

	public void setCoords(DPoint coord, int nr) {
		if (nr > coords.length || nr < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		coords[nr] = coord;
	}

	public int getId() {
		return id;

	}

	public void setId(int newId) {
		id = newId;
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

	private Color lineColor;
	{
		this.setLineColor(0, 0, 255);
	}
}