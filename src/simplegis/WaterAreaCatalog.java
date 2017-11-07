package simplegis;

import extern.*;
import java.util.*;
import java.io.*;

/**
 * Class for handling a collection of WaterArea objects. Copyright: Copyright
 * (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public class WaterAreaCatalog implements IMapObjectCatalogWindow {

	public WaterAreaCatalog(String myPath) {

		this.path = myPath;
		source = new File(path + "lakes.map");
		infoFile = new File(path + "lakes.inf");

		importCoords(source);
		importInfoFile(infoFile);
	}

	/**
	 * Import coordinates of lakes from source file.
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
				WaterArea lake = new WaterArea(dim);
				lake.setId(locID);
				for (int i = 0; i < dim; i++) {
					row = in.readLine();
					StringTokenizer st = new StringTokenizer(row, ",");
					String yVal = st.nextToken();
					String xVal = st.nextToken();
					DPoint newCoord = new DPoint();
					newCoord.x = Double.parseDouble(xVal);
					newCoord.y = Double.parseDouble(yVal);

					lake.setCoords(newCoord, i);

				}

				waterAreaList.add(lake);
				row = in.readLine();
				row = in.readLine();

			}

			in.close();
		}

		catch (IOException ex) {
			// ignore lakes
			this.setPaintStatus(false);
			System.err.println("WaterAreaCatalog: Can't import coordinates of lakes. Map File not found!");

		} catch (NoSuchElementException ex) {
			// ignore lakes
			this.setPaintStatus(false);
			System.err.println(
					"WaterAreaCatalog: NoSuchElementException - can't import coordinates of lakes. Please check map content!");
		} catch (NumberFormatException ex) {
			// ignore lakes
			this.setPaintStatus(false);
			System.err.println(
					"WaterAreaCatalog: NumberFormatException - can't import coordinates of lakes. Please check map content!");
		} catch (NullPointerException ex) {
			// ignore lakes
			this.setPaintStatus(false);
			System.err.println(
					"WaterAreaCatalog: NullPointerException - can't import coordinates of lakes. Please check map content!");
		}
	}

	/**
	 * Imports information about WaterArea objects and determines coordinates of
	 * rectangle.
	 * 
	 * @param sourceFile
	 *
	 */
	public void importInfoFile(File sourceFile) {
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
			System.out.println("Water Areas: Can't import Info File. File not found!");
		}
	}

	public String toString() {
		String answer = "Coords \n";

		{
			answer = answer + "Top Left x=" + this.getTopLeft().getX() + " y=" + this.getTopLeft().getY() + " \n"
					+ "Bottom Right x=" + this.getBottomRight().getX() + " y=" + this.getBottomRight().getY() + " \n";

		}

		return answer;
	}

	public void printDebug() {

		System.out.println(this);

		for (int i = 0; i < waterAreaList.size(); i++) {
			System.out.println(waterAreaList.get(i));
		}

	}

	public WaterAreaCatalog getWaterAreaCatalog() {
		return this;
	}

	public ArrayList<WaterArea> getWaterAreaList() {
		return waterAreaList;
	}

	public DPoint getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(DPoint tp) {
		topLeft = tp;
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

	private ArrayList<WaterArea> waterAreaList = new ArrayList<WaterArea>();

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