package simplegis;

import extern.*;
import java.awt.*;

/**
 * MapPanel collects all map objects. 
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public class ObjectCatalog {

	public ObjectCatalog() {
	}

	public void setAll(String myPath) {
		this.setAllStreets(myPath);
		this.setAllRailways(myPath);
		this.setAllRivers(myPath);
		this.setAllCities(myPath);
		this.setAllWaterAreas(myPath);
	}

	public void setAllStreets(String myPath) {
		allStreets = new StreetCatalog(myPath);
	}

	public StreetCatalog getAllStreets() {
		return allStreets;
	}

	public void setAllRailways(String myPath) {
		allRailways = new RailwayCatalog(myPath);
	}

	public RailwayCatalog getAllRailways() {
		return allRailways;
	}

	public void setAllRivers(String myPath) {
		allRivers = new RiverCatalog(myPath);
	}

	public RiverCatalog getAllRivers() {
		return allRivers;
	}

	public void setAllWaterAreas(String myPath) {
		allWaterAreas = new WaterAreaCatalog(myPath);
	}

	public WaterAreaCatalog getAllWaterAreas() {
		return allWaterAreas;
	}

	public void setAllCities(String myPath) {
		allCities = new CityCatalog(myPath);
	}

	public CityCatalog getAllCities() {
		return allCities;
	}

	public DPoint getTopLeft() {
		return topLeft;
	}

	public void defineTopLeft() {
		topLeft.x = 90;

		if (allStreets.getPaintStatus() && topLeft.x > allStreets.getTopLeft().x) {
			topLeft.x = allRailways.getTopLeft().x;
		}
		if (allRailways.getPaintStatus() && topLeft.x > allRailways.getTopLeft().x) {
			topLeft.x = allRailways.getTopLeft().x;
		}
		if (allCities.getPaintStatus() && topLeft.x > allCities.getTopLeft().x) {
			topLeft.x = allCities.getTopLeft().x;
		}
		if (allWaterAreas.getPaintStatus() && topLeft.x > allWaterAreas.getTopLeft().x) {
			topLeft.x = allWaterAreas.getTopLeft().x;
		}
		if (allRivers.getPaintStatus() && topLeft.x > allRivers.getTopLeft().x) {
			topLeft.x = allRivers.getTopLeft().x;
		}

		topLeft.y = 0;
		if (allStreets.getPaintStatus() && topLeft.y < allStreets.getTopLeft().y) {
			topLeft.y = allStreets.getTopLeft().y;
		}
		if (allRailways.getPaintStatus() && topLeft.y < allRailways.getTopLeft().y) {
			topLeft.y = allRailways.getTopLeft().y;
		}
		if (allCities.getPaintStatus() && topLeft.y < allCities.getTopLeft().y) {
			topLeft.y = allCities.getTopLeft().y;
		}
		if (allWaterAreas.getPaintStatus() && topLeft.y < allWaterAreas.getTopLeft().y) {
			topLeft.y = allWaterAreas.getTopLeft().y;
		}
		if (allRivers.getPaintStatus() && topLeft.y < allRivers.getTopLeft().y) {
			topLeft.y = allRivers.getTopLeft().y;
		}
	}

	public DPoint getBottomRight() {
		return bottomRight;
	}

	public void defineBottomRight() {
		bottomRight.x = 0;
		if (allStreets.getPaintStatus() && bottomRight.x < allStreets.getBottomRight().x) {
			bottomRight.x = allRailways.getBottomRight().x;
		}
		if (allRailways.getPaintStatus() && bottomRight.x < allRailways.getBottomRight().x) {
			bottomRight.x = allRailways.getBottomRight().x;
		}
		if (allCities.getPaintStatus() && bottomRight.x < allCities.getBottomRight().x) {
			bottomRight.x = allCities.getBottomRight().x;
		}
		if (allWaterAreas.getPaintStatus() && bottomRight.x < allWaterAreas.getBottomRight().x) {
			bottomRight.x = allWaterAreas.getBottomRight().x;
		}
		if (allRivers.getPaintStatus() && bottomRight.x < allRivers.getBottomRight().x) {
			bottomRight.x = allRivers.getBottomRight().x;
		}

		bottomRight.y = 90;
		if (allStreets.getPaintStatus() && bottomRight.y > allStreets.getBottomRight().y) {
			bottomRight.y = allStreets.getBottomRight().y;
		}
		if (allRailways.getPaintStatus() && bottomRight.y > allRailways.getBottomRight().y) {
			bottomRight.y = allRailways.getBottomRight().y;
		}
		if (allCities.getPaintStatus() && bottomRight.y > allCities.getBottomRight().y) {
			bottomRight.y = allCities.getBottomRight().y;
		}
		if (allWaterAreas.getPaintStatus() && bottomRight.y > allWaterAreas.getBottomRight().y) {
			bottomRight.y = allWaterAreas.getBottomRight().y;
		}
		if (allRivers.getPaintStatus() && bottomRight.y > allRivers.getBottomRight().y) {
			bottomRight.y = allRivers.getBottomRight().y;
		}
	}

	public boolean getFixedWindow() {
		return this.fixedWindow;
	}

	public void setFixedWindow(boolean fixed) {
		this.fixedWindow = fixed;
	}

	public void setTopLeftCoords(double x, double y) {
		// TODO: validate if coordinates are in allowed range
		topLeft.x = x;
		topLeft.y = y;
	}

	public void setBottomRightCoords(double x, double y) {
		// TODO: validate if coordinates are in allowed range
		bottomRight.x = x;
		bottomRight.y = y;
	}

	public void printDebug() {
		System.out.println(this);
	}

	public String toString() {
		String answer = "Coords of Map \n";

		answer = answer + "Top Left x: " + this.getTopLeft().getX() + " y: " + this.getTopLeft().getY() + " \n"
				+ "Bottom Right x: " + this.getBottomRight().getX() + " y: " + this.getBottomRight().getY() + " \n";

		return answer;
	}

	/**
	 * Paints all map objects by executing related paint methods.
	 * 
	 * @param g
	 *            Graphics object
	 * @param xMin:
	 *            min x value of (map screen) area
	 * @param yMin:
	 *            min y value of (map screen) area
	 * @param xMax:
	 *            max x value of (map screen) area
	 * @param yMax:
	 *            max y value of (map screen) area
	 *
	 */
	public void paintAll(Graphics g, double xMin, double yMin, double xMax, double yMax) {
		this.allRailways.setPaintStatus(true);
		this.allRivers.setPaintStatus(true);
		this.allCities.setPaintStatus(true);
		this.allWaterAreas.setPaintStatus(true);
		this.allStreets.setPaintStatus(true);
		if (!fixedWindow) {
			this.defineTopLeft();
			this.defineBottomRight();
		}

		worldToScreen = new WorldToScreenTransform(this.getTopLeft().x, this.getBottomRight().y,
				this.getBottomRight().x, this.getTopLeft().y, xMin, yMin, xMax, yMax);

		this.paintCities(g, worldToScreen);
		this.paintWaterAreas(g, worldToScreen);
		this.paintRivers(g, worldToScreen);
		this.paintStreets(g, worldToScreen);
		this.paintRailways(g, worldToScreen);
	}

	/**
	 *
	 * Paints all map objects by executing related paint methods and optimizes
	 * display area.
	 * 
	 * @param g
	 *            Graphics object
	 * @param xMin:
	 *            min x value of (map screen) area
	 * @param yMin:
	 *            min y value of (map screen) area
	 * @param xMax:
	 *            max x value of (map screen) area
	 * @param yMax:
	 *            max y value of (map screen) area
	 */
	public void paintSelection(Graphics g, double xMin, double yMin, double xMax, double yMax) {
		if (!fixedWindow) {
			this.defineTopLeft();
			this.defineBottomRight();
		}
		worldToScreen = new WorldToScreenTransform(this.getTopLeft().x, this.getBottomRight().y,
				this.getBottomRight().x, this.getTopLeft().y, xMin, yMin, xMax, yMax);

		if (allCities.getPaintStatus()) {
			this.paintCities(g, worldToScreen);
		}
		if (allWaterAreas.getPaintStatus()) {
			this.paintWaterAreas(g, worldToScreen);
		}

		if (allStreets.getPaintStatus()) {
			this.paintStreets(g, worldToScreen);
		}

		if (allRivers.getPaintStatus()) {
			this.paintRivers(g, worldToScreen);
		}
		if (allRailways.getPaintStatus()) {
			this.paintRailways(g, worldToScreen);
		}
	}

	/**
	 * Defines coordinates of bounding box.
	 * 
	 * @param xMin
	 * @param yMin
	 * @param xMax
	 * @param yMax
	 */
	public void calcMouseZoom(int xMin, int yMin, int xMax, int yMax) {
		double newTLX = this.worldToScreen.getSourceX(xMin);
		double newTPY = this.worldToScreen.getSourceY(yMin);
		double newBRX = this.worldToScreen.getSourceX(xMax);
		double newBRY = this.worldToScreen.getSourceY(yMax);
		this.setTopLeftCoords(newTLX, newTPY);
		this.setBottomRightCoords(newBRX, newBRY);
	}

	/**
	 * Paint streets.
	 * 
	 * @param g:
	 *            Graphics object
	 * @param factor:
	 *            scaling factor
	 */
	private void paintStreets(Graphics g, WorldToScreenTransform factor) {

		for (int i = 0; i < allStreets.getStreetList().size(); i++) {
			((Street) allStreets.getStreetList().get(i)).draw(factor, g);

		}
	}

	/**
	 * Paint rivers.
	 * 
	 * @param g:
	 *            Graphics object
	 * @param factor:
	 *            scaling factor
	 */
	private void paintRivers(Graphics g, WorldToScreenTransform factor) {
		for (int i = 0; i < allRivers.getRiverList().size(); i++) {
			((River) allRivers.getRiverList().get(i)).draw(factor, g);
		}
	}

	/**
	 * Paint railways.
	 * 
	 * @param g:
	 *            Graphics object
	 * @param factor:
	 *            scaling factor
	 */
	private void paintRailways(Graphics g, WorldToScreenTransform factor) {
		for (int i = 0; i < allRailways.getRailwayList().size(); i++) {
			((Railway) allRailways.getRailwayList().get(i)).draw(factor, g);
		}
	}

	/**
	 * Paint water areas (i.e. lakes).
	 * 
	 * @param g:
	 *            Graphics object
	 * @param factor:
	 *            scaling factor
	 */
	private void paintWaterAreas(Graphics g, WorldToScreenTransform factor) {
		for (int i = 0; i < allWaterAreas.getWaterAreaList().size(); i++) {
			((WaterArea) allWaterAreas.getWaterAreaList().get(i)).draw(factor, g);
		}
	}

	/**
	 * Paint cities.
	 * 
	 * @param g:
	 *            Graphics object
	 * @param factor:
	 *            scaling factor
	 */
	private void paintCities(Graphics g, WorldToScreenTransform factor) {
		for (int i = 0; i < allCities.getCitiesList().size(); i++) {
			((City) allCities.getCitiesList().get(i)).draw(factor, g);
		}
	}

	private boolean fixedWindow = false;
	private StreetCatalog allStreets;
	private RiverCatalog allRivers;
	private RailwayCatalog allRailways;
	private WaterAreaCatalog allWaterAreas;
	private CityCatalog allCities;
	private DPoint topLeft = new DPoint();
	private DPoint bottomRight = new DPoint();
	private WorldToScreenTransform worldToScreen;
}