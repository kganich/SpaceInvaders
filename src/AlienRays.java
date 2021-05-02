import java.awt.*;
import java.util.ArrayList;

public class AlienRays {
    ArrayList<AlienRay> rays = new ArrayList<>();

    void add(int x, int y) {
        rays.add(new AlienRay(x, y));
    }

    void fly() {
        for (AlienRay ray : rays) ray.fly();
        for (AlienRay ray : rays)
            if (ray.hitGround()) {
                rays.remove(ray);
                break;
            }
        for (AlienRay ray : rays)
            if (ray.hitCannon()) {
                Game.lives--;
                Game.cannon = new Cannon();
                Game.gameOver = Game.lives == 0;
                rays.remove(ray);
                break;
            }
    }

    int getSize() { return rays.size(); }

    void paint(Graphics g) {
        for (AlienRay ray : rays) ray.paint(g);
    }

    static class AlienRay {
        final int Y = 10;
        final int DY = 6;
        int x, y;

        AlienRay(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void fly() { y += DY; }

        boolean hitGround() { return y + Y > Game.HEIGHT - 20; }

        boolean hitCannon() {
            if (y + Y > Game.cannon.getY())
                if (x >= Game.cannon.getX() && x <= Game.cannon.getX() + Game.cannon.getCANNON_X())

                    return true;
            return false;
        }

        void paint(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(x + 2, y, 2, Y);
        }
    }
}
