import java.awt.*;

class Alien {
    int x, y, type, view = 0;
    int width, height = 8;
    public Alien(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        width = Game.PATTERNS_OF_ALIENS[type][view][8][0];
    }
    int getType() {
        return type;
    }

    boolean hitRay() {
        if (Game.ray.isEnable())
            if ((Game.ray.getX() >= x) && (Game.ray.getX() <= x + width * Game.POINT_SCALE))
                if (Game.ray.getY() < y + height * Game.POINT_SCALE) {
                    Game.ray.disable();
                    return true;
                }
        return false;
    }
    void nextStep(int direction) {
        view = 1 - view;
        if (direction == Game.RIGHT) x += 5;
        else if (direction == Game.LEFT) x -= 5;
        else if (direction == Game.DOWN) y += 15;
    }

    void shot() {
        Game.rays.add(x + width/2, y + height);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        for (int col = 0; col < width; col++)
            for (int row = 0; row < height; row++)
                if (Game.PATTERNS_OF_ALIENS[type][view][row][col] == 1)
                    g.fillRect(col * Game.POINT_SCALE + x, row * Game.POINT_SCALE + y, Game.POINT_SCALE, Game.POINT_SCALE);
    }

}
