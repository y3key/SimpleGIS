package simplegis;

/**
 * MapLogic defines basic settings such as path to the map. 
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
class MapLogic {
	public MapLogic() {

		allFiles = new ObjectCatalog();

	}

	/**
	 * Load project and set bounding box.
	 * 
	 * @param path
	 */
	public void loadPrj(String path) {
		this.path = path;
		allFiles.setAll(path);
		allFiles.defineBottomRight();
		allFiles.defineTopLeft();
		this.setRatio((allFiles.getBottomRight().y - allFiles.getTopLeft().y)
				/ (allFiles.getTopLeft().x - allFiles.getBottomRight().x));

	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setObjectCatalog(String path) {
		allFiles = new ObjectCatalog();
	}

	public ObjectCatalog getObjectCatalog() {
		return allFiles;
	}

	public void setRatio(double v) {
		ratio = v;
	}

	public double getRatio() {
		return ratio;

	}

	private ObjectCatalog allFiles;
	private String path;
	private double ratio;

}