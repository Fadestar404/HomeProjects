package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 4;
        velY = 4;
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

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.yellow, 16, 16, 0.080f, handler));

    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 20, 20);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
