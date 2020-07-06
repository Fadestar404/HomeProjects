package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.GameClass;
import game.GameObject;
import game.Handler;
import game.ID;
import game.Trail;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                player = handler.object.get(i);
            }
        }

    }

    public void tick() {
        x += velX;
        y += velY;

        float difX = x - player.getX();
        float difY = y - player.getY();

        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = (float) ((-2.0 / distance) * difX);
        velY = (float) ((-2.0 / distance) * difY);

        //if(y<=0 || y >=GameClass.HEIGHT-35)velY *=-1;
        //if(x<=0 || x >=GameClass.WIDTH-35)velX *=-1;
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.green, 16, 16, 0.080f, handler));

    }

    public void render(Graphics g) {

        g.setColor(Color.green);
        g.fillOval((int) x, (int) y, 20, 20);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
