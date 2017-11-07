package simplegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * LayerDialog can be used to select the map layers. 
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
class LayerDialog extends JDialog {

	public LayerDialog(Frame frame, boolean modal) {
		super(frame, modal);
		setTitle("Map Layers");
		setSize(270, 200);
		setLocation(50, 100);
		setResizable(false);

		setLayout();
		registerListener();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void setLayout() {

		this.getContentPane().setLayout(new BorderLayout());

		// WEST:
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(5, 1, 10, 10));
		// SOUTH::
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 2, 10, 10));

		steets.setSelected(true);
		rivers.setSelected(true);
		railways.setSelected(true);
		water.setSelected(true);
		cities.setSelected(true);
		selectAll.setSelected(false);

		westPanel.add(steets);
		westPanel.add(rivers);
		westPanel.add(railways);
		westPanel.add(water);
		westPanel.add(cities);

		southPanel.add(oK);
		southPanel.add(cancel);

		/*** ToolTipText ***/
		oK.setToolTipText("OK");
		cancel.setToolTipText("Cancel");
		steets.setToolTipText("Select streets");
		rivers.setToolTipText("Select rivers");
		railways.setToolTipText("Select railways");
		water.setToolTipText("Select water areas");
		cities.setToolTipText("Select cities");

		this.getContentPane().add(westPanel, BorderLayout.WEST);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
	}

	private void registerListener() {

		cancel.addActionListener(new Stop());
		oK.addActionListener(new LayerSelection());
	}

	class Stop implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			dispose();
		}
	}

	class LayerSelection implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object oK = event.getSource();
			JButton b = (JButton) oK;
			String cmd = b.getText();

			if (steets.isSelected()) {
				setStreetSelection(true);
			}
			if (railways.isSelected()) {
				setRailwaysSelection(true);
			}
			if (rivers.isSelected()) {
				setRiverSelection(true);
			}
			if (water.isSelected()) {
				setWaterAreas(true);
			}
			if (cities.isSelected()) {
				setCitySelection(true);
			}
			dispose();
		}
	}

	private JButton oK = new JButton("OK");
	private JButton cancel = new JButton("Cancel");

	private JCheckBox steets = new JCheckBox("Streets");
	private JCheckBox railways = new JCheckBox("Railways");
	private JCheckBox rivers = new JCheckBox("Rivers");
	private JCheckBox water = new JCheckBox("Water Areas");
	private JCheckBox cities = new JCheckBox("Cities");
	private JCheckBox selectAll = new JCheckBox("Select All");

	private boolean streetSelection;
	private boolean railwaysSelection;
	private boolean riverSelection;
	private boolean waterAreas;
	private boolean citySelection;

	public void setStreetSelection(boolean displayStatus) {
		streetSelection = displayStatus;
	}

	public boolean getStreetSelection() {
		return streetSelection;
	}

	public void setRailwaysSelection(boolean displayStatus) {
		railwaysSelection = displayStatus;
	}

	public boolean getRailwaySelecction() {
		return railwaysSelection;
	}

	public void setRiverSelection(boolean displayStatus) {
		riverSelection = displayStatus;
	}

	public boolean getRiverSelection() {
		return riverSelection;
	}

	public void setWaterAreas(boolean displayStatus) {
		waterAreas = displayStatus;
	}

	public boolean getWaterAreas() {
		return waterAreas;
	}

	public void setCitySelection(boolean displayStatus) {
		citySelection = displayStatus;
	}

	public boolean getCitySelection() {
		return citySelection;
	}

}
