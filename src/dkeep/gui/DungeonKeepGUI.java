package dkeep.gui;

import dkeep.logic.*;
import dkeep.logic.Game.gameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import dkeep.cli.UserInterface;

public class DungeonKeepGUI extends JPanel{
	
	private JFrame frame;
	private Game game;
	private GamePanel gameBox;
	private JButton upButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton downButton;
	private JButton saveGameButton;
	private JButton startGameButton;
	private JLabel status;
	private boolean gamePlaying;

	DungeonKeepGUI(Game game) {
		this.game = game;
		gamePlaying = false;

		initializeFrame();
		initializePanel();
		initializeStartEndButtons();
		initializeButtons();
		initializeStatus();

		status.setText("You can start the game when you want to.");
	}

	private void initializeStatus() {
		//game status
		status = new JLabel("You can start a new game");
		status.setBounds(50, 450, 400, 20);
		status.setForeground(Color.white);
		frame.add(status);
	}

	private void initializeStartEndButtons() {
		//start game button
		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new startGameEvent());
		startGameButton.setBounds(460, 50, 100, 25);
		startGameButton.setBackground(Color.white);
		frame.add(startGameButton);

		//save game button
		saveGameButton = new JButton("Save Game");
		saveGameButton.addActionListener(new saveGameEvent());
		saveGameButton.setBounds(460, 410, 100, 25);
		saveGameButton.setEnabled(false);
		saveGameButton.setBackground(Color.white);
		frame.add(saveGameButton);

		//end game button
		JButton endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new endGameEvent());
		endGameButton.setBounds(460, 445, 100, 25);
		endGameButton.setBackground(Color.white);
		frame.add(endGameButton);
	}

	private void initializeFrame() {
		//frame
		frame = new JFrame("Dungeon Balls");
		frame.setBounds(100, 100, 640, 530);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(32,32,32));
	}

	private void initializeButtons() {
		upButton = new JButton("Up");
		upButton.addActionListener(new moveUpEvent());
		upButton.setBounds(472, 188, 75, 25);
		upButton.setBackground(Color.white);
		upButton.setEnabled(false);
		frame.add(upButton);

		leftButton = new JButton("Left");
		leftButton.addActionListener(new moveLeftEvent());
		leftButton.setBounds(417, 228, 75, 25);
		leftButton.setBackground(Color.white);
		leftButton.setEnabled(false);
		frame.add(leftButton);

		rightButton = new JButton("Right");
		rightButton.addActionListener(new moveRightEvent());
		rightButton.setBounds(518, 228, 75, 25);
		rightButton.setEnabled(false);
		rightButton.setBackground(Color.white);
		frame.add(rightButton);

		downButton = new JButton("Down");
		downButton.addActionListener(new moveDownEvent());
		downButton.setBounds(472, 268, 75, 25);
		downButton.setBackground(Color.white);
		downButton.setEnabled(false);
		frame.add(downButton);
	}

	private void initializePanel() {

		createPanel();
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
			public void keyReleased(KeyEvent e) { }
			@Override
			public void keyTyped(KeyEvent e) { }
		});
		gameBox.setBackground(new Color(32,32,32));
		frame.add(gameBox);
	}

	private void createPanel() {
		//game box
		gameBox = new GamePanel(game);
		gameBox.setBounds(50, 50, 360, 400);
		gameBox.setVisible(false);
		gameBox.setFocusable(false);
	}

	private void stateHandler(gameState gameState) {

		game.setState(gameState);
		upButton.setEnabled(false);
		leftButton.setEnabled(false);
		rightButton.setEnabled(false);
		downButton.setEnabled(false);
		gameBox.setFocusable(false);
		saveGameButton.setEnabled(false);
	}

	private void movementHandler(UserInterface.Direction way){
		game.userMove(way);
		gameBox.repaint();
		switch(game.getLevelState()) {
			case WIN:
				if(game.getCurrentLevel() == game.getLevels().size()) {
					status.setText("You won the game! Congratulations.");
					stateHandler(gameState.WIN);
				}else{
					game.incLevel();
					game.getLevel().draw();
				}
				break;
			case LOSE:
				status.setText("The villain has restrained you. You Lost.");
				stateHandler(gameState.LOSE);
				break;
			case NONE:
				if(game.getLevel().moved())
					printWayMove(way);
				break;
		}
	}

	private void printWayMove(UserInterface.Direction way) {
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
	}
	
	private class startGameEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			gamePlaying = true;
			game.getLevel().draw();
			gameBox.setVisible(true);
			upButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);
			downButton.setEnabled(true);
			saveGameButton.setEnabled(true);
			startGameButton.setText("Restart");
			status.setText("You can play now.");
			gameBox.repaint();
			gameBox.setFocusable(true);
			gameBox.requestFocusInWindow();
			startGameButton.addActionListener(new restartGameEvent());
			startGameButton.removeActionListener(this);
		}
	}

	private class restartGameEvent implements ActionListener{

		public void actionPerformed(ActionEvent arg) {
			gamePlaying = true;
			game.restart();
			game.getLevel().draw();
			upButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);
			downButton.setEnabled(true);
			saveGameButton.setEnabled(true);
			status.setText("You can try again now.");
			gameBox.repaint();
			gameBox.setFocusable(true);
			gameBox.requestFocusInWindow();
		}
	}

	private class endGameEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent arg) {
			frame.setVisible(false);
		}
	}

	private class saveGameEvent implements ActionListener{

		public void actionPerformed(ActionEvent arg) {
			String file = JOptionPane.showInputDialog(null,
					"Enter file name:");
			UserInterface.saveState(file, game);
			gameBox.requestFocusInWindow();
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
	
	private class moveDownEvent implements ActionListener {

		public void actionPerformed(ActionEvent arg) {
			movementHandler(UserInterface.Direction.DOWN);
			gameBox.requestFocusInWindow();
		}
	}

	public JButton getUpButton() {
		return upButton;
	}

	public JLabel getStatus() {
		return status;
	}

	public JButton getLeftButton() {

		return leftButton;
	}

	public JButton getRightButton() {
		return rightButton;
	}

	public JButton getDownButton() {
		return downButton;
	}

	public GamePanel getGameBox() {
		return gameBox;
	}

	public Game getGame(){
		return game;
	}

	public JFrame getFrame() {
		return frame;
	}
}
