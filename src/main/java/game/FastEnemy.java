package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 10;
        velY = 10;
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= GameClass.HEIGHT - 35) {
            velY *= -1;
        }
        if (x <= 0 || x >= GameClass.WIDTH - 35) {
            velX *= -1;
        }

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.yellow, 14, 14, 0.080f, handler));

    }

    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, 14, 14);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 14, 14);
    }

}
