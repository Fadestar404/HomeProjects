package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class BossEnemy extends GameObject {

    private Handler handler;
    private int timer = 130;
    private int timer2 = 50;

    private Random r = new Random();

    public BossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 1;
    }

    public void tick() {
        x += velX;
        y += velY;

        // if(y<=0 || y >=GameClass.HEIGHT-35)velY *=-1;
        if (x <= 0 || x >= GameClass.WIDTH - 100) {
            velX *= -1;
        }

        if (timer <= 0) {
            velY = 0;
        }
        timer--;

        if (timer <= 0) {
            timer2--;
        }

        if (timer2 <= 0) {
            if (velX == 0) {
                velX = 3;
            }

            if (velX > 0) {
                velX += 0.1f;
            }

            if (velX >= 10) {
                velX = 1;
            }

            if (velX > 0) {
                velX += 0.01f;
            }

            int spawn = r.nextInt(30);
            if (spawn == 5) {
                handler.addObject(new BulletsBossEnemy(x, y, ID.BasicEnemy, handler));
            }
        }

        //handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,99,99,0.080f,handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillOval((int) x, (int) y, 100, 100);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 100, 100) {
        };
    }

}
