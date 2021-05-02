import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class Canvas extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintText(g);
        paintNumber(g, Game.score, 110, 20);
        paintNumber(g, Game.lives, 390, 20);
        if (!Game.gameOver) {
            Game.cannon.paint(g);
            Game.ray.paint(g);
            Game.wave.paint(g);
            Game.rays.paint(g);
        } else playSound(new File("/Users/karina/Documents/src/SpaceInvaders1/src/sounds/explosion.wav"));
    }

    private void paintText(Graphics g) {
        final int[][] SCORE = {
                {0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0},
                {0,1,1,1,1,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,1,1,1,1,1,0},
                {0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0},
                {1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,1,1,1,1,0,0,1,0,0,0,0,1,0,1,1,1,1,1,1}
        };
        final int[][] LIVES = {
                {1,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,1,0,0,1,1,1,1,1},
                {1,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0},
                {1,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,0,0,1,1,1,1,0},
                {1,0,0,0,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,0}
        };
        final int[][] GAME_OVER = {
                {0,1,1,1,1,0,0,1,1,1,0,0,1,0,0,0,1,0,0,1,1,1,1,0,0,0,1,1,1,0,0,1,0,0,0,1,0,0,1,1,1,1,0,1,1,1,1,0},
                {1,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
                {1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,1,1,1,1,0},
                {1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0},
                {0,1,1,1,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,1,1,1,0,0,0,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,1},
        };
        g.setColor(Color.white);
        for (int y = 0; y < SCORE.length; y++) {
            for (int x = 0; x < SCORE[y].length; x++)
                if (SCORE[y][x] == 1) g.fillRect(x * Game.POINT_SCALE + 30, y * Game.POINT_SCALE + 20, Game.POINT_SCALE, Game.POINT_SCALE);
            for (int i = 0; i < LIVES[y].length; i++)
                if (LIVES[y][i] == 1) g.fillRect(i * Game.POINT_SCALE + 320, y * Game.POINT_SCALE + 20, Game.POINT_SCALE, Game.POINT_SCALE);
        }
        if (Game.gameOver)
            for (int y = 0; y < GAME_OVER.length; y++)
                for (int x = 0; x < GAME_OVER[y].length; x++)
                    if (GAME_OVER[y][x] == 1) g.fillRect(x * Game.POINT_SCALE + 170, y * Game.POINT_SCALE + 250, Game.POINT_SCALE, Game.POINT_SCALE);
        g.setColor(Color.green);
    }

    private void paintNumber(Graphics g, int number, int x, int y) {
        final int[][][] NUMBERS = {
                {{0,1,1,1,1,0}, {1,0,0,0,0,1}, {1,0,0,0,0,1}, {1,0,0,0,0,1}, {0,1,1,1,1,0}},
                {{0,0,0,0,1,1}, {0,0,0,0,0,1}, {0,0,0,0,0,1}, {0,0,0,0,0,1}, {0,0,0,0,0,1}},
                {{1,1,1,1,1,0}, {0,0,0,0,0,1}, {0,1,1,1,1,0}, {1,0,0,0,0,0}, {0,1,1,1,1,1}},
                {{1,1,1,1,1,0}, {0,0,0,0,0,1}, {1,1,1,1,1,1}, {0,0,0,0,0,1}, {1,1,1,1,1,0}},
                {{1,0,0,0,0,1}, {1,0,0,0,0,1}, {0,1,1,1,1,0}, {0,0,0,0,0,1}, {0,0,0,0,0,1}},
                {{1,1,1,1,1,1}, {1,0,0,0,0,0}, {0,1,1,1,1,0}, {0,0,0,0,0,1}, {1,1,1,1,1,0}},
                {{0,1,1,1,1,1}, {1,0,0,0,0,0}, {1,1,1,1,1,0}, {1,0,0,0,0,1}, {0,1,1,1,1,0}},
                {{1,1,1,1,1,0}, {0,0,0,0,0,1}, {0,0,0,0,0,1}, {0,0,0,0,0,1}, {0,0,0,0,0,1}},
                {{0,1,1,1,1,0}, {1,0,0,0,0,1}, {0,1,1,1,1,0}, {1,0,0,0,0,1}, {0,1,1,1,1,0}},
                {{0,1,1,1,1,0}, {1,0,0,0,0,1}, {0,1,1,1,1,1}, {0,0,0,0,0,1}, {1,1,1,1,1,0}}
        };
        String numStr = Integer.toString(number);
        g.setColor(Color.green);
        for (int p = 0; p < numStr.length(); p++) {
            int n = (int) numStr.charAt(p) - 48;
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 6; j++)
                    if (NUMBERS[n][i][j] == 1) g.fillRect(x + j * Game.POINT_SCALE + p * 14, y + i * Game.POINT_SCALE, Game.POINT_SCALE, Game.POINT_SCALE);
        }
    }

    public static void playSound(File file) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
