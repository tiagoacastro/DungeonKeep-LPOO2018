package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import dkeep.logic.Game;

import dkeep.cli.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

//TODO
public class MainMenu extends JPanel {

	private JFrame menuWindow;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnExitGame;
	private MenuBackground background;

	//main function

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu();
			}
		});
	}

	private MainMenu() {

		initializeFrame();
		initializeButtons();
		initializePanel();
	}

	private void initializeFrame() {
		//frame
		menuWindow = new JFrame("Dungeon Balls");
		menuWindow.setBounds(100, 100, 640, 480);
		menuWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuWindow.setVisible(true);
		menuWindow.setResizable(false);
	}

	private void initializePanel() {

		background = new MenuBackground();
		background.setBounds(0, 0, 640, 480);
		background.setVisible(true);
		background.setFocusable(false);
		menuWindow.getContentPane().add(background);
	}

	private void initializeButtons() {
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Impact", Font.PLAIN, 12));
		btnNewGame.addActionListener(new newGameEvent());
		btnNewGame.setBounds(117, 60, 170, 50);
		btnNewGame.setBackground(Color.white);
		menuWindow.getContentPane().setLayout(null);
		menuWindow.getContentPane().add(btnNewGame);
		
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.setFont(new Font("Impact", Font.PLAIN, 13));
		btnLoadGame.addActionListener(new loadGameEvent());
		btnLoadGame.setBounds(117, 190, 170, 50);
		btnLoadGame.setBackground(Color.white);
		menuWindow.getContentPane().add(btnLoadGame);
		
		btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("Impact", Font.PLAIN, 12));
		btnExitGame.addActionListener(new exitGameEvent());
		btnExitGame.setBounds(117, 340, 170, 50);
		btnExitGame.setBackground(Color.white);
		menuWindow.getContentPane().add(btnExitGame);

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
			Game game = null;
			boolean success = false;
			JOptionPane pane = new JOptionPane();
			while(!success) {

				String file = pane.showInputDialog(null,
						"Enter file name:");
				try {
					if(file == null)
						return;
					game = null;
					game = UserInterface.loadState(file);
					success = true;
				} catch (IOException i) {
					JOptionPane.showMessageDialog(pane, "File Not Found");
				} catch (ClassNotFoundException c) {
					c.printStackTrace(); }}
			new DungeonKeepGUI(game);
        }
    }
}
