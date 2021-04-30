import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JFrame {
    public static final int POINT_SCALE = 2;
    public static final int WIDTH = 224 * POINT_SCALE;
    public static final int HEIGHT = 256 * POINT_SCALE;
    public static  final int LEFT = 37;
    public static final int RIGHT = 39;
    public static final int DOWN = 40;
    public static final int SPACE = 32;
    public static final int DELAY = 20;
    Canvas canvas = new Canvas();
    public static Cannon cannon = new Cannon();
    public static Ray ray = new Ray();
    public static Wave wave = new Wave();
    public static AlienRays rays = new AlienRays();
    public static Random random = new Random();
    public static int score, lives = 3;
    public static boolean gameOver;

    public static void main(String[] args) {
        new Game().go();
    }

    public Game() throws HeadlessException {
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(150, 150, WIDTH + 7, HEIGHT + 26);
        setResizable(false);
        canvas.setBackground(Color.black);
        getContentPane().add(BorderLayout.CENTER, canvas);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)) cannon.setDirection(e.getKeyCode());
                if (e.getKeyCode() == SPACE) cannon.shot();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)) cannon.setDirection(0);
            }
        });
        setVisible(true);
    }

    public void go() {
        while (true) {
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            canvas.repaint();
            cannon.move();
            ray.fly();
            wave.nextStep();
            rays.fly();
            if(wave.isDestroyed()){
                wave = new Wave();
                lives++;
            }
            if(gameOver) {
                canvas.repaint();
                break;
            }
        }
    }
}
