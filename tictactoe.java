import java.awt.*;
import javax.swing.*;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class tictactoe extends JPanel
        implements ActionListener {

    JButton button[] = new JButton[9];   //declaring needed buttons,arrays and JFrames
    JButton restart;   //button to restart
    int button_status[] = new int[9];     //declare an array to keep button details
    Boolean condition = false;   //if the condition is false it's a cross else a naught
    static int win = 0;    //variable to state a player has won or not
    static JFrame frame = new JFrame("Tic tac toe");   //frame contain the game buttons
    static JFrame frame_result_cross = new JFrame("Game over!");    //this frame appear when crosses win
    static JFrame frame_result_naught = new JFrame("Game over!");   //this frame appear when naughts win

    public tictactoe() {    //game method
        for (int count = 0; count < 9; count++) {
            button[count] = new JButton();    //adding buttons
            button[count].setPreferredSize(new Dimension(80, 80));
            add(button[count], BorderLayout.AFTER_LINE_ENDS);
            button[count].addActionListener(this);    //adding each button to actionlistner
        }

        restart = new JButton("RESTART GAME");   //add the restart button
        restart.setPreferredSize(new Dimension(250, 80));
        add(restart, BorderLayout.AFTER_LINE_ENDS);
        restart.addActionListener(this);
    }



    public void actionPerformed(ActionEvent e) {   //actions hapenning in game
        if (e.getSource() == restart) {  //if player restarts game resets
            condition = false;
            win = 0;
            frame_result_cross.setVisible(false);
            frame_result_naught.setVisible(false);
            for (int counter1 = 0; counter1 < 9; counter1++) {
                button[counter1].setText("");
                button_status[counter1] = 0;
            }
        } else if (win == 0) {   //if not restart getsource and mark buttons('O' foe naught turns and 'X' for crosses turns)
            int counter;
            for (counter = 0; counter < 9; counter++) {    //select the correct button among 9 buttons
                if (e.getSource() == button[counter])
                    break;
            }
            if (button_status[counter] == 1 || button_status[counter] == 2) {   //mark and give the relevant state to buttons according it's a cross or a naught
            } else {
                if (condition == false) {
                    button[counter].setText("X");
                    button_status[counter] = 1;
                } else {
                    button[counter].setText("O");
                    button_status[counter] = 2;
                }
                condition = !condition;    //inverting the condition to change turns of crosses and naughts
            }


            //check the wining positions
            if (win == 0) {     //row win
                for (int row = 0; row < 7; row += 3) {
                    if (button_status[row] == button_status[row + 1] && button_status[row + 1] == button_status[row + 2] && button_status[row] == 1)
                        win = 1;
                    else if (button_status[row] == button_status[row + 1] && button_status[row + 1] == button_status[row + 2] && button_status[row] == 2)
                        win = 2;
                }
            }

            if (win == 0) {   //coloumn win
                for (int coloumn = 0; coloumn < 3; coloumn++) {
                    if (button_status[coloumn] == button_status[coloumn + 3] && button_status[coloumn + 3] == button_status[coloumn + 6] && button_status[coloumn] == 1)
                        win = 1;
                    else if (button_status[coloumn] == button_status[coloumn + 3] && button_status[coloumn + 3] == button_status[coloumn + 6] && button_status[coloumn] == 2)
                        win = 2;
                }
            }

            if (win == 0) {    //diagonal win
                if (button_status[0] == button_status[4] && button_status[4] == button_status[8] && button_status[0] == 1)
                    win = 1;
                else if (button_status[0] == button_status[4] && button_status[4] == button_status[8] && button_status[0] == 2)
                    win = 2;
                else if (button_status[2] == button_status[4] && button_status[4] == button_status[6] && button_status[2] == 1)
                    win = 1;
                else if (button_status[2] == button_status[4] && button_status[4] == button_status[6] && button_status[2] == 2)
                    win = 2;
            }


            if (win == 1) {    //if a win exist show in the windows
                frame_result_cross.setVisible(true);
            } else if (win == 2) {
                frame_result_naught.setVisible(true);
            }

        }


    }


    public static void main(String[] args) {
        //Create and set up the window.


        frame.setPreferredSize(new Dimension(280, 385));    //the properties of game window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new tictactoe();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

        frame_result_cross.setPreferredSize(new Dimension(250, 100));   //the properties of the cross win window
        JLabel label_cross=new JLabel("Crosses win!");
        label_cross.setFont(new Font("Serif",Font.BOLD,20));
        label_cross.setForeground(Color.red);
        label_cross.setHorizontalAlignment(JLabel.CENTER);
        frame_result_cross.add(label_cross);
        frame_result_cross.pack();

        frame_result_naught.setPreferredSize(new Dimension(250, 100));   //the properties of the naughts win window
        JLabel label_naught=new JLabel("Naughts win!");
        label_naught.setFont(new Font("Serif",Font.BOLD,20));
        label_naught.setForeground(Color.red);
        label_naught.setHorizontalAlignment(JLabel.CENTER);
        frame_result_naught.add(label_naught);
        frame_result_naught.pack();

    }
}
/*note: You can play the game without closing the game window by clicking the restart button again when cross or naughts win once*/