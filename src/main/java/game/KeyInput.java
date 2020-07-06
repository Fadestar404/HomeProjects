package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    boolean KeyDown[] = new boolean[4];
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;

        KeyDown[0] = false;
        KeyDown[1] = false;
        KeyDown[2] = false;
        KeyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                    KeyDown[0] = true;
                }
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    KeyDown[1] = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    KeyDown[2] = true;
                }
                if (key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                    KeyDown[3] = true;
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    tempObject.setVelY(0);
                }
                KeyDown[0] = false;
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(0);
                }
                KeyDown[1] = false;
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(0);
                }
                KeyDown[2] = false;
                if (key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(0);
                }
                KeyDown[3] = false;

                if (!KeyDown[0] && !KeyDown[3]) {
                    tempObject.setVelX(0);
                }
                if (!KeyDown[1] && !KeyDown[2]) {
                    tempObject.setVelX(0);
                }

            }
        }
    }

}
