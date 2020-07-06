package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class GameClass extends Canvas implements Runnable {

    private static final long serialVersionUID = 2362432432L;

    public static final int WIDTH = 1240, HEIGHT = WIDTH / 12 * 9;

    public Thread thread;
    private boolean running = false;

    private Handler handler;
    private Random rand;

    private Spawn spawn;

    private HUD hud;

    private Menu menu;

    public enum STATE {
        Menu,
        Game

    };

    public STATE gameState = STATE.Menu;

    public GameClass() {

        handler = new Handler();
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "GAME", this);

        hud = new HUD();

        spawn = new Spawn(handler, hud);

        rand = new Random();

        //  if(gameState==STATE.Game){
        //   handler.addObject(new Player(WIDTH/2-32 , HEIGHT/2-32,ID.Player, handler));
        // }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS" + frames);
                    frames = 0;
                }
            }

        }
        stop();
    }

    private void tick() {
        handler.tick();

        if (gameState == STATE.Game) {
            hud.tick();

            spawn.tick();
        } else if (gameState == STATE.Menu) {

            menu.tick();

        }
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.Game) {

            hud.render(g);
        } else if (gameState == STATE.Menu) {
            menu.render(g);

        }
        g.dispose();
        bs.show();

    }

    public static float Clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }

    }

    public static void main(String args[]) {
        new GameClass();
    }

}
