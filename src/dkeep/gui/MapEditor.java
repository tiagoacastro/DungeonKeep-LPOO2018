package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dkeep.Hardcoded.GameLevels;
import dkeep.logic.*;

import java.awt.*;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.*;

import static dkeep.gui.MapEditor.charButtonPressed.DOOR;
import static dkeep.gui.MapEditor.charButtonPressed.WALL;


public class MapEditor extends JFrame implements MouseListener, MouseMotionListener {

	private JFrame frame;
	private GamePanel gameBox;
	private Character[][] map;
	private int dimensionX;
	private int dimensionY;
	private Game game;
	private int ogreCount;
	private JButton btnStartTheCreation;
	private JTextField textField;
	private JTextField textField2;
	private JLabel lblSize;
	private JLabel lblSize2;
	private JButton ogre;
	private JButton hero;
	private JButton wall;
	private JButton key;
	private JButton door;
	private JButton finished;
	private JButton defaultButton;

	private charButtonPressed currButton = charButtonPressed.NONE;

	private JLabel instruction;

	private void createMap() {

		Character[][] map1 = new Character[dimensionY][dimensionX];

		for (int i = 0; i < dimensionY; i++) {
			for (int j = 0; j < dimensionX; j++) {

				if (i == 0 || i == dimensionY - 1 || j == 0 || j == dimensionX - 1) {
					map1[i][j] = 'X';
				} else map1[i][j] = ' ';
			}
		}

		map = map1;
		this.game.getLevels().get(1).setLvlMap(map1);

	}

	MapEditor(Game game) {

		this.game = game;
		this.game.incLevel();

		initializeFrame();

		lblSize = new JLabel("Width");
		lblSize.setBounds(85, 40, 60, 20);
		lblSize.setForeground(Color.white);
		frame.getContentPane().add(lblSize);
		lblSize2 = new JLabel("Height");
		lblSize2.setBounds(85, 80, 60, 20);
		lblSize2.setForeground(Color.white);
		frame.getContentPane().add(lblSize2);

		initializeTextField();
		initializeCreateButton();
		ogreCount = 0;
	}

	private void initializeInstructionLabel() {
		lblSize2 = new JLabel("You can remove certain objetcs by clicking right button on the mouse!");
		lblSize2.setBounds(50, 50+20 + dimensionY*32, 400, 20);
		lblSize2.setForeground(Color.white);
		frame.getContentPane().add(lblSize2);
	}
	private void initializeFrame() {
		//frame
		frame = new JFrame("Map Editor");
		frame.setBounds(50, 50, 200, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(32,32,32));
	}

	private void initializeCreateButton() {
		btnStartTheCreation = new JButton("Start Creation");
		btnStartTheCreation.addActionListener(new CreationEvent());
		btnStartTheCreation.setBounds(40, 140, 120, 20);
		btnStartTheCreation.setBackground(Color.white);
		frame.getContentPane().add(btnStartTheCreation);
	}

	private void initializeTextField() {
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setBounds(70, 60, 60, 20);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setColumns(10);

		textField2 = new JTextField();
		frame.getContentPane().add(textField2);
		textField2.setBounds(70, 100, 60, 20);
		textField2.setHorizontalAlignment(JTextField.CENTER);
		textField2.setColumns(10);
	}

	private void initializePanel() {
		//game box
		gameBox = new GamePanel(game);
		gameBox.setVisible(false);
		gameBox.setFocusable(true);
		gameBox.addMouseListener(this);
		frame.add(gameBox);
	}

	public enum charButtonPressed {
		HERO, OGRE, WALL, KEY, DOOR, NONE
	}

	private void initializeCharButtons() {
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
		finished.setBackground(Color.white);
		frame.getContentPane().add(finished);
	}

	private void initializeDefaultButton() {
		defaultButton = new JButton("Default Map");
		defaultButton.addActionListener(new DefaultButtonEvent());
		defaultButton.setBounds(475, 270, 150, 60);
		defaultButton.setVisible(true);
		defaultButton.setBackground(Color.white);
		frame.getContentPane().add(defaultButton);
	}

	private void initializeDoorButton() {
		door = new JButton("Door");
		door.addActionListener(new DoorEvent());
		door.setBounds(500, 210, 100, 30);
		door.setVisible(true);
		door.setBackground(Color.white);
		frame.getContentPane().add(door);
	}

	private void initializeKeyButton() {
		key = new JButton("Key");
		key.addActionListener(new KeyEvent());
		key.setBounds(500, 170, 100, 30);
		key.setVisible(true);
		key.setBackground(Color.white);
		frame.getContentPane().add(key);
	}

	private void initializeWallButton() {
		wall = new JButton("Wall");
		wall.addActionListener(new WallEvent());
		wall.setBounds(500, 130, 100, 30);
		wall.setVisible(true);
		wall.setBackground(Color.white);
		frame.getContentPane().add(wall);
	}

	private void initializeHeroButton() {
		hero = new JButton("Hero");
		hero.addActionListener(new HeroEvent());
		hero.setBounds(500, 90, 100, 30);
		hero.setVisible(true);
		hero.setBackground(Color.white);
		frame.getContentPane().add(hero);
	}

	private void initializeOgreButton() {
		ogre = new JButton("Ogre");
		ogre.addActionListener(new OgreEvent());
		ogre.setBounds(500, 50, 100, 30);
		ogre.setVisible(true);
		ogre.setBackground(Color.white);
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

	private boolean checkBoundaries(int cellX, int cellY) {
		return (cellX == 0) || (cellY == 0) || (cellX == dimensionY - 1) || (cellY == dimensionX - 1);
	}

	private boolean checkNotCorners(int cellX, int cellY) {
		return (cellX != 0 && cellY != dimensionX - 1 && cellY != 0) ||
				(cellY != 0 && cellX != dimensionY - 1 && cellX != 0) ||
				(cellX != dimensionY - 1 && cellY != dimensionX - 1 && cellY != 0) ||
				(cellY != dimensionX - 1 && cellX != dimensionY - 1 && cellX != 0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int cellX = arg0.getY() / 32;
		int cellY = arg0.getX() / 32;

		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (checkBoundaries(cellX, cellY)) {
				if (currButton == DOOR) {
					if (checkNotCorners(cellX,cellY)) {
						Door d = new Door(cellX, cellY);
						this.game.getLevel().addDoor(d);
						this.game.getLevel().getPlayMap()[cellX][cellY] = 'I';
					}
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

	private void mouseRightPress(int cellX, int cellY) {
		if (checkBoundaries(cellX,cellY)) {
			int i= this.game.getLevel().findDoor(cellX,cellY);
			if (i>= 0) {
				this.game.getLevel().getDoors().remove(i);
				this.game.getLevel().getMap()[cellX][cellY] = 'X';
			}
		} else if (map[cellX][cellY] == 'X') {
            map[cellX][cellY] = ' ';
        } else if (this.game.getLevel().findOgre(cellX,cellY) != -1){
			this.game.getLevel().getChars().remove(this.game.getLevel().findOgre(cellX,cellY));
			this.game.getLevel().getMap()[cellX][cellY] = ' ';
		}
	}

	private void heroPressedHandler(int cellX, int cellY) {

		if(game.getLevel().getHero() != null){
			this.game.getLevels().get(1).getHero().setX(cellX);
			this.game.getLevels().get(1).getHero().setY(cellY);
		} else {
			Hero hero = new Hero(cellX,cellY);
			this.game.getLevel().setHero(hero);
		}
	}

	private Ogre createOgreForEditing(int cellX, int cellY) {

		if (map[cellX + 1][cellY] != 'X' && map[cellX + 1][cellY] != 'I')
			return new Ogre(cellX, cellY, cellX + 1, cellY);
		else if (map[cellX - 1][cellY] != 'X' && map[cellX - 1][cellY] != 'I')
			return new Ogre(cellX, cellY, cellX - 1, cellY);
		else if (map[cellX][cellY + 1] != 'X' && map[cellX][cellY + 1] != 'I')
			return new Ogre(cellX, cellY, cellX, cellY + 1);
		else if (map[cellX][cellY - 1] != 'X' && map[cellX][cellY - 1] != 'I')
			return new Ogre(cellX, cellY, cellX, cellY - 1);

		return new Ogre(0,0,0,0);
	}

	private void currButtonHandler(int cellX, int cellY) {
		int ogres = game.getLevel().getChars().size();
		switch (currButton) {
          case HERO:
			  heroPressedHandler(cellX,cellY);
              break;
          case OGRE:
          	  Ogre o = createOgreForEditing(cellX,cellY);
          	  if(o.getClubX()== 0 && o.getClubY() ==0)
          	  	return;
			  this.game.getLevel().addOgre(o);
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

				String width = textField.getText();
				String height = textField2.getText();
                
                if ((width.equals("9") || width.equals("10") || width.equals("11")) && (height.equals("9") || height.equals("10") || height.equals("11"))) {
					int mazeWidth = Integer.parseInt(textField.getText());
					int mazeHeight = Integer.parseInt(textField2.getText());
					frame.setBounds(50, 50, 670, 500);
					dimensionX = mazeWidth;
					dimensionY = mazeHeight;
                	createMap();
                	textField.setVisible(false);
					textField2.setVisible(false);
                	initializePanel();
                	btnStartTheCreation.setVisible(false);
                	lblSize.setVisible(false);
					lblSize2.setVisible(false);
                	gameBox.setBounds(50, 50, 32* dimensionX, 32 *dimensionY);
                	gameBox.setVisible(true);
                	initializeCharButtons();
					initializeInstructionLabel();
                }
                else JOptionPane.showMessageDialog(frame, "You have to insert a positive number between 9 and 11");
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

				if (canPlay()) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					DungeonKeepGUI gui = new DungeonKeepGUI(game);

					game.decLevel();
					game.updateLevelCopy(1);
				} else JOptionPane.showMessageDialog( frame, "A level must contain a hero, between 1 and 5 ogres, a door and a key!");
			}
		}

		private boolean canPlay() {

		if (game.getLevel().getChars().size() >0 && game.getLevel().getChars().size() <= 5 )
			if (game.getLevel().getDoors().size() != 0)
				return true;
			return false;
		}

	private class DefaultButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			int i = game.getLevels().get(1).getChars().size();
			game.getLevels().remove(1);
			game.getLevelsCopy().remove(1);
			GameLevels.loadLevel2(i, game);
			createMap();
			game.getLevels().get(1).draw();
			gameBox.repaint();
		}
	}
}
