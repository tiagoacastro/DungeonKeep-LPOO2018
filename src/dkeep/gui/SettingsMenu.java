package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dkeep.logic.Game;

public class SettingsMenu {

	private JFrame settingsMenu;
	private JComboBox<String> guardType;
	private JButton defaultKeep;
	private JButton editKeep;
	private JTextField ogres;
	private Game game;
	
	public SettingsMenu() {

		initializeFrame();

		initializeGuardInput();
		
		initializeButtons();
		
		initializeOgreInput();
	}

	private void initializeFrame() {
		settingsMenu = new JFrame("Game Settings");
		settingsMenu.setBounds(100, 100, 300, 400);
		settingsMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settingsMenu.getContentPane().setLayout(null);
		settingsMenu.setVisible(true);
	}

	private void initializeButtons() {
		defaultKeep = new JButton("Use default keep level");
		defaultKeep.addActionListener(new defaultKeepEvent());
		defaultKeep.setBounds(40, 274, 210, 21);
		settingsMenu.getContentPane().add(defaultKeep);
		
		editKeep = new JButton("Edit the keep level");
		editKeep.addActionListener(new editKeepEvent());
		editKeep.setBounds(40, 300, 210, 21);
		settingsMenu.getContentPane().add(editKeep);
	}
	
	public void initializeGuardInput() {
		//guard input
		JLabel guardLabel = new JLabel();
		guardLabel.setText("Guard personality");
		guardLabel.setBounds(30, 30, 100, 20);
		settingsMenu.getContentPane().add(guardLabel);

		guardType = new JComboBox();
		guardType.setBounds(135, 30, 100, 20);
		guardType.addItem("Rookie");
		guardType.addItem("Drunken");
		guardType.addItem("Suspicious");
		settingsMenu.getContentPane().add(guardType);
	}
	
	public void initializeOgreInput() {
		//ogre input
		JLabel ogreLabel = new JLabel();
		ogreLabel.setText("Number of Ogres");
		ogreLabel.setBounds(30, 80, 100, 20);
		settingsMenu.getContentPane().add(ogreLabel);

		ogres = new JTextField(10);
		ogres.setBounds(135, 80, 100, 20);
		settingsMenu.getContentPane().add(ogres);
	}

	public void startGame(String type) {

		game = new Game();
		game.loadLevel1(guardType.getSelectedItem().toString());
		DungeonKeepGUI gui;
		MapEditor mapEditor;

		if (ogres.getText().equals("1") || ogres.getText().equals("2") || ogres.getText().equals("3") || ogres.getText().equals("4") || ogres.getText().equals("5")) {
			game.loadLevel2(Integer.parseInt(ogres.getText()));
			game.getLevel().draw();

			if (type == "default")
			 gui = new DungeonKeepGUI();
			else if (type == "editor") mapEditor = new MapEditor();
		} else {
			JOptionPane.showMessageDialog(settingsMenu, "You have to insert a positive number of 5 or less!");
		}
	}

	private class defaultKeepEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			startGame("default");
			
		}
		
	}

	private class editKeepEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			startGame("editor");
		}
	}
}
