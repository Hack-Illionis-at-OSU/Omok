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
    GameBoard b = new GameBoard(13);
    AI ai = new AI(b);

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

        button_panel.setLayout(new GridLayout(13,13));
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
                if(b.getPlayerTurn()){
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.white);
                        buttons[i].setText("O");
                        textField.setText("Black Turn");
                        check();
                        Stone temp = b.fillBoard(buttons[i], i);
                        ai.setResultBoard(temp);
                        ai.calculateBoard();
                        ai.getPossibilities();

                        int tempx = i % 13;
                        int tempy = i / 13;
                        Pair pair = new Pair(tempx, tempy);
                        AINode calminMax = new AINode(ai, 0, pair);
                        
                        ArrayList<AINode> path = SearchTree.bfs(calminMax);
                        Pair aichoice = path.get(0).getLastCord();
                        int aiPosition = aichoice.x + aichoice.y * 13;

                        if (buttons[aiPosition].getText() == "") {
                            buttons[aiPosition].setForeground(Color.black);
                            buttons[aiPosition].setText("O");
                            textField.setText("White Turn");
                            check();
                            temp = b.fillBoard(buttons[aiPosition], aiPosition);
                            ai.setResultBoard(temp);

                            // ?????? ???????????? ????????? ?????? ????????????????????? ???????????? WinCon.Java ??????
                            // ?????? ?????? x, y??? ????????? GUI?????? ???????????? ????????????????????? ????????? ????????? ????????????
                            Pair tempPair= new Pair(aiPosition % 13, aiPosition / 13);
                            if ( WinCon.isWinCon(new AINode(ai, 0, tempPair))) {
                                break;
                            }
                        }
                    }

                    
//                else{ // ??? ?????? ???????????? ????????? ???????????????
//                    ai.getPossibilities();
//                    ArrayList<Pair> aiChoice = AI.getMinMaxList();
//                    int aiPosition = aiChoice.get(0).x + aiChoice.get(0).y * 13;
//                    if (buttons[aiPosition].getText() == "") {
//                        buttons[aiPosition].setForeground(Color.black);
//                        buttons[aiPosition].setText("O");
//                        textField.setText("White Turn");
//                        check();
//                        Stone temp = b.fillBoard(buttons[aiPosition], aiPosition);
//                        ai.setResultBoard(temp);
//
//                        // ?????? ???????????? ????????? ?????? ????????????????????? ???????????? WinCon.Java ??????
//                        // ?????? ?????? x, y??? ????????? GUI?????? ???????????? ????????????????????? ????????? ????????? ????????????
//                        Pair tempPair= new Pair(aiPosition % 13, aiPosition / 13);
//                        if ( WinCon.isWinCon(new AINode(ai, 0, tempPair))) {
//                            break;
//
//                        }
//                    }
                }
                b.displayBoard();
                AI.displayResult();
            }
        }

    }

    public void check(){

    }

    public static void main(String[] args){
        new GUI();
    }
}
