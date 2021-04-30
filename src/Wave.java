import java.awt.*;
import java.io.File;
import java.util.ArrayList;

class Wave {
    private final int[][] PATTERN = {
            {2,2,2,2,2,2,2,2,2,2,2}, {2,2,2,2,2,2,2,2,2,2,2}, {1,1,1,1,1,1,1,1,1,1,1}, {0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0}};
    volatile ArrayList<Alien> wave = new ArrayList<>();
    int countFrames = 0;
    int direction = Game.RIGHT;
    boolean stepDown = false;
    int startX = 50;
    int startY = 60;

    public Wave() {
        for (int y = 0; y < PATTERN.length; y++)
            for (int x = 0; x < PATTERN[y].length; x++)
                wave.add(new Alien(startX + x * Game.POINT_SCALE * 16 + PATTERN[y][x] * Game.POINT_SCALE, startY + y * Game.POINT_SCALE * 16, PATTERN[y][x]));
    }

    public void nextStep() {
        if (countFrames == Game.DELAY) {
            if ((startX == 10) || (startX == 17 * 5 + 10)) {
                if (!stepDown) {
                    direction = Game.DOWN;
                } else {
                    direction = (startX == 10)? Game.RIGHT : Game.LEFT;
                    stepDown = false;
                }
            }
            for (Alien alien : wave) {
                alien.nextStep(direction);
                if (Game.random.nextInt(10) == 9)
                    if (Game.rays.getSize() < 2)
                        alien.shot();
            }
            if (direction == Game.DOWN) {
                startY += 15;
                stepDown = true;
            } else {
                startX += (direction == Game.RIGHT)? 5 : -5;
            }
            countFrames = 0;
        } else { countFrames++; }
        for (Alien alien : wave)
            if (alien.hitRay()) {
                Game.score += (alien.getType() + 1) * 10;
                Canvas.playSound(new File("/Users/karina/Documents/src/SpaceInvaders1/src/sounds/invaderkilled.wav"));
                wave.remove(alien);
                break;
            }
    }

    boolean isDestroyed() { return wave.size() == 0; }

    public void paint(Graphics g) {
        for (Alien alien : wave) alien.paint(g);
    }
}
