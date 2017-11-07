package simplegis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * MapPanel is a JPanel which calculates the bounding box and displays the map.
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
class MapPanel extends JPanel {
	public MapPanel() {

		mapLogic = new MapLogic();
		this.setBackground(Color.white);
	}

	/**
	 * Paint map.
	 * 
	 * g Graphics object.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (mapLogic.getObjectCatalog().getAllStreets() != null && mapLogic.getObjectCatalog().getAllRailways() != null
				&& mapLogic.getObjectCatalog().getAllRivers() != null
				&& mapLogic.getObjectCatalog().getAllCities() != null
				&& mapLogic.getObjectCatalog().getAllWaterAreas() != null) {

			if (this.getAll()) {
				mapLogic.getObjectCatalog().paintAll(g, this.getScreenXmin(), this.getScreenYmin(),
						this.getScreenXmax(), this.getScreenYmax());
			} else {
				mapLogic.getObjectCatalog().paintSelection(g, this.getScreenXmin(), this.getScreenYmin(),
						this.getScreenXmax(), this.getScreenYmax());
			}
		}
	}

	/**
	 * Calculate map scale in km. Full map or selected area.
	 */
	public void calcScale() {
		double deltaYr = mapLogic.getObjectCatalog().getTopLeft().y - mapLogic.getObjectCatalog().getBottomRight().y
				+ mapLogic.getObjectCatalog().getBottomRight().y;

		double deltaXr = mapLogic.getObjectCatalog().getBottomRight().x - mapLogic.getObjectCatalog().getTopLeft().x;

		double picW = deltaXr * 2 * 6371 * Math.cos(Math.toRadians(deltaYr)) * Math.PI / 360;
		this.setScale(picW);
	}

	/**
	 * Calculate bounding box if display are changes after zooming.
	 * 
	 * @param factor
	 *            zoom factor
	 * @return false, if zoom limit is reached
	 */
	public boolean zoom(double factor) {
		boolean resp = true;
		setAll(false);
		this.setZoomFactor(factor);
		int xOffset = (int) (((this.getScreenXmax() * this.getZoomFactor()) - this.getScreenXmax()) / 2);
		int yOffset = (int) ((this.getScreenYmax() * this.getZoomFactor() - this.getScreenYmax()) / 2);

		if (this.getScreenXmax() + xOffset > this.getScreenXmin() - xOffset
				&& this.getScreenYmax() + yOffset > this.getScreenYmin() - yOffset)

		{
			this.setScreenXmax(this.getScreenXmax() + xOffset);
			this.setScreenXmin(this.getScreenXmin() - xOffset);
			this.setScreenYmin(this.getScreenYmin() - yOffset);
			this.setScreenYmax(
					(int) (this.getScreenYmin() + (this.getScreenXmax() - this.getScreenXmin()) * mapLogic.getRatio()));
		} else {
			resp = false;
		}
		return resp;
	}

	public void setScreenXmin(int xmin) {
		screenXmin = xmin;
	}

	public int getScreenXmin() {
		return this.screenXmin;

	}

	public void setScreenYmin(int ymin) {
		screenYmin = ymin;
	}

	public int getScreenYmin() {
		return this.screenYmin;

	}

	public void setScreenXmax(int xmax) {
		screenXmax = xmax;
	}

	public int getScreenXmax() {
		return this.screenXmax;
	}

	public void setScreenYmax(int ymax) {
		screenYmax = ymax;
	}

	public int getScreenYmax() {
		return this.screenYmax;
	}

	public void setScreenYmaxOrig(int ymax) {
		screenYmaxOrig = ymax;
	}

	public int getScreenYmaxOrig() {
		return this.screenYmaxOrig;
	}

	public void setZoomFactor(double factor) {
		zoomFactor = factor;
	}

	public double getZoomFactor() {
		return zoomFactor;
	}

	public void setAll(boolean status) {
		all = status;
	}

	public boolean getAll() {
		return all;
	}

	public MapLogic getLogic() {
		return mapLogic;
	}

	public double getScale() {
		return this.scale;
	}

	public void setScale(double s) {
		this.scale = s;
	}
	
	MapLogic getMapLogic() {
		return mapLogic;
	}

	private double scale;

	private MapLogic mapLogic;
	
	private int screenXmin;
	{
		this.setScreenXmin(0);
	}
	private int screenYmin;
	{
		this.setScreenYmin(0);
	}
	private int screenXmax;
	{
	}
	private int screenYmax;

	private int screenYmaxOrig;

	private double zoomFactor;
	{
		this.setZoomFactor(1);
	}

	private boolean all;
	{
		this.setAll(true);
	}
}