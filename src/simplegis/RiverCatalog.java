package simplegis;

import extern.*;
import java.util.*;
import java.io.*;

/**
 * Class for handling a collection of River objects. Copyright: Copyright (c)
 * 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */

public class RiverCatalog implements IMapObjectCatalogWindow {

	public RiverCatalog(String newPath) {

		this.path = newPath;
		sourceFile = new File(path + "rivers.map");
		infoFile = new File(path + "rivers.inf");

		importCoords(sourceFile);
		importInfo(infoFile);
	}

	/**
	 * Import coordinates of rivers from source file.
	 * 
	 * @param newSource
	 *            path to file containing the coordinates.
	 */
	public void importCoords(File newSource) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(newSource)));
			String row = in.readLine();
			while (!row.equals("EOF")) {

				int locID = Integer.parseInt(row);
				row = in.readLine();
				int dim = Integer.parseInt(row);
				River river = new River(dim);
				river.setId(locID);
				for (int i = 0; i < dim; i++) {
					row = in.readLine();
					StringTokenizer st = new StringTokenizer(row, ",");
					String yVal = st.nextToken();
					String xVal = st.nextToken();
					DPoint newPt = new DPoint();
					newPt.x = Double.parseDouble(xVal);
					newPt.y = Double.parseDouble(yVal);

					river.setCoords(newPt, i);

				}

				riverList.add(river);
				row = in.readLine();
				row = in.readLine();

			}

			in.close();
		}

		catch (IOException ex) {
			// ignore Rivers
			this.setPaintStatus(false);
			System.err.println("RiverCatalog: Can't import coordinates of rivers. Map File not found!");

		} catch (NoSuchElementException ex) {
			// ignore Rivers
			this.setPaintStatus(false);
			System.err.println(
					"RiverCatalog: NoSuchElementException - can't import coordinates of rivers. Please check map content!");
		} catch (NumberFormatException ex) {
			// ignore Rivers
			this.setPaintStatus(false);
			System.err.println(
					"RiversCatalog: NumberFormatException - can't import coordinates of rivers. Please check map content!");
		} catch (NullPointerException ex) {
			// ignore Rivers
			this.setPaintStatus(false);
			System.err.println(
					"RiverCatalog: NullPointerException - can't import coordinates of rivers. Please check map content!");
		}
	}

	/**
	 * Imports information about River objects and determines coordinates of
	 * rectangle.
	 * 
	 * @param newSource
	 *
	 */
	public void importInfo(File newSource) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(newSource)));

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
		String answer = "Coords\n";

		{
			answer = answer + "Top Left x=" + this.getTopLeft().getX() + " y=" + this.getTopLeft().getY() + " \n"
					+ "Bottom Right x=" + this.getBottomRight().getX() + " y=" + this.getBottomRight().getY() + " \n";

		}

		return answer;
	}

	public void testPrint() {

		System.out.println(this);

		for (int i = 0; i < riverList.size(); i++) {
			System.out.println(riverList.get(i));
		}

	}

	public RiverCatalog getRiverCatalog() {
		return this;
	}

	public ArrayList<River> getRiverList() {
		return riverList;
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

	public void setBottomRight(DPoint br) {
		bottomRight = br;
	}

	public boolean getPaintStatus() {
		return paintStatus;
	}

	public void setPaintStatus(boolean paint) {
		paintStatus = paint;
	}

	private ArrayList<River> riverList = new ArrayList<River>();

	private String path;
	{
		path = "";
	}

	private File sourceFile;

	private File infoFile;

	private DPoint topLeft = new DPoint();

	private DPoint bottomRight = new DPoint();

	private boolean paintStatus;
	{
		paintStatus = true;
	}

}