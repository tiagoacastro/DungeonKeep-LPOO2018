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

public class MapEditor extends JPanel implements MouseListener, MouseMotionListener{

	private JFrame frame;
	private GamePanel gameBox;
	private Character[][] map;
	private int dimension;
    private DungeonKeepGUI gui;
    private Game game;
    private int ogreCount;
    private JButton btnStartTheCreation;
    private JTextField textField;
    private JLabel lblSize;
	private JButton ogre;
	
	boolean ogreButtonPressed,heroButtonPressed,wallButtonPressed,keyButtonPressed;
	private JButton hero;
	private JButton wall;
	private JButton key;
	private JButton finished;

	public void createMap(Game g) {

		Character[][]map1 = new Character [dimension][dimension];
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {

				if (i == 0 || i == dimension - 1 || j == 0 || j == dimension - 1) {
					map1[i][j] = 'X';
				}
				else map1[i][j] = ' ';
			}
		}
		
		map = map1;
		this.gui.getGame().getLevel().setMap(map1);
		this.gui.getGame().getLevel().setLvlMap(map1);
	}
	
	public MapEditor(DungeonKeepGUI d) {
		
		gui = d;
		game = d.getGame();
		game.incLevel();
	
		//frame
		frame = new JFrame("Map Editor");
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setVisible(true);
		
		lblSize = new JLabel("Size");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 1;
		gbc_lblSize.gridy = 1;
		frame.getContentPane().add(lblSize, gbc_lblSize);
		
		textField = new JTextField();
		textField.setText("11");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnStartTheCreation = new JButton("Start the Creation !");
		btnStartTheCreation.addActionListener( new CreationEvent());
		GridBagConstraints gbc_btnStartTheCreation = new GridBagConstraints();
		gbc_btnStartTheCreation.gridx = 4;
		gbc_btnStartTheCreation.gridy = 2;
		frame.getContentPane().add(btnStartTheCreation, gbc_btnStartTheCreation);
		
		ogreCount = 0;

	}
	void initializePanel() {
		//game box
				gameBox = new GamePanel(gui);
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridx = 4;
				gbc_panel.gridy = 3;
				gbc_panel.fill = GridBagConstraints.BOTH;
				gameBox.setVisible(false);
				gameBox.setFocusable(true);
				gameBox.addMouseListener(this);
				frame.add(gameBox,gbc_panel);
	}
	
	void initializeCharButtons() {
		ogre = new JButton("Ogre");
		ogre.addActionListener( new OgreEvent());
		ogre.setBounds(400,150,100,50);
		/*GridBagConstraints gbc_ogre = new GridBagConstraints();
		gbc_ogre.gridx = 5;
		gbc_ogre.gridy = 1;
		*/
		ogre.setVisible(true);
		frame.getContentPane().add(ogre);
		
		hero = new JButton("Hero");
		hero.addActionListener( new HeroEvent());
		hero.setBounds(400,215,100,50);
		/*GridBagConstraints gbc_hero = new GridBagConstraints();
		gbc_hero.gridx = 5;
		gbc_hero.gridy = 2;*/
		hero.setVisible(true);
		frame.getContentPane().add(hero);
		
		wall = new JButton("Wall");
		wall.addActionListener( new WallEvent());
		wall.setBounds(400,300,100,50);
		/*GridBagConstraints gbc_wall = new GridBagConstraints();
		gbc_wall.gridx = 5;
		gbc_wall.gridy = 3;
		*/
		wall.setVisible(true);
		frame.getContentPane().add(wall);
		
		key = new JButton("Key");
		key.addActionListener( new KeyEvent());
		key.setBounds(400,375,100,50);
		/*GridBagConstraints gbc_key = new GridBagConstraints();
		gbc_key.gridx = 5;
		gbc_key.gridy = 4;
		*/
		key.setVisible(true);
		frame.getContentPane().add(key);

		finished = new JButton("Finished");
		finished.addActionListener( new FinishedEvent());
		finished.setBounds(400,10,100,50);
		/*GridBagConstraints gbc_key = new GridBagConstraints();
		gbc_key.gridx = 5;
		gbc_key.gridy = 4;
		*/
		finished.setVisible(true);
		frame.getContentPane().add(finished);
		
		
	}
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/*
		public void initializeCombo(int x, int y) {
			
			characterType = new JComboBox();
			characterType.setBounds(x, y, 100, 20);
			characterType.addItem("Hero");
			characterType.addItem("Ogre");
			characterType.addItem("Key");
			characterType.addItem("Wall");
			characterType.setVisible(true);
			frame.getContentPane().add(characterType);
	
		}
		*/

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

		@Override
		public void mousePressed(MouseEvent arg0) {
			System.out.println("pe");
			  int y=arg0.getX();
			  int x=arg0.getY();
			  
			  int cellX = x/25;
			  int cellY = y/25;
			  
			  if (cellX == 0 || cellY == 0)
				  return;
			  if (map[cellX][cellY] == ' ') {
			  if(heroButtonPressed) {
			  	map[this.gui.getGame().getLevels().get(1).getHero().getX()][this.gui.getGame().getLevels().get(1).getHero().getY()] = ' ';

			  	this.gui.getGame().getLevel().getHero().setX(cellX);
			  	this.gui.getGame().getLevel().getHero().setY(cellY);
			  }
				else if(ogreButtonPressed) {
				  this.gui.getGame().getLevels().get(1).getChars().get(ogreCount).setX(cellX);
				  this.gui.getGame().getLevels().get(1).getChars().get(ogreCount).setY(cellX);
				ogreCount++;
				}
				else if(wallButtonPressed) {
					map[cellX][cellY] = 'X';
				}
				else if(keyButtonPressed) {
				if (this.gui.getGame().getLevels().get(1).getObject() instanceof Key)
				((Key)this.gui.getGame().getLevels().get(1).getObject()).setX(cellX);
				((Key)this.gui.getGame().getLevels().get(1).getObject()).setY(cellY);
				}

			  resetFlags();
			  this.gui.getGame().getLevel().drawImovable(map);
			  this.gui.getGame().getLevel().drawMovable(map);
			  this.gui.getGame().getLevel().setMap(map);
			  this.gameBox.repaint();
			}
		}
		
		public void resetFlags() {
			heroButtonPressed = false;
			ogreButtonPressed = false;
			keyButtonPressed = false;
			wallButtonPressed = false;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		private class CreationEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
                int mazeSize = Integer.parseInt(textField.getText());
                
                if (mazeSize == 7 || mazeSize == 9 || mazeSize == 11) {
                	dimension = mazeSize;
                	createMap(game);
                	textField.setVisible(false);
                	initializePanel();
                	btnStartTheCreation.setVisible(false);
                	lblSize.setVisible(false);
                	frame.getContentPane().setLayout(null);
                	gameBox.setBounds(100, 100, 275, 275);
                	gameBox.setVisible(true);
                	initializeCharButtons();
                }
			}
		}
		
		private class OgreEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				ogreButtonPressed = true;
			}
		}
		
		private class HeroEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				heroButtonPressed = true;
			}
		}
		
		private class WallEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				wallButtonPressed = true;
			}
		}
		

		private class KeyEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				keyButtonPressed = true;
			}
		}

		private class FinishedEvent implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				gui.getGame().decLevel();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
}
