package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {

    Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

    }

    public Rectangle getBounds() {

        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = GameClass.Clamp((int) x, 0, GameClass.WIDTH - 48);
        y = GameClass.Clamp((int) y, 0, GameClass.HEIGHT - 72);

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.red, 32, 32, 0.050f, handler));

        collision();
    }

    public void collision() {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 1;
                }
            }

        }

    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.green);
        g2d.draw(getBounds());

        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 32, 32);

    }

}
