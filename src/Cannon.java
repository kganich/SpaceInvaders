import java.awt.*;

class Cannon {
    public final int CANNON_X = 26;
    public final int CANNON_Y = 16;
    int x, y, direction;

    public Cannon() {
        x = 10;
        y = Game.HEIGHT - CANNON_Y - 30;
    }

    public void move() {
        if (direction == Game.LEFT && x > 10) x -= 5;
        if (direction == Game.RIGHT && x < Game.WIDTH - CANNON_X - 12) x += 5;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void shot() {
        Game.ray.start(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCANNON_X() {
        return CANNON_X;
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y + CANNON_Y/2, CANNON_X, CANNON_Y /2);
        g.fillRect(x + 10, y + 2, CANNON_X - 20, CANNON_Y /2);
        g.fillRect(x + 12, y, 2, 2);
    }
}
