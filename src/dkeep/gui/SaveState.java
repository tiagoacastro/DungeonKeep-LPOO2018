package dkeep.gui;
import dkeep.logic.*;
import javax.swing.*;
import java.awt.event.*;
import dkeep.cli.UserInterface;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SaveState extends JFrame{
    
    private Game game;
    private JTextField textField;

    public SaveState(Game game){
        this.game=game;
        getContentPane().setLayout(null);

        JLabel lblFileName = new JLabel("File Name");
        lblFileName.setBounds(112, 30, 57, 16);
        getContentPane().add(lblFileName);

        textField = new JTextField();
        textField.setBounds(83, 81, 116, 22);
        getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(110, 138, 61, 25);
        getContentPane().add(btnSave);
    }
}
