package simplegis;

import extern.DPoint;

/**
* Interface for map object catalogs. Copyright: Copyright (c) 2017.
* 
* @author Stefan Friese
* @version 1.1
*/
public interface IMapObjectCatalogWindow {
	
	/**
	 * get Top Left coordinate of bounding box.
	 * @return DPoint coordinate.
	 */
	public DPoint getTopLeft();
	
	/**
	 * set Top Left coordinates of bounding box.
	 * @param tl DPoint coordinate
	 */
	public void setTopLeft(DPoint tl);
	
	/**
	 * get Bottom Right coordinate of bounding box.
	 * @return DPoint coordinate.
	 */
	public DPoint getBottomRight();
	
	/**
	 * set Bottom Right coordinates of bounding box.
	 * @param br DPoint coordinate.
	 */
	public void setBottomRight(DPoint br);
}
