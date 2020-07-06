package game;

import static game.GameClass.HEIGHT;
import game.GameClass.STATE;
import static game.GameClass.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private GameClass gameClass;
    private Random r;
    private Handler handler;

    public Menu(GameClass gameClass, Handler handler) {
        this.gameClass = gameClass;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if (mouseOver(mx, my, GameClass.WIDTH / 2 - 200, 200, 400, 100)) {
            gameClass.gameState = STATE.Game;
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
        }

        if (mouseOver(mx, my, GameClass.WIDTH / 2 - 200, 420, 400, 100)) {

            if (gameClass.gameState == STATE.Menu) {

                System.exit(1);
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }

    public void tick() {

    }

    public void render(Graphics g) {

        Font f = new Font("arial", 1, 50);

        g.setFont(f);

        g.setColor(Color.yellow);
        g.drawRect(GameClass.WIDTH / 2 - 200, 200, 400, 100);
        g.drawString("Play", GameClass.WIDTH / 2 - 50, 260);

      

        g.setColor(Color.yellow);
        g.drawRect(GameClass.WIDTH / 2 - 200, 420, 400, 100);
        g.drawString("Exit", GameClass.WIDTH / 2 - 50, 485);
    }

}
