package simplegis;

import java.awt.Graphics;

import extern.WorldToScreenTransform;

/**
 * Interface for map objects. Copyright: Copyright (c) 2017.
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public interface IMapObject {
	/**
	 * draw map object.
	 * @param myFactor scaling factor.
	 * @param canvas Graphics object.
	 */
	public void draw(WorldToScreenTransform myFactor, Graphics canvas);
	
	/**
	 * set line color of map object by defining its Red Green Blue values.
	 * @param R red value
	 * @param G green value
	 * @param B blue value
	 */
	public void setLineColor(int R, int G, int B);
}
