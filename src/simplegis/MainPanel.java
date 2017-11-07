package simplegis;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import simplegis.Frame;

/**
 * MainPanel contains the UI elements. It has a MapPanel object as member
 * variable. Copyright (c) 2002.
 * 
 * @author Stefan Friese
 * @version 1.1
 */

public class MainPanel extends JPanel {

	public MainPanel() {
		defineLayout();
		registerListener();
		activateUI(false);

		this.objectSelection.setEnabled(false);

		this.title.setText("select map");
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public MainPanel getMainPanel() {
		return this;
	}

	public void setMoveWidth(int width) {
		this.moveWidth = width;
	}

	public int getMoveWidth() {
		return moveWidth;
	}

	boolean isReady() {
		return bReady;
	}

	/**
	 * set layout and add elements to the content panel.
	 */
	private void defineLayout() {
		this.setLayout(new BorderLayout());

		// WEST:
		toolBar.setFloatable(false);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setToolTipText("Menu");
		toolBar.createToolTip();

		// SOUTH:
		sToolBar.setFloatable(false);
		sToolBar.setOrientation(JToolBar.HORIZONTAL);
		sToolBar.setToolTipText("Menu");
		sToolBar.createToolTip();

		sToolBar.setLayout(new FlowLayout());

		zoomList.addItem(" 0.25");
		zoomList.addItem(" 0.5");
		zoomList.addItem(" 1");
		zoomList.addItem(" 2");
		zoomList.addItem(" 4");
		zoomList.setSelectedIndex(2);

		// WEST:
		this.fileOpen.setAlignmentX(0);
		this.fileOpen.setAlignmentY(0);
		this.fileOpen.setLocation(0, 0);

		this.fileOpen.setMaximumSize(new Dimension(120, 30));
		this.fileOpen.setMinimumSize(new Dimension(120, 30));
		this.fileOpen.setPreferredSize(new Dimension(120, 30));

		this.fullMap.setMaximumSize(new Dimension(120, 30));
		this.fullMap.setMinimumSize(new Dimension(120, 30));
		this.fullMap.setPreferredSize(new Dimension(120, 30));

		this.displayMap.setMaximumSize(new Dimension(120, 30));
		this.displayMap.setMinimumSize(new Dimension(120, 30));
		this.displayMap.setPreferredSize(new Dimension(120, 30));

		this.zoomIn.setMaximumSize(new Dimension(120, 30));
		this.zoomIn.setMinimumSize(new Dimension(120, 30));
		this.zoomIn.setPreferredSize(new Dimension(120, 30));

		this.zoomOut.setMaximumSize(new Dimension(120, 30));
		this.zoomOut.setMinimumSize(new Dimension(120, 30));
		this.zoomOut.setPreferredSize(new Dimension(120, 30));

		this.mouseZoom.setMaximumSize(new Dimension(120, 30));
		this.mouseZoom.setMinimumSize(new Dimension(120, 30));
		this.mouseZoom.setPreferredSize(new Dimension(120, 30));

		this.panRight.setMaximumSize(new Dimension(120, 30));
		this.panRight.setMinimumSize(new Dimension(120, 30));
		this.panRight.setPreferredSize(new Dimension(120, 30));

		this.panLeft.setMaximumSize(new Dimension(120, 30));
		this.panLeft.setMinimumSize(new Dimension(120, 30));
		this.panLeft.setPreferredSize(new Dimension(120, 30));

		this.panUp.setMaximumSize(new Dimension(120, 30));
		this.panUp.setMinimumSize(new Dimension(120, 30));
		this.panUp.setPreferredSize(new Dimension(120, 30));

		this.panDown.setMaximumSize(new Dimension(120, 30));
		this.panDown.setMinimumSize(new Dimension(120, 30));
		this.panDown.setPreferredSize(new Dimension(120, 30));

		this.moveFree.setMaximumSize(new Dimension(120, 30));
		this.moveFree.setMinimumSize(new Dimension(120, 30));
		this.moveFree.setPreferredSize(new Dimension(120, 30));

		this.mapLayer.setMaximumSize(new Dimension(120, 30));
		this.mapLayer.setMinimumSize(new Dimension(120, 30));
		this.mapLayer.setPreferredSize(new Dimension(120, 30));

		this.toolBar.addSeparator(new Dimension(0, 10));
		toolBar.add(fileOpen);
		this.toolBar.addSeparator(new Dimension(0, 20));
		toolBar.add(fullMap);
		this.toolBar.addSeparator(new Dimension(0, 20));
		toolBar.add(displayMap);
		this.toolBar.addSeparator(new Dimension(10, 20));
		toolBar.add(zoomIn);
		this.toolBar.addSeparator(new Dimension(10, 5));
		toolBar.add(zoomOut);
		this.toolBar.addSeparator(new Dimension(10, 5));
		toolBar.add(mouseZoom);
		this.toolBar.addSeparator(new Dimension(10, 20));
		toolBar.add(panRight);
		this.toolBar.addSeparator(new Dimension(10, 5));
		toolBar.add(panLeft);
		this.toolBar.addSeparator(new Dimension(10, 5));
		toolBar.add(panUp);
		this.toolBar.addSeparator(new Dimension(10, 5));
		toolBar.add(panDown);
		this.toolBar.addSeparator(new Dimension(10, 5));
		toolBar.add(moveFree);
		this.toolBar.addSeparator(new Dimension(10, 20));
		toolBar.add(mapLayer);

		// SOUTH:
		this.zoomList.setMaximumSize(new Dimension(60, 30));
		this.zoomList.setMinimumSize(new Dimension(60, 30));
		this.zoomList.setPreferredSize(new Dimension(60, 30));
		this.objectSelection.setMaximumSize(new Dimension(120, 30));
		this.objectSelection.setMinimumSize(new Dimension(120, 30));
		this.objectSelection.setPreferredSize(new Dimension(120, 30));
		this.title.setMaximumSize(new Dimension(120, 30));
		this.title.setMinimumSize(new Dimension(120, 30));
		this.title.setPreferredSize(new Dimension(120, 30));

		this.scaleText.setMaximumSize(new Dimension(125, 30));
		this.scaleText.setMinimumSize(new Dimension(125, 30));
		this.scaleText.setPreferredSize(new Dimension(125, 30));

		this.stopApp.setMaximumSize(new Dimension(120, 30));
		this.stopApp.setMinimumSize(new Dimension(120, 30));
		this.stopApp.setPreferredSize(new Dimension(120, 30));
		this.zoomLevel.setMaximumSize(new Dimension(70, 30));
		this.zoomLevel.setMinimumSize(new Dimension(70, 30));
		this.zoomLevel.setPreferredSize(new Dimension(70, 30));

		this.sToolBar.addSeparator(new Dimension(10, 10));
		sToolBar.add(zoomLevel);
		sToolBar.add(zoomList);
		this.sToolBar.addSeparator(new Dimension(20, 10));
		sToolBar.add(objectSelection);
		this.sToolBar.addSeparator(new Dimension(10, 10));
		sToolBar.add(title);
		this.sToolBar.addSeparator(new Dimension(10, 10));
		sToolBar.add(scaleText);
		this.sToolBar.addSeparator(new Dimension(10, 10));
		sToolBar.add(stopApp);

		zoomLevel.setForeground(Color.black);

		/*** ToolTipText ***/
		fileOpen.setToolTipText("load map");
		fullMap.setToolTipText("full map");
		displayMap.setToolTipText("display map");
		zoomIn.setToolTipText("zoom in");
		zoomOut.setToolTipText("zoom out");
		panRight.setToolTipText("move right");
		panLeft.setToolTipText("move left");
		panUp.setToolTipText("move up");
		panDown.setToolTipText("move down");
		moveFree.setToolTipText("move map");
		zoomList.setToolTipText("select zoom level");
		zoomLevel.setToolTipText("show zoom level");
		mapLayer.setToolTipText("select map layer");
		objectSelection.setToolTipText("select object");
		title.setToolTipText("title");
		stopApp.setToolTipText("close");
		mouseZoom.setToolTipText("zoom");

		/*** BorderLayout ***/
		this.add(toolBar, BorderLayout.WEST); // Toolbar
		this.add(sToolBar, BorderLayout.SOUTH); // Toolbar
		this.add(mapPanel, BorderLayout.CENTER); // Panel with Flow-Layout
	}

	/**
	 * activates ActionListeners for buttons.
	 */
	private void registerListener() {
		fileOpen.addActionListener(new MapFile());
		fullMap.addActionListener(new MapZoom());
		displayMap.addActionListener(new MapDraw());
		zoomIn.addActionListener(new MapZoom());
		zoomOut.addActionListener(new MapZoom());
		mouseZoom.addActionListener(new MapZoom());
		panRight.addActionListener(new MoveMap());
		panLeft.addActionListener(new MoveMap());
		panUp.addActionListener(new MoveMap());
		panDown.addActionListener(new MoveMap());
		moveFree.addActionListener(new MoveMap());
		zoomList.addActionListener(new ZoomList());
		mapLayer.addActionListener(new MapLayer());
		objectSelection.addActionListener(new SelectObject());
		stopApp.addActionListener(new CloseApp());
	}

	/**
	 * Activate buttons. The buttons get only activated if the map is loaded.
	 * 
	 * @param status
	 */
	public void activateUI(boolean status) {
		fullMap.setEnabled(status);
		displayMap.setEnabled(status);
		zoomIn.setEnabled(status);
		zoomOut.setEnabled(status);
		mouseZoom.setEnabled(status);
		panRight.setEnabled(status);
		panLeft.setEnabled(status);
		panUp.setEnabled(status);
		panDown.setEnabled(status);
		moveFree.setEnabled(status);
		zoomList.setEnabled(status);
		mapLayer.setEnabled(status);
		zoomLevel.setEnabled(status);
	}

	public void refreshLabel(String text) {
		this.title.setText(text);
	}

	/****************************************************************
	 *** ActionEvents ***
	 ****************************************************************/

	/**
	 * MapLayer is a dialog to select map layers.
	 */
	class MapLayer implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Frame parentFrame = (Frame) getParent().getParent().getParent().getParent();

			LayerDialog dialog = new LayerDialog(parentFrame, true);
			dialog.show();

			mapPanel.setAll(false);
			mapPanel.getLogic().getObjectCatalog().getAllStreets().setPaintStatus(false);
			mapPanel.getLogic().getObjectCatalog().getAllRailways().setPaintStatus(false);
			mapPanel.getLogic().getObjectCatalog().getAllRivers().setPaintStatus(false);
			mapPanel.getLogic().getObjectCatalog().getAllCities().setPaintStatus(false);
			mapPanel.getLogic().getObjectCatalog().getAllWaterAreas().setPaintStatus(false);

			if (dialog.getRailwaySelecction()) {
				mapPanel.getLogic().getObjectCatalog().getAllRailways().setPaintStatus(true);
			}
			if (dialog.getRiverSelection()) {
				mapPanel.getLogic().getObjectCatalog().getAllRivers().setPaintStatus(true);
			}
			if (dialog.getCitySelection()) {
				mapPanel.getLogic().getObjectCatalog().getAllCities().setPaintStatus(true);
			}
			if (dialog.getStreetSelection()) {
				mapPanel.getLogic().getObjectCatalog().getAllStreets().setPaintStatus(true);
			}
			if (dialog.getWaterAreas()) {
				mapPanel.getLogic().getObjectCatalog().getAllWaterAreas().setPaintStatus(true);
			}
			bReady = true;
			mapPanel.repaint();
		}
	}

	/**
	 * MapFile is getting invoked by ActionListener to open a map.
	 */
	public class MapFile implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			FileDialog fd = new FileDialog(new JFrame(), "Choose a map file", FileDialog.LOAD);
			fd.setDirectory("C:\\");
			fd.setFile("*.map");
			fd.setVisible(true);
			System.out.println(fd.getDirectory());

			setPrjPath(fd.getDirectory());

			mapPanel.setScreenXmax(mapPanel.getWidth());
			mapPanel.getLogic().loadPrj(fd.getDirectory());
			mapPanel.setScreenYmaxOrig((int) (getMapPanel().getScreenXmax() * mapPanel.getLogic().getRatio()));
			mapPanel.setScreenYmax(getMapPanel().getScreenYmaxOrig());
			title.setText("Map Opened");
			activateUI(true);

			mapPanel.calcScale();
			String inter = "" + mapPanel.getScale();
			scaleText.setText("Width: " + inter.substring(0, 5) + " km");
			sToolBar.validate();
			mapPanel.repaint();
		}
	}

	/**
	 * MapDraw is getting invoked by ActionListener to repaint map.
	 */
	class MapDraw implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			Object paint = event.getSource();
			JButton b = (JButton) paint;
			String cmd = b.getText();

			if (cmd.equals("repaint map")) {
				mapPanel.setScreenXmin(0);
				mapPanel.setScreenYmin(0);
				mapPanel.setScreenXmax(mapPanel.getWidth());
				mapPanel.setScreenYmax(
						(int) ((mapPanel.getScreenXmax() - mapPanel.getScreenYmin()) * mapPanel.getLogic().getRatio()));

				mapPanel.calcScale();
				String inter = "" + mapPanel.getScale();
				scaleText.setText("Width: " + inter.substring(0, 5) + " km");
				sToolBar.validate();

				mapPanel.repaint();
			}
		}
	}

	/**
	 * CloseApp is getting invoked by ActionListener to close the application.
	 */
	class CloseApp implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object closeApp = event.getSource();
			JButton b = (JButton) closeApp;
			String cmd = b.getText();
			if (cmd.equals("close")) {
				System.exit(0);
			}
		}
	}

	/**
	 * MapZoom is getting invoked by ActionListener to zoom in/out, with the mouse.
	 */
	class MapZoom implements ActionListener {
		String cmd;

		public void actionPerformed(ActionEvent event) {
			cmd = (String) event.getActionCommand();

			if (cmd.equals("full map")) {
				zoomList.setSelectedIndex(2); // zoom factor 1
				mapPanel.setAll(true);
				mapPanel.setScreenXmin(0);
				mapPanel.setScreenYmin(0);
				mapPanel.setScreenXmax(mapPanel.getWidth());
				mapPanel.setScreenYmax(
						(int) ((mapPanel.getScreenXmax() - mapPanel.getScreenYmin()) * mapPanel.getLogic().getRatio()));

				title.setText("Map Opened");
				mapPanel.calcScale();
				String inter = "" + mapPanel.getScale();
				scaleText.setText("Width: " + inter.substring(0, 5) + " km");
				sToolBar.validate();

				mapPanel.repaint();
			}

			else if (cmd.equals("zoom in")) {
				if (zoomList.getSelectedIndex() < zoomList.getItemCount() - 1) {
					mapPanel.zoom(2); // zoom factor
					mapPanel.repaint();
					zoomList.setSelectedIndex(zoomList.getSelectedIndex() + 1);
					title.setText("Map Opened");
					String cmd = (String) zoomList.getSelectedItem();

					float zoomFactor;
					zoomFactor = Float.parseFloat(cmd);
					String inter = "" + mapPanel.getScale() / zoomFactor;
					scaleText.setText("Width: " + inter.substring(0, 5) + " km");
					sToolBar.validate();
					mapPanel.repaint();

				} else {
					title.setText("reached zoom limit");
				}
			}

			else if (cmd.equals("zoom out")) {
				if (zoomList.getSelectedIndex() > 0) {
					mapPanel.zoom(0.5);
					mapPanel.repaint();
					zoomList.setSelectedIndex(zoomList.getSelectedIndex() - 1);
					title.setText("Map Opened");
					String cmd = (String) zoomList.getSelectedItem();
					float zoomFactor;
					zoomFactor = Float.parseFloat(cmd);
					String inter = "" + mapPanel.getScale() / zoomFactor;
					scaleText.setText("Width: " + inter.substring(0, 5) + " km");
					sToolBar.validate();
					mapPanel.repaint();

				} else {
					title.setText("reached zoom limit");
				}
			}

			else if (cmd.equals("mouse zoom")) {

				if (!freeZoom) {
					setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
					mapPanel.getLogic().getObjectCatalog().setFixedWindow(true);
					moveFree.setEnabled(false);
					zoomIn.setEnabled(false);
					zoomOut.setEnabled(false);
					zoomList.setEnabled(false);
					fullMap.setEnabled(false);
					freeZoom = true;
					mouseZoom.setBackground(Color.gray);
					mapPanel.addMouseListener(zoomListener);
				} else {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					mapPanel.getLogic().getObjectCatalog().setFixedWindow(false);
					zoomListener.setScaleFactor(Double.parseDouble((String) zoomList.getSelectedItem()));
					fullMap.setEnabled(true);
					freeZoom = false;
					zoomIn.setEnabled(true);
					zoomOut.setEnabled(true);
					zoomList.setEnabled(true);
					moveFree.setEnabled(true);
					mouseZoom.setBackground(Color.lightGray);
					mapPanel.removeMouseListener(zoomListener);
					title.setText("Map Opened");
					scaleText.setText("");
					mapPanel.repaint();
				}
				title.setText("Map Opened");
			}
		}

		private float minZoom;
		private float maxZoom;
		private boolean freeZoom = false;
		ZoomListener zoomListener = new ZoomListener();

		public float getMinZoomFloat() {
			float returnWert = (float) 0.5;
			return returnWert;
		}

		public float getMaxZoomFloat() {
			float returnWert = 4;
			return returnWert;
		}

	}

	/**
	 * ZoomList sets the zoom factor.
	 */
	class ZoomList implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String cmd = (String) zoomList.getSelectedItem();
			float zoomFactor;
			zoomFactor = Float.parseFloat(cmd);

			mapPanel.setAll(true);
			mapPanel.setScreenXmin(0);
			mapPanel.setScreenYmin(0);
			mapPanel.setScreenYmax(mapPanel.getScreenYmaxOrig());
			mapPanel.setScreenXmax(mapPanel.getWidth());

			mapPanel.zoom(zoomFactor);
			String inter = "" + mapPanel.getScale() / zoomFactor;
			scaleText.setText("Width: " + inter.substring(0, 5) + " km");
			sToolBar.validate();
			mapPanel.repaint();
			mapPanel.repaint();

		}
	}

	/**
	 * MoveMap gets invoked by ActionListener to move map (e.g. right, left, up,
	 * down).
	 */
	class MoveMap implements ActionListener {
		String cmd;

		public void actionPerformed(ActionEvent event) {
			cmd = (String) event.getActionCommand();

			if (cmd.equals("right")) {
				mapPanel.setScreenXmin(mapPanel.getScreenXmin() + getMoveWidth());
				mapPanel.setScreenXmax(mapPanel.getScreenXmax() + getMoveWidth());
				mapPanel.repaint();
			}

			else if (cmd.equals("left")) {
				mapPanel.setScreenXmin(mapPanel.getScreenXmin() - getMoveWidth());
				mapPanel.setScreenXmax(mapPanel.getScreenXmax() - getMoveWidth());
				mapPanel.repaint();
			}

			else if (cmd.equals("up")) {
				mapPanel.setScreenYmin(mapPanel.getScreenYmin() - getMoveWidth());
				mapPanel.setScreenYmax(mapPanel.getScreenYmax() - getMoveWidth());
				mapPanel.repaint();
			}

			else if (cmd.equals("down")) {
				mapPanel.setScreenYmin(mapPanel.getScreenYmin() + getMoveWidth());
				mapPanel.setScreenYmax(mapPanel.getScreenYmax() + getMoveWidth());
				mapPanel.repaint();
			}

			else if (cmd.equals("move")) {
				if (!moveStatus) {
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
					moveStatus = true;
					moveFree.setBackground(Color.gray);
					mapPanel.addMouseListener(moveMapListener);
				} else {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					moveStatus = false;
					moveFree.setBackground(Color.lightGray);
					mapPanel.removeMouseListener(moveMapListener);
				}
			}

		}

		MouseListener moveMapListener = new MoveListener();
		boolean moveStatus = false;
	}

	/**
	 * MouseListener gets invoked if there are MouseEvents.
	 */
	class MouseListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			this.setStartMouseX(e.getX());
			this.setStartMouseY(e.getY());
		}

		public void mouseReleased(MouseEvent e) {
			this.setEndMouseX(e.getX());
			this.setEndMouseY(e.getY());
		}

		public void setStartMouseX(int mx) {
			startMouseX = mx;
		}

		public int getStartMouseX() {
			return startMouseX;
		}

		public void setEndMouseX(int mx) {
			endMouseX = mx;
		}

		public int getEndMouseX() {
			return endMouseX;
		}

		public void setStartMouseY(int my) {
			startMouseY = my;
		}

		public int getStartMouseY() {
			return startMouseY;
		}

		public void setEndMouseY(int my) {
			endMouseY = my;
		}

		public int getEndMouseY() {
			return endMouseY;
		}

		private int startMouseX;
		private int startMouseY;
		private int endMouseX;
		private int endMouseY;
	}

	/**
	 * MoveListener stores coordinates of start and end points to adapt bounding box
	 * of map.
	 */
	class MoveListener extends MouseListener {

		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			int deltaX = this.getEndMouseX() - this.getStartMouseX();
			int deltaY = this.getEndMouseY() - this.getStartMouseY();

			mapPanel.setScreenXmin(mapPanel.getScreenXmin() + deltaX);
			mapPanel.setScreenXmax(mapPanel.getScreenXmax() + deltaX);

			mapPanel.setScreenYmin(mapPanel.getScreenYmin() + deltaY);
			mapPanel.setScreenYmax(mapPanel.getScreenYmax() + deltaY);
			mapPanel.repaint();
		}
	}

	/**
	 * ZoomListener sets coordinates of bounding box.
	 */
	class ZoomListener extends MouseListener {
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			int xMinNeu = this.getStartMouseX();
			int yMinNeu = this.getStartMouseY();
			int xMaxNeu = this.getEndMouseX();
			int yMaxNeu = this.getEndMouseY();

			if (xMinNeu > this.getEndMouseX()) {
				xMinNeu = this.getEndMouseX();
				xMaxNeu = this.getStartMouseX();
			}
			if (yMinNeu > this.getEndMouseY()) {
				yMinNeu = this.getEndMouseY();
			}

			yMaxNeu = (int) (yMinNeu + (xMaxNeu - xMinNeu) * mapPanel.getLogic().getRatio());

			mapPanel.getLogic().getObjectCatalog().calcMouseZoom(xMinNeu, yMinNeu, xMaxNeu, yMaxNeu);

			String faktor = (String) zoomList.getSelectedItem();
			float zoomFa;
			zoomFa = Float.parseFloat(faktor);

			setScaleFactor(getScaleFactor() * mapPanel.getWidth() / (xMaxNeu - xMinNeu));

			title.setText(("Zoom factor ca. : " + this.getScaleFactor() * zoomFa).substring(0, 21));

			mapPanel.calcScale();
			String inter = "" + mapPanel.getScale();
			scaleText.setText("Widht: " + inter.substring(0, 5) + " km");
			sToolBar.validate();

			mapPanel.repaint();
		}

		public void setScaleFactor(double f) {
			scaleFactor = f;
		}

		public double getScaleFactor() {
			return this.scaleFactor;
		}

		private double scaleFactor;
		{
			setScaleFactor(1);
		}

	}

	/**
	 * SelectObject gets invoked by ActionListener to select a map object.
	 */
	class SelectObject implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Object objectSelection = event.getSource();
			// JButton b = (JButton) objectSelection;
			// String cmd = b.getText();
		}
	}

	private JButton mapLayer = new JButton("map layer");
	private JButton fileOpen = new JButton("open map");
	private JButton fullMap = new JButton("full map"
	/* , new ImageIcon ("fullView.gif") */);
	private JButton displayMap = new JButton("repaint map");
	private JButton zoomIn = new JButton("zoom in", new ImageIcon("zoom+.gif"));
	private JButton zoomOut = new JButton("zoom out", new ImageIcon("zoom-.gif"));
	private JButton mouseZoom = new JButton("mouse zoom", new ImageIcon("zoom.gif"));
	private JButton panRight = new JButton("right");
	private JButton panLeft = new JButton("left");
	private JButton panUp = new JButton("up");
	private JButton panDown = new JButton("down");
	private JButton moveFree = new JButton("move", new ImageIcon("hand.gif"));
	private JButton objectSelection = new JButton("select object");
	private JButton stopApp = new JButton("close");
	private JLabel title = new JLabel("");
	private JLabel scaleText = new JLabel(" ");
	private JLabel zoomLevel = new JLabel("zoom fakcor");
	private JComboBox zoomList = new JComboBox();
	private JToolBar toolBar = new JToolBar();
	private JToolBar sToolBar = new JToolBar();
	private MapPanel mapPanel = new MapPanel();
	private Icon hand = new ImageIcon("../icon/hand.gif");
	private ImageIcon zoom = new ImageIcon("../icon/zoom.gif");

	private MapFile mFile = new MapFile();
	private MapZoom mZoom = new MapZoom();
	private MapDraw mDraw = new MapDraw();
	private MoveMap mMove = new MoveMap();
	private MapLayer mMapLayer = new MapLayer();
	private String prjPath = "";

	private int moveWidth;

	private boolean bReady = false;

	public MapFile getFile() {
		return mFile;
	}

	public MapZoom getZoom() {
		return mZoom;
	}

	public MapDraw getMapDraw() {
		return mDraw;
	}

	public MoveMap getMoveMap() {
		return mMove;
	}

	public MapLayer getMapLayer() {
		return mMapLayer;
	}

	{
		this.setMoveWidth(200);
	}

	public void setPrjPath(String prj) {
		this.prjPath = prj;
	}

}