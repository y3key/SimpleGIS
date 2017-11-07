package simplegis;

import extern.*;
import java.util.*;
import java.io.*;

/**
 * Class for handling a collection of Railway objects. 
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */

public class RailwayCatalog implements IMapObjectCatalogWindow {

	public RailwayCatalog(String myPath) {

		this.path = myPath;
		sourceFile = new File(path + "railways.map");
		infoFile = new File(path + "railways.inf");

		importCoords(sourceFile);
		importInfoFile(infoFile);
	}

	/**
	 * Import coordinates of railways from source file.
	 * 
	 * @param source
	 *            path to file containing the coordinates.
	 */

	public void importCoords(File source) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			String row = in.readLine();
			while (!row.equals("EOF")) {

				int locID = Integer.parseInt(row);
				row = in.readLine();
				int dim = Integer.parseInt(row);
				Railway rail = new Railway(dim);
				rail.setId(locID);
				for (int i = 0; i < dim; i++) {
					row = in.readLine();
					StringTokenizer st = new StringTokenizer(row, ",");
					String yVal = st.nextToken();
					String xVal = st.nextToken();
					DPoint newCoord = new DPoint();
					newCoord.x = Double.parseDouble(xVal);
					newCoord.y = Double.parseDouble(yVal);

					rail.setCoords(newCoord, i);

				}

				railwayList.add(rail);
				row = in.readLine();
				row = in.readLine();

			}

			in.close();
		}

		catch (IOException ex) {
			// ignore railways
			this.setPaintStatus(false);
			System.err.println("RailwayCatalog: Can't import coordinates of railways. Map File not found!");

		} catch (NoSuchElementException ex) {
			// ignore railways
			this.setPaintStatus(false);
			System.err.println(
					"RailwayCatalog: NoSuchElementException - can't import coordinates of railways. Please check map content!");
		} catch (NumberFormatException ex) {
			// ignore railways
			this.setPaintStatus(false);
			System.err.println(
					"RailwayCatalog: NumberFormatException - can't import coordinates of railways. Please check map content!");
		} catch (NullPointerException ex) {
			// ignore railways
			this.setPaintStatus(false);
			System.err.println(
					"RailwayCatalog: NullPointerException - can't import coordinates of railways. Please check map content!");
		}
	}

	/**
	 * Imports information about Railway objects and determines coordinates of
	 * rectangle.
	 * 
	 * @param source
	 *
	 */
	public void importInfoFile(File source) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)));

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

			DPoint topL = new DPoint();
			topL.x = Double.parseDouble(val[2]);
			topL.y = Double.parseDouble(val[1]);
			this.setTopLeft(topL);

			DPoint botR = new DPoint();
			botR.x = Double.parseDouble(val[3]);
			botR.y = Double.parseDouble(val[0]);
			this.setBottomRight(botR);

			in.close();

		}

		catch (IOException ex) {
			System.out.println("File not found!");
		}
	}

	public String toString() {
		String answer = "Coords:\n";

		{
			answer = answer + "Top Left x: " + this.getTopLeft().getX() + " y: " + this.getTopLeft().getY() + " \n"
					+ "Bottom Right x: " + this.getBottomRight().getX() + " y: " + this.getBottomRight().getY() + " \n";

		}

		return answer;
	}

	public void testPrint() {

		System.out.println(this);

		for (int i = 0; i < railwayList.size(); i++) {
			System.out.println(railwayList.get(i));
		}

	}

	public RailwayCatalog getBahnKatalog() {
		return this;
	}

	public ArrayList<Railway> getRailwayList() {
		return railwayList;
	}

	public DPoint getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(DPoint ol) {
		topLeft = ol;
	}

	public DPoint getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(DPoint ur) {
		bottomRight = ur;
	}

	public boolean getPaintStatus() {
		return paintStatus;
	}

	public void setPaintStatus(boolean paint) {
		paintStatus = paint;
	}

	private ArrayList<Railway> railwayList = new ArrayList<Railway>();

	private String path;

	private File sourceFile;

	private File infoFile;

	private DPoint topLeft = new DPoint();

	private DPoint bottomRight = new DPoint();

	private boolean paintStatus;
	{
		paintStatus = true;
	}

}
