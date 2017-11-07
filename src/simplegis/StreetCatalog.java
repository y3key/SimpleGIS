package simplegis;

import extern.*;
import java.util.*;
import java.io.*;

/**
 * Class for handling a collection of Street objects. Copyright: Copyright (c)
 * 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public class StreetCatalog implements IMapObjectCatalogWindow {

	public StreetCatalog(String myPath) {

		this.path = myPath;
		source = new File(path + "roads.map");
		infoFile = new File(path + "roads.inf");

		importCoords(source);
		importInfoFile(infoFile);
	}

	/**
	 * Import coordinates of roads from source file.
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
				Street street = new Street(dim);
				street.setId(locID);
				for (int i = 0; i < dim; i++) {
					row = in.readLine();
					StringTokenizer st = new StringTokenizer(row, ",");
					String yVal = st.nextToken();
					String xVal = st.nextToken();
					DPoint newCoord = new DPoint();
					newCoord.x = Double.parseDouble(xVal);
					newCoord.y = Double.parseDouble(yVal);

					street.setCoords(newCoord, i);

				}

				streetList.add(street);
				row = in.readLine();
				row = in.readLine();

			}

			in.close();
		}

		catch (IOException ex) {
			// ignore Streets
			this.setPaintStatus(false);
			System.err.println("StreetCatalog: Can't import coordinates of streets. Map File not found!");

		} catch (NoSuchElementException ex) {
			// ignore Streets
			this.setPaintStatus(false);
			System.err.println(
					"StreetCatalog: NoSuchElementException - can't import coordinates of streets. Please check map content!");
		} catch (NumberFormatException ex) {
			// ignore Streets
			this.setPaintStatus(false);
			System.err.println(
					"StreetCatalog: NumberFormatException - can't import coordinates of streets. Please check map content!");
		} catch (NullPointerException ex) {
			// ignore Streets
			this.setPaintStatus(false);
			System.err.println(
					"StreetCatalog: NullPointerException - can't import coordinates of streets. Please check map content!");
		}
	}

	/**
	 * Imports information about City objects and determines coordinates of
	 * rectangle.
	 * 
	 * @param sourceFile
	 *
	 */
	public void importInfoFile(File mySource) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(mySource)));

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

			DPoint tl = new DPoint();
			tl.x = Double.parseDouble(val[2]);
			tl.y = Double.parseDouble(val[1]);
			this.setTopLeft(tl);

			DPoint br = new DPoint();
			br.x = Double.parseDouble(val[3]);
			br.y = Double.parseDouble(val[0]);
			this.setBottomRight(br);

			in.close();

		}

		catch (IOException ex) {
			System.out.println("Can't import Street Info File. File not found!");
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

		for (int i = 0; i < streetList.size(); i++) {
			System.out.println(streetList.get(i));
		}

	}

	public StreetCatalog getStreetCatalog() {
		return this;
	}

	public ArrayList<Street> getStreetList() {
		return streetList;
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

	private ArrayList<Street> streetList = new ArrayList<Street>();

	private String path;
	{
		path = "";
	}

	private File source;

	private File infoFile;

	private DPoint topLeft = new DPoint();

	private DPoint bottomRight = new DPoint();

	private boolean paintStatus;
	{
		paintStatus = true;
	}
}
