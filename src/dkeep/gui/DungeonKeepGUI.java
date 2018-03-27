package dkeep.gui;

import dkeep.logic.*;
import dkeep.logic.Game.gameState;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import dkeep.cli.UserInterface;

//TODO: meter Grid Bag Layout (preciso recomeçar tudo), dizer que o heroi se moveu so se se tiver movido mesmo(mover contra parede tambem esta a dar print)
public class DungeonKeepGUI{
	
	private JFrame frame;
	private JTextField ogres;
	private JComboBox<String> guardType;
	private Game game;
	private GamePanel gameBox;
	private JButton upButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton downButton;
	private JLabel status;
	private boolean gamePlaying;
	
	
	//main function
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DungeonKeepGUI window = new DungeonKeepGUI();
			}
		});
	}
	
	
	//GUI creator
	
	public DungeonKeepGUI() {
		gamePlaying = false;

		//frame
		frame = new JFrame("Dungeon Keep");
		frame.setBounds(100, 100, 640, 480);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		
		//ogre input
		JLabel ogreLabel = new JLabel();
		ogreLabel.setText("Number of Ogres");
		ogreLabel.setBounds(10, 15, 100, 20);
		frame.add(ogreLabel);
		
		ogres = new JTextField(10);
		ogres.setBounds(135, 14, 40, 20);
		frame.add(ogres);
		
		
		//guard input
		JLabel guardLabel = new JLabel();
		guardLabel.setText("Guard personality");
		guardLabel.setBounds(10, 45, 100, 20);
		frame.add(guardLabel);
		
		guardType = new JComboBox();
		guardType.setBounds(135, 45, 100, 20);
		guardType.addItem("Rookie");
		guardType.addItem("Drunken");
		guardType.addItem("Suspicious");
		frame.add(guardType);
		
		
		//start game button
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new startGameEvent());
		newGameButton.setBounds(460, 80, 100, 25);
		frame.add(newGameButton);
		
		
		//end game button
		JButton endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new endGameEvent());
		endGameButton.setBounds(460, 335, 100, 25);
		frame.add(endGameButton);

		//end game button
		JButton editGameMap = new JButton("Edit Game");
		editGameMap.addActionListener(new editGameMapEvent());
		editGameMap.setBounds(460, 335, 100, 25);
		frame.add(editGameMap);
		
		
		//game box
		gameBox = new GamePanel(this);
		gameBox.setBounds(10, 80, 360, 280);
		gameBox.setVisible(false);
		gameBox.setFocusable(false);
		gameBox.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						movementHandler(UserInterface.Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						movementHandler(UserInterface.Direction.RIGHT);
						break;
					case KeyEvent.VK_UP:
						movementHandler(UserInterface.Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
						movementHandler(UserInterface.Direction.DOWN);
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		frame.add(gameBox);
		
		
		//hero move buttons
		upButton = new JButton("Up");
		upButton.addActionListener(new moveUpEvent());
		upButton.setBounds(472, 168, 75, 25);
		upButton.setEnabled(false);
		frame.add(upButton);
		
		leftButton = new JButton("Left");
		leftButton.addActionListener(new moveLeftEvent());
		leftButton.setBounds(417, 208, 75, 25);
		leftButton.setEnabled(false);
		frame.add(leftButton);
		
		rightButton = new JButton("Right");
		rightButton.addActionListener(new moveRightEvent());
		rightButton.setBounds(518, 208, 75, 25);
		rightButton.setEnabled(false);
		frame.add(rightButton);
		
		downButton = new JButton("Down");
		downButton.addActionListener(new moveDownEvent());
		downButton.setBounds(472, 248, 75, 25);
		downButton.setEnabled(false);
		frame.add(downButton);


		//game status
		status = new JLabel("You can start a new game");
		status.setBounds(10, 380, 400, 20);
		frame.add(status);
	}
	
	
	//movement button handler
	
	public void movementHandler(UserInterface.Direction way){
		game.userMove(way);
		gameBox.repaint();
		//gameBox.setText(game.getLevel().getMapString());
		switch(game.getLevelState()) {
			case WIN:
				if(game.getCurrentLevel() == game.getLevels().size()) {
					status.setText("You won the game! Congrats ");
					game.setState(gameState.WIN);
					upButton.setEnabled(false);
					leftButton.setEnabled(false);
					rightButton.setEnabled(false);
					downButton.setEnabled(false);
					gameBox.setFocusable(false);
				}else{
					game.incLevel();
					game.getLevel().draw();
				    //gameBox.setText(game.getLevel().getMapString());
				}
				break;
			case LOSE:
				status.setText("The villain has restrained you, you LOST ! :( ");
				game.setState(gameState.LOSE);
				upButton.setEnabled(false);
				leftButton.setEnabled(false);
				rightButton.setEnabled(false);
				downButton.setEnabled(false);
				gameBox.setFocusable(false);
				break;
			case NONE:
				switch (way) {
                	case UP:
                		status.setText("The hero was moved Up");
                		break;
                	case LEFT:
                		status.setText("The hero was moved Left");
                		break;
                	case RIGHT:
                		status.setText("The hero was moved Right");
                		break;
                	case DOWN:
                		status.setText("The hero was moved Down");
                		break;
				}	
				break;
		}
	}
	

	//button events
	
	private class startGameEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			
			game = new Game();
			gamePlaying = true;
			game.loadLevel1(guardType.getSelectedItem().toString());

			if (ogres.getText().equals("1") || ogres.getText().equals("2") || ogres.getText().equals("3") || ogres.getText().equals("4") || ogres.getText().equals("5")) {
		        game.loadLevel2(Integer.parseInt(ogres.getText()));
		        game.getLevel().draw();
		        //gameBox.setText(game.getLevel().getMapString());
				gameBox.setVisible(true);
				upButton.setEnabled(true);
				leftButton.setEnabled(true);
				rightButton.setEnabled(true);
				downButton.setEnabled(true);
				status.setText("You can play now.");
				gameBox.repaint();
				gameBox.setFocusable(true);
				gameBox.requestFocusInWindow();
			} else {
				JOptionPane.showMessageDialog(frame, "You have to insert a positive number of 5 or less!");
			}
		}
	}
	
	private class endGameEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			System.exit(0);
		}
	}

	private class editGameMapEvent implements ActionListener{

		public void actionPerformed(ActionEvent arg) {

		}
	}
	
	private class moveUpEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			movementHandler(UserInterface.Direction.UP);
			gameBox.requestFocusInWindow();
		}
	}
	
	private class moveLeftEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			movementHandler(UserInterface.Direction.LEFT);
			gameBox.requestFocusInWindow();
		}
	}
	
	private class moveRightEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			movementHandler(UserInterface.Direction.RIGHT);
			gameBox.requestFocusInWindow();
		}
	}
	
	private class moveDownEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			movementHandler(UserInterface.Direction.DOWN);
			gameBox.requestFocusInWindow();
		}
	}


	//extra functions

	public boolean checkGameStart(){
		return gamePlaying;
	}

	public Game getGame(){
		return game;
	}
}
