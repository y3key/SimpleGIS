package simplegis;

import extern.*;
import java.util.*;
import java.io.*;

/**
 * Class for handling a collection of City objects.
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */

public class CityCatalog implements IMapObjectCatalogWindow {

	public CityCatalog(String myPath) {

		this.path = myPath;
		source = new File(path + "cities.map");
		infofile = new File(path + "cities.inf");

		importCoords(source);
		importInfo(infofile);
	}

	/**
	 * Import coordinates of cities from source file.
	 * 
	 * @param sourceFile
	 *            path to file containing the coordinates.
	 */
	public void importCoords(File sourceFile) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
			String row = in.readLine();
			while (!row.equals("EOF")) {

				int locID = Integer.parseInt(row);
				row = in.readLine();
				int dim = Integer.parseInt(row);
				City city = new City(dim);
				city.setId(locID);
				for (int i = 0; i < dim; i++) {
					row = in.readLine();
					StringTokenizer st = new StringTokenizer(row, ",");
					String yVal = st.nextToken();
					String xVal = st.nextToken();
					DPoint newCoord = new DPoint();
					newCoord.x = Double.parseDouble(xVal);
					newCoord.y = Double.parseDouble(yVal);

					city.setCoords(newCoord, i);

				}

				citiesList.add(city);
				row = in.readLine();
				row = in.readLine();
			}

			in.close();
		}

		catch (IOException ex) {
			// ignore Cities
			this.setPaintStatus(false);
			System.err.println("CityCatalog: Can't import coordinates of cities. Map File not found!");

		} catch (NoSuchElementException ex) {
			// ignore Cities
			this.setPaintStatus(false);
			System.err.println("CityCatalog: NoSuchElementException - can't import coordinates of cities. Please check map content!");
		} catch (NumberFormatException ex) {
			// ignore Cities
			this.setPaintStatus(false);
			System.err.println("CityCatalog: NumberFormatException - can't import coordinates of cities. Please check map content!");
		} catch (NullPointerException ex) {
			// ignore Cities
			this.setPaintStatus(false);
			System.err.println(
					"CitiesCatalog: NullPointerException - can't import coordinates of cities. Please check map content!");
		}
	}

	/**
	 * Imports information about City objects and determines coordinates of
	 * rectangle.
	 * 
	 * @param sourceFile
	 *
	 */
	public void importInfo(File sourceFile) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));

			String row = in.readLine();

			row = in.readLine();
			row = in.readLine();

			String delim = new String(":");
			String[] val = new String[4];

			for (int i = 0; i < 4; i++) {
				row = in.readLine();
				StringTokenizer toky = new StringTokenizer(row, delim);
				@SuppressWarnings("unused")
				String name = toky.nextToken();
				val[i] = toky.nextToken();
			}

			DPoint topLeft = new DPoint();
			topLeft.x = Double.parseDouble(val[2]);
			topLeft.y = Double.parseDouble(val[1]);
			this.setTopLeft(topLeft);

			DPoint bottomRight = new DPoint();
			bottomRight.x = Double.parseDouble(val[3]);
			bottomRight.y = Double.parseDouble(val[0]);
			this.setBottomRight(bottomRight);
			
			// bounding box check - errors are getting ignored
			if ( this.getTopLeft().getX() > this.getBottomRight().getX() ) {
				this.setPaintStatus(false);
				System.err.println("WRN: Bounding box Top Left x=" +  this.getTopLeft().getX() + ", Bottom Right x=" + this.getBottomRight().getX());
			}
			if ( this.getTopLeft().getY() < this.getBottomRight().getY() ) {
				this.setPaintStatus(false);
				System.err.println("WRN: Bounding box Top Left y=" +  this.getTopLeft().getY() + ", Bottom Right y=" + this.getBottomRight().getY());
			}

			in.close();
		} catch (IOException ex) {
			System.out.println("Can't read Cities Info file. File not found!");
		}
	}


	public String toString() {
		String answer = "Coords \n";

		{
			answer = answer + "Top Left x: " + this.getTopLeft().getX() + " y: " + this.getTopLeft().getY() + " \n"
					+ "Bottom Right x: " + this.getBottomRight().getX() + " y: " + this.getBottomRight().getY() + " \n";

		}

		return answer;
	}

	public void printDebug() {
		System.out.println(this);

		for (int i = 0; i < citiesList.size(); i++) {
			System.out.println(citiesList.get(i));
		}

	}

	public CityCatalog getCitiesCatalog() {
		return this;
	}

	public ArrayList<City> getCitiesList() {
		return citiesList;
	}

	public DPoint getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(DPoint tl) {
		topLeft = tl;
	}

	public DPoint getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(DPoint br) {
		bottomRight = br;
	}

	public boolean getPaintStatus() {
		return paintStatus;
	}

	public void setPaintStatus(boolean paint) {
		paintStatus = paint;
	}

	private ArrayList<City> citiesList = new ArrayList<City>();

	private String path;
	{
		path = "";
	}

	private File source;

	private File infofile;

	private DPoint topLeft = new DPoint();

	private DPoint bottomRight = new DPoint();

	private boolean paintStatus;
	{
		paintStatus = true;
	}

}