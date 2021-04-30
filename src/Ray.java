import java.awt.*;
import java.io.File;

class Ray {
    final int X = 2;
    final int Y = 8;
    final int DY = 12;
    int x, y;
    boolean isAlive;
    void start(int x, int y) {
        if (!isAlive) {
            Canvas.playSound(new File("/Users/karina/Documents/src/SpaceInvaders1/src/sounds/shoot.wav"));
            isAlive = true;
            this.x = x + (Game.cannon.getCANNON_X() - X) / 2;
            this.y = y - Y;
        }
    }
    void fly() {
        if (isAlive) {
            y -= DY;
            isAlive = (y + DY) > 0;
        }
    }
    void disable() { isAlive = false; }
    boolean isEnable() { return isAlive; }
    int getX() { return x; }
    int getY() { return y; }
    void paint(Graphics g) {
        g.setColor(Color.white);
        if (isAlive) g.fillRect(x, y, X, Y);
    }
}
