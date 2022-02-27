package com.example.omok;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUI implements ActionListener {

    Random random = new Random();
    static JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[169];
    boolean player1_turn;

    GUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(Color.black);
        textField.setBackground(Color.white);
        textField.setFont(new Font("Int Free", Font.BOLD, 75));
        textField.setText("Gomoku");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(14,14));
        button_panel.setBackground(new Color(150,150,150));

        for(int i = 0; i < 169; i++){
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.black);
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 30));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        for(int i = 0; i < 169; i++){
            buttons[i].setBorder(new LineBorder(Color.black));
            buttons[i].setBackground(new Color(135,80,0));
            buttons[i].setOpaque(true);
            button_panel.setBackground(Color.white);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 169; i++){
            if(e.getSource() == buttons[i]){
                if(player1_turn){
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.white);
                        buttons[i].setText("O");
                        player1_turn = false;
                        textField.setText("Black Turn");
                        check();
                    }
                }
                else{
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.black);
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("White Turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn(){
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // get random num 0 or 1
        if(random.nextInt(2) == 0){
            player1_turn = true;
            textField.setText("White Turn");
        }
        else{
            player1_turn = false;
            textField.setText("Black Turn");
        }
    }

    public void check(){
    }

    public void whiteWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 169; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("Black WINS!");
    }

    public void blackWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 169; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("White WINS!");
    }
    public static void main(String[] args){
        new GUI();
    }
}