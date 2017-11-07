package simplegis;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Defines frame elements Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public class Frame extends JFrame {

	public Frame(String title) {
		setTitle(title);
		setSize(900, 700);
		setLocation(200, 100);
		setResizable(true);

		myMenu = new Menu();

		// Panel:
		getContentPane().add(mainPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	Menu myMenu;

	Menu getMenu() {
		return myMenu;
	}

	class Menu implements ActionListener {
		public Menu() {

			registerListener();
			this.moveW200.setForeground(Color.blue);

			activateMenu(true);

			/*** File ***/
			file.setMnemonic('F');
			mapSelection.setMnemonic('M');
			mapSelection.add(map);
			file.add(mapSelection);
			file.addSeparator();
			file.add(close);
			menuBar.add(file);

			presentationRules.setMnemonic('a');
			presentationRules.add(pRuleCity);
			presentationRules.add(pRuleStreet);
			presentationRules.add(pRuleRailway);
			presentationRules.add(pRuleWater);
			presentationRules.add(pRuleRiver);
			presentationRules.addSeparator();
			presentationRules.add(pRuleBackground);
			menuBar.add(presentationRules);

			/*** Zoom ***/
			zoom.setMnemonic('Z');
			zoom.add(zoomAll);
			zoom.addSeparator();
			zoom.add(zoomIn);
			zoom.add(zoomOut);
			zoom.addSeparator();
			zoom.add(zoomMouse);
			// TBC only as buttons
			// menuBar.add(zoom);

			/*** Move ***/
			move.setMnemonic('V');
			move.add(moveRight);
			move.add(moveLeft);
			move.add(moveUp);
			move.add(moveDown);
			move.addSeparator();
			move.add(moveFree);
			moveW.setMnemonic('w');
			moveW.add(moveW50);
			moveW.add(moveW100);
			moveW.add(moveW150);
			moveW.add(moveW200);
			moveW.add(moveW250);
			move.add(moveW);
			// TBC only as bottons
			// menuBar.add( move );

			/*** Layers ***/
			layers.setMnemonic('L');
			layers.add(layerDialog);
			menuBar.add(layers);

			/*** Help ***/
			help.setMnemonic('H');
			help.add(helpDialog);
			menuBar.add(help);

			setJMenuBar(menuBar);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent event) {
			String cmd = event.getActionCommand();
			System.out.println("Frame ActionEvent " + cmd);

			/*** File ***/
			if (cmd.equalsIgnoreCase("close")) {
				System.exit(0);
			}
			if (cmd.equalsIgnoreCase("open map")) {
				Frame parentFrame = (Frame) getParent();
				FileDialog fd = new FileDialog(parentFrame, "Choose a map file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.map");
				fd.setVisible(true);
				mainPanel.setPrjPath(fd.getDirectory());
				mainPanel.getMapPanel().setScreenXmax(mainPanel.getMapPanel().getWidth());
				mainPanel.getMapPanel().getLogic().loadPrj(fd.getDirectory());
				mainPanel.getMapPanel().setScreenYmaxOrig((int) (mainPanel.getMapPanel().getScreenXmax()
						* mainPanel.getMapPanel().getLogic().getRatio()));
				mainPanel.getMapPanel().setScreenYmax(mainPanel.getMapPanel().getScreenYmaxOrig());
				mainPanel.repaint();
				mainPanel.activateUI(true);
				mainPanel.refreshLabel("Opened Map");

				activateMenu(true);
			}

			if (cmd.equalsIgnoreCase("background")) {
				Color myColor = JColorChooser.showDialog(null, "Background Color",
						mainPanel.getMapPanel().getBackground());
				mainPanel.getMapPanel().setBackground(myColor);
			}

			if (cmd.equalsIgnoreCase("street")) {

				try {
					int red, green, blue; // RGB

					Color steetColor = new Color(
							((Street) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllStreets()
									.getStreetList().get(/* i */1)).getLineColor().getRed(),
							((Street) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllStreets()
									.getStreetList().get(/* i */1)).getLineColor().getGreen(),
							((Street) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllStreets()
									.getStreetList().get(/* i */1)).getLineColor().getBlue());

					Color myColor = JColorChooser.showDialog(null, "Street Color", steetColor);
					if (myColor != null) {
						red = myColor.getRed();
						green = myColor.getGreen();
						blue = myColor.getBlue();
						System.out.println("X2X");

						for (int i = 0; i < mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllStreets()
								.getStreetList().size(); i++) {
							((Street) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllStreets()
									.getStreetList().get(i)).setLineColor(red, green, blue);
						}
					}
					mainPanel.repaint();
				} catch (NullPointerException e) {
					System.out.println("Map not ready.");
				}
			}

			if (cmd.equalsIgnoreCase("railway")) {
				try {
					int red, green, blue;

					Color railwayColor = new Color(
							((Railway) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRailways()
									.getRailwayList().get(/* i */1)).getLineColor().getRed(),
							((Railway) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRailways()
									.getRailwayList().get(/* i */1)).getLineColor().getGreen(),
							((Railway) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRailways()
									.getRailwayList().get(/* i */1)).getLineColor().getBlue());

					Color myColor = JColorChooser.showDialog(null, "Railway Color", railwayColor);
					if (myColor != null) {
						red = myColor.getRed();
						green = myColor.getGreen();
						blue = myColor.getBlue();

						for (int i = 0; i < mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRailways()
								.getRailwayList().size(); i++) {
							((Railway) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRailways()
									.getRailwayList().get(i)).setLineColor(red, green, blue);
						}

						mainPanel.repaint();
					}
				} catch (NullPointerException e) {
					System.out.println("Map not ready.");
				}
			}

			if (cmd.equalsIgnoreCase("river")) {
				try {
					int red, green, blue;

					Color flussfarbe = new Color(
							((River) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRivers().getRiverList()
									.get(/* i */1)).getLineColor().getRed(),
							((River) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRivers().getRiverList()
									.get(/* i */1)).getLineColor().getGreen(),
							((River) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRivers().getRiverList()
									.get(/* i */1)).getLineColor().getBlue());

					Color myColor = JColorChooser.showDialog(null, "River Color", flussfarbe);
					if (myColor != null) {
						red = myColor.getRed();
						green = myColor.getGreen();
						blue = myColor.getBlue();

						for (int i = 0; i < mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRivers()
								.getRiverList().size(); i++) {
							((River) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllRivers().getRiverList()
									.get(i)).setLineColor(red, green, blue);
						}
					}
					mainPanel.repaint();
				} catch (NullPointerException e) {
					System.out.println("Map not ready.");
				}
			}

			if (cmd.equalsIgnoreCase("water")) {
				try {
					int red, green, blue;

					Color waterColor = new Color(
							((WaterArea) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllWaterAreas()
									.getWaterAreaList().get(/* i */1)).getLineColor().getRed(),
							((WaterArea) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllWaterAreas()
									.getWaterAreaList().get(/* i */1)).getLineColor().getGreen(),
							((WaterArea) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllWaterAreas()
									.getWaterAreaList().get(/* i */1)).getLineColor().getBlue());

					Color myColor = JColorChooser.showDialog(null, "Water Area Color", waterColor);
					if (myColor != null) {
						red = myColor.getRed();
						green = myColor.getGreen();
						blue = myColor.getBlue();

						for (int i = 0; i < mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllWaterAreas()
								.getWaterAreaList().size(); i++) {
							((WaterArea) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllWaterAreas()
									.getWaterAreaList().get(i)).setLineColor(red, green, blue);
						}
					}
					mainPanel.repaint();
				} catch (NullPointerException e) {
					System.out.println("Map not ready.");
				}
			}

			if (cmd.equalsIgnoreCase("city")) {
				try {
					int red, green, blue;

					Color cityColor = new Color(
							((City) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllCities().getCitiesList()
									.get(/* i */1)).getLineColor().getRed(),
							((City) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllCities().getCitiesList()
									.get(/* i */1)).getLineColor().getGreen(),
							((City) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllCities().getCitiesList()
									.get(/* i */1)).getLineColor().getBlue());

					Color myColor = JColorChooser.showDialog(null, "City Color", cityColor);
					if (myColor != null) {

						red = myColor.getRed();
						green = myColor.getGreen();
						blue = myColor.getBlue();

						for (int i = 0; i < mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllCities()
								.getCitiesList().size(); i++) {
							((City) mainPanel.getMapPanel().getLogic().getObjectCatalog().getAllCities().getCitiesList()
									.get(i)).setLineColor(red, green, blue);
						}
					}
					mainPanel.repaint();
				} catch (NullPointerException e) {
					System.out.println("Map not ready.");
				}
			}

			/*** Zoom ***/
			if (cmd.equalsIgnoreCase("all") || cmd.equalsIgnoreCase("zoom in") || cmd.equalsIgnoreCase("zoom out")
					|| cmd.equalsIgnoreCase("mouse zoom")) {
				mainPanel.getZoom().actionPerformed(event);
			}
			if (cmd.equalsIgnoreCase("right") || cmd.equalsIgnoreCase("left") || cmd.equalsIgnoreCase("up")
					|| cmd.equalsIgnoreCase("down") || cmd.equalsIgnoreCase("move")) {
				mainPanel.getMoveMap().actionPerformed(event);
			}

			if (cmd == "50") {
				mainPanel.setMoveWidth(50);
				this.moveW50.setForeground(Color.blue);

				this.moveW100.setForeground(Color.black);
				this.moveW150.setForeground(Color.black);
				this.moveW200.setForeground(Color.black);
				this.moveW250.setForeground(Color.black);
			}
			if (cmd == "100") {
				mainPanel.setMoveWidth(100);
				this.moveW100.setForeground(Color.blue);

				this.moveW50.setForeground(Color.black);
				this.moveW150.setForeground(Color.black);
				this.moveW200.setForeground(Color.black);
				this.moveW250.setForeground(Color.black);
			}
			if (cmd == "150") {
				mainPanel.setMoveWidth(150);
				this.moveW150.setForeground(Color.blue);

				this.moveW100.setForeground(Color.black);
				this.moveW50.setForeground(Color.black);
				this.moveW200.setForeground(Color.black);
				this.moveW250.setForeground(Color.black);
			}
			if (cmd == "200") {
				mainPanel.setMoveWidth(200);
				this.moveW200.setForeground(Color.blue);

				this.moveW50.setForeground(Color.black);
				this.moveW150.setForeground(Color.black);
				this.moveW200.setForeground(Color.black);
				this.moveW250.setForeground(Color.black);
			}
			if (cmd == "250") {
				mainPanel.setMoveWidth(250);
				this.moveW250.setForeground(Color.blue);

				this.moveW100.setForeground(Color.black);
				this.moveW150.setForeground(Color.black);
				this.moveW200.setForeground(Color.black);
				this.moveW50.setForeground(Color.black);
			}

			/*** Layers ***/

			if (cmd.equalsIgnoreCase("map layer")) {
				try {
					mainPanel.getMapLayer().actionPerformed(event);
				} catch (NullPointerException e) {
					System.out.println("Map not ready.");
				}
			}

			/*** Help ***/

			if (cmd.equalsIgnoreCase("info")) {
				Frame parentFrame = (Frame) getParent();
				InfoDialog myInfo = new InfoDialog(parentFrame, true);
				myInfo.show();
			}

		}

		JMenuBar menuBar = new JMenuBar();

		private LayerDialog selection;

		private JMenu file = new JMenu("File");
		private JMenu mapSelection = new JMenu("Maps");
		private JMenuItem map = new JMenuItem("Open Map", 'M');
		private JMenuItem close = new JMenuItem("Close", 'S');

		private JMenu presentationRules = new JMenu("Presentation");
		private JMenuItem pRuleCity = new JMenuItem("City", 'O');
		private JMenuItem pRuleStreet = new JMenuItem("Street", 'S');
		private JMenuItem pRuleRailway = new JMenuItem("Railway", 'B');
		private JMenuItem pRuleWater = new JMenuItem("Water", 'W');
		private JMenuItem pRuleRiver = new JMenuItem("River", 'F');
		private JMenuItem pRuleBackground = new JMenuItem("Background", 'H');

		private JMenu zoom = new JMenu("Zoom");
		private JMenuItem zoomAll = new JMenuItem("Full Map", 'G');
		private JMenuItem zoomIn = new JMenuItem("Zoom in", 'i');
		private JMenuItem zoomOut = new JMenuItem("Zoom out", 'o');
		private JMenuItem zoomMouse = new JMenuItem("Mouse Zoom", 'M');

		private JMenu move = new JMenu("Move");
		private JMenuItem moveRight = new JMenuItem("Right", 'R');
		private JMenuItem moveLeft = new JMenuItem("Left", 'L');
		private JMenuItem moveUp = new JMenuItem("Up", 'O');
		private JMenuItem moveDown = new JMenuItem("Down", 'U');
		private JMenuItem moveFree = new JMenuItem("Move", 'V');

		private JMenu moveW = new JMenu("Move Width");
		private JMenuItem moveW50 = new JMenuItem("50");
		private JMenuItem moveW100 = new JMenuItem("100");
		private JMenuItem moveW150 = new JMenuItem("150");
		private JMenuItem moveW200 = new JMenuItem("200");
		private JMenuItem moveW250 = new JMenuItem("250");

		private JMenu layers = new JMenu("Layers");
		private JMenuItem layerDialog = new JMenuItem("Map Layer", 'E');

		private JMenu help = new JMenu("Help");
		private JMenuItem helpDialog = new JMenuItem("Info", 'I');

		/*
		 * ************************************************************************
		 * Register listener *
		 ************************************************************************/
		private void registerListener() {
			map.addActionListener(this);
			close.addActionListener(this);
			pRuleCity.addActionListener(this);
			pRuleStreet.addActionListener(this);
			pRuleRailway.addActionListener(this);
			pRuleWater.addActionListener(this);
			pRuleRiver.addActionListener(this);
			pRuleBackground.addActionListener(this);
			zoomAll.addActionListener(this);
			zoomIn.addActionListener(this);
			zoomOut.addActionListener(this);
			zoomMouse.addActionListener(this);
			moveRight.addActionListener(this);
			moveLeft.addActionListener(this);
			moveUp.addActionListener(this);
			moveDown.addActionListener(this);
			moveFree.addActionListener(this);
			moveW50.addActionListener(this);
			moveW100.addActionListener(this);
			moveW150.addActionListener(this);
			moveW200.addActionListener(this);
			moveW250.addActionListener(this);
			layerDialog.addActionListener(this);
			helpDialog.addActionListener(this);
		}

		private void activateMenu(boolean status) {
			this.presentationRules.setEnabled(status);
			this.zoom.setEnabled(status);
			this.moveRight.setEnabled(status);
			this.moveLeft.setEnabled(status);
			this.moveUp.setEnabled(status);
			this.moveDown.setEnabled(status);
			this.moveFree.setEnabled(status);
			this.layers.setEnabled(status);
		}

		public JMenuBar getMenue() {
			return menuBar;
		}

	}

	MainPanel mainPanel = new MainPanel();
}