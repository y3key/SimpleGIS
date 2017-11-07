package simplegis;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * InfoDialog shows basic information about application.
 * Copyright: Copyright (c) 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */
public class InfoDialog extends JDialog {

	public InfoDialog(Frame frame, boolean modal) {
		super(frame, modal);
		setTitle("SimpleGIS Info");
		setSize(270, 180);
		setLocation(50, 100);
		setResizable(false);

		createLayout();
		registerListener();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void createLayout() {

		this.getContentPane().setLayout(new BorderLayout());

		// NORTH:
		toolBar.setFloatable(false);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setToolTipText("SimpleGIS Infos");
		toolBar.createToolTip();

		name.setForeground(Color.blue);
		name.setPreferredSize(new Dimension(120, 40));
		name.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cred.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cred.setForeground(Color.blue);

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(4, 1, 5, 5));
		toolBar.add(name);
		toolBar.add(cred);
		toolBar.addSeparator(new Dimension(0, 10));
		toolBar.add(version);
		toolBar.add(year);

		// SOUTH:
		JPanel southPanel = new JPanel();
		southPanel.add(oK);

		// *** ToolTipText:
		oK.setToolTipText("OK");

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
	}

	private void registerListener() {

		oK.addActionListener(new StopApplication());

	}

	class StopApplication implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			dispose();
		}
	}

	private JButton oK = new JButton("OK");
	private JLabel name = new JLabel("SimpleGIS");
	private JLabel cred = new JLabel("Stefan Friese");
	private JLabel version = new JLabel("Version 1.1");
	private JLabel year = new JLabel("Copyright (c) 2017 Created: 2002");

	private JToolBar toolBar = new JToolBar();
}