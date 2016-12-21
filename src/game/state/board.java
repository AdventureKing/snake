package game.state;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by brandon.a.snow on 12/21/2016.
 */
public class board extends JPanel implements Runnable, KeyListener {


    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public board(){
       setPreferredSize(new Dimension(WIDTH, HEIGHT));
       setFocusable(true);
       requestFocus();
       addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

    }
}
