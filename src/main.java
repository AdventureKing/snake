import game.state.board;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by brandon.a.snow on 12/21/2016.
 */
public class main {



    public static void main(String args[]) {
        /*Date today = new Date();*/
        //game frame
        JFrame window = new JFrame("SNAKE");
        //game board
        board gameBoard = new board();
        //put game board in the frame
        window.setContentPane(gameBoard);
        //gets rid of extra space
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setPreferredSize(new Dimension(gameBoard.WIDTH,gameBoard.HEIGHT));
/*
        window.setLocation(null);
*/
        window.setVisible(true);

        /*System.out.println(today);*/
    }


}
