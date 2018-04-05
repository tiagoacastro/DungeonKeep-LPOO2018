package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JOptionPane;

import dkeep.logic.*;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static dkeep.gui.MapEditor.charButtonPressed.DOOR;
import static dkeep.gui.MapEditor.charButtonPressed.WALL;


public class MapEditor extends JFrame implements MouseListener, MouseMotionListener {

	private JFrame frame;
	private GamePanel gameBox;
	private Character[][] map;
	private int dimension;
	private Game game;
	private int ogreCount;
	private JButton btnStartTheCreation;
	private JTextField textField;
	private JLabel lblSize;
	private JButton ogre;
	private JButton hero;
	private JButton wall;
	private JButton key;
	private JButton door;
	private JButton finished;
	private JButton defaultButton;

	private charButtonPressed currButton = charButtonPressed.NONE;

	public void createMap() {

		Character[][] map1 = new Character[dimension][dimension];

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {

				if (i == 0 || i == dimension - 1 || j == 0 || j == dimension - 1) {
					map1[i][j] = 'X';
				} else map1[i][j] = ' ';
			}
		}

		map = map1;
		this.game.getLevels().get(1).setMap(map1);
		this.game.getLevels().get(1).setLvlMap(map1);

	}

	public MapEditor(Game game) {

		this.game = game;
		this.game.incLevel();

		initializeFrame();

		lblSize = new JLabel("Size");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 1;
		gbc_lblSize.gridy = 1;
		frame.getContentPane().add(lblSize, gbc_lblSize);

		initializeTextField();
		initializeCreateButton();
		ogreCount = 0;
	}

	public void initializeFrame() {
		//frame
		frame = new JFrame("Map Editor");
		frame.setBounds(50, 50, 750, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setVisible(true);
	}

	public void initializeCreateButton() {
		btnStartTheCreation = new JButton("Start the Creation !");
		btnStartTheCreation.addActionListener(new CreationEvent());
		GridBagConstraints gbc_btnStartTheCreation = new GridBagConstraints();
		gbc_btnStartTheCreation.gridx = 4;
		gbc_btnStartTheCreation.gridy = 2;
		frame.getContentPane().add(btnStartTheCreation, gbc_btnStartTheCreation);
	}

	public void initializeTextField() {
		textField = new JTextField();
		textField.setText("11");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
	}

	void initializePanel() {
		//game box
		gameBox = new GamePanel(game);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gameBox.setVisible(false);
		gameBox.setFocusable(true);
		gameBox.addMouseListener(this);
		frame.add(gameBox, gbc_panel);
	}

	public enum charButtonPressed {
		HERO, OGRE, WALL, KEY, DOOR, NONE
	}

	void initializeCharButtons() {
		initializeOgreButton();

		initializeHeroButton();

		initializeWallButton();

		initializeKeyButton();

		initializeDoorButton();

		initializeFinishButton();

		initializeDefaultButton();
	}

	private void initializeFinishButton() {
		finished = new JButton("Finished");
		finished.addActionListener(new FinishedEvent());
		finished.setBounds(475, 340, 150, 60);
		finished.setVisible(true);
		frame.getContentPane().add(finished);
	}

	private void initializeDefaultButton() {
		defaultButton = new JButton("Default Map");
		defaultButton.addActionListener(new DefaultButtonEvent());
		defaultButton.setBounds(475, 270, 150, 60);
		defaultButton.setVisible(true);
		frame.getContentPane().add(defaultButton);
	}

	private void initializeDoorButton() {
		door = new JButton("Door");
		door.addActionListener(new DoorEvent());
		door.setBounds(500, 210, 100, 30);
		door.setVisible(true);
		frame.getContentPane().add(door);
	}

	private void initializeKeyButton() {
		key = new JButton("Key");
		key.addActionListener(new KeyEvent());
		key.setBounds(500, 170, 100, 30);
		key.setVisible(true);
		frame.getContentPane().add(key);
	}

	private void initializeWallButton() {
		wall = new JButton("Wall");
		wall.addActionListener(new WallEvent());
		wall.setBounds(500, 130, 100, 30);
		wall.setVisible(true);
		frame.getContentPane().add(wall);
	}

	private void initializeHeroButton() {
		hero = new JButton("Hero");
		hero.addActionListener(new HeroEvent());
		hero.setBounds(500, 90, 100, 30);
		hero.setVisible(true);
		frame.getContentPane().add(hero);
	}

	private void initializeOgreButton() {
		ogre = new JButton("Ogre");
		ogre.addActionListener(new OgreEvent());
		ogre.setBounds(500, 50, 100, 30);
		ogre.setVisible(true);
		frame.getContentPane().add(ogre);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean checkBoundaries(int cellX, int cellY) {
		if ((cellX == 0) || (cellY == 0) || (cellX == dimension - 1) || (cellY == dimension - 1))
			return true;
		else return false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int cellX = arg0.getY() / 32;
		int cellY = arg0.getX() / 32;

		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (checkBoundaries(cellX, cellY)) {
				if (currButton == DOOR) {
					Door d = new Door(cellX, cellY);
					this.game.getLevels().get(1).addDoor(d);
				} else if (currButton == WALL)
					map[cellX][cellY] = 'X';
			} else {
				currButtonHandler(cellX, cellY);
			}
		} else if (arg0.getButton() == MouseEvent.BUTTON3)
			mouseRightPress(cellX,cellY);

		this.game.getLevels().get(1).draw();
		this.game.getLevels().get(1).setMap(map);
		this.gameBox.repaint();
	}

	public void mouseRightPress(int cellX, int cellY) {
		if (map[cellX][cellY] == 'X') {
			map[cellX][cellY] = ' ';
		}
	}

	public void currButtonHandler(int cellX, int cellY) {
		int ogres = game.getLevel().getChars().size();
		switch (currButton) {
          case HERO:
			  this.game.getLevels().get(1).getHero().setX(cellX);
			  this.game.getLevels().get(1).getHero().setY(cellY);
              break;
          case OGRE:
			  if (ogreCount >= ogres)
				  ogreCount = 0;
			  this.game.getLevels().get(1).getChars().get(ogreCount).setX(cellX);
              this.game.getLevels().get(1).getChars().get(ogreCount).setY(cellY);
              if (this.game.getLevels().get(1).getChars().get(ogreCount) instanceof Ogre)
				  ((Ogre)this.game.getLevels().get(1).getChars().get(ogreCount)).updateClub(map);

              ogreCount++;
              break;
			case WALL:
				map[cellX][cellY] = 'X';
				break;
              case KEY:
                  this.game.getLevels().get(1).getObject().setX(cellX);
                  this.game.getLevels().get(1).getObject().setY(cellY);
              break;
          default:
              break;
      }
	}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
		
		private class CreationEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
                int mazeSize = Integer.parseInt(textField.getText());
                
                if (mazeSize >= 8 && mazeSize <=13) {
                	dimension = mazeSize;
                	createMap();
                	textField.setVisible(false);
                	initializePanel();
                	btnStartTheCreation.setVisible(false);
                	lblSize.setVisible(false);
                	frame.getContentPane().setLayout(null);
                	gameBox.setBounds(50, 50, 32* dimension, 32 *dimension);
                	gameBox.setVisible(true);
                	initializeCharButtons();
                }
                else JOptionPane.showMessageDialog(frame, "You have to insert a positive number between 8 and 13!");
			}
		}
		
		private class OgreEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				currButton = charButtonPressed.OGRE;
			}
		}
		
		private class HeroEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				currButton = charButtonPressed.HERO;
			}
		}
		
		private class WallEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				currButton = WALL;
			}
		}
		

		private class KeyEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				currButton = charButtonPressed.KEY;
			}
		}

		private class DoorEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				currButton = DOOR;
			}
		}

		private class FinishedEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {

				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				DungeonKeepGUI gui = new DungeonKeepGUI(game);

				game.decLevel();
				game.updateLevel2Copy();
			}
		}

	private class DefaultButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			int i = game.getLevels().get(1).getChars().size();
			game.getLevels().remove(1);
			game.loadLevel2(i);
			createMap();
			game.getLevels().get(1).draw();
			gameBox.repaint();
		}
	}
}
