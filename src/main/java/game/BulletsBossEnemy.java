package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BulletsBossEnemy extends GameObject {

    private Handler handler;

    private Random r = new Random();

    public BulletsBossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = r.nextInt(3);
        velY = 5;
    }

    public void tick() {
        x += velX;
        y += velY;

        // if(y<=0 || y >=GameClass.HEIGHT-35)velY *=-1;
        //if(x<=0 || x >=GameClass.WIDTH-35)velX *=-1;
        if (y >= GameClass.HEIGHT) {
            handler.removeObject(this);
        }

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.yellow, 21, 21, 0.050f, handler));

    }

    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, 20, 20);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 20, 20);
    }

}
