package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import dkeep.logic.Game;

import dkeep.cli.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends JPanel {

	private JFrame menuWindow;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnExitGame;
	private BufferedImage background;

	//main function

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu();
			}
		});
	}

	public MainMenu() {
		initializeFrame();
		initializeButtons();
	}
	
	public void initializeFrame() {
		//frame
		menuWindow = new JFrame("Dungeon Balls");
		menuWindow.setBounds(100, 100, 640, 480);
		menuWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		menuWindow.getContentPane().setLayout(gridBagLayout);
	}
	
	public void initializeButtons() {
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new newGameEvent());
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.gridwidth = 3;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 9;
		gbc_btnNewGame.gridy = 6;
		menuWindow.getContentPane().add(btnNewGame, gbc_btnNewGame);
		
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new loadGameEvent());
		GridBagConstraints gbc_btnLoadGame = new GridBagConstraints();
		gbc_btnLoadGame.gridwidth = 5;
		gbc_btnLoadGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadGame.gridx = 8;
		gbc_btnLoadGame.gridy = 8;
		menuWindow.getContentPane().add(btnLoadGame, gbc_btnLoadGame);
		
		btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new exitGameEvent());
		GridBagConstraints gbc_btnExitGame = new GridBagConstraints();
		gbc_btnExitGame.insets = new Insets(0, 0, 0, 5);
		gbc_btnExitGame.gridx = 10;
		gbc_btnExitGame.gridy = 11;
		menuWindow.getContentPane().add(btnExitGame, gbc_btnExitGame);
		menuWindow.setVisible(true);
	}
	
	private class exitGameEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			menuWindow.dispatchEvent(new WindowEvent(menuWindow, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	private class newGameEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			SettingsMenu settings = new SettingsMenu();
		}
	}

    private class loadGameEvent implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
			String file = JOptionPane.showInputDialog(null,
					"Enter file name:");
			Game game = UserInterface.loadState(file);
			new DungeonKeepGUI(game);
        }
    }
}
