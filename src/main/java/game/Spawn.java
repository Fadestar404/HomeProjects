package game;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(r.nextInt(GameClass.WIDTH), r.nextInt(GameClass.HEIGHT), ID.BasicEnemy, handler));
            }

            if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(r.nextInt(GameClass.WIDTH), r.nextInt(GameClass.HEIGHT), ID.FastEnemy, handler));
            }

            if (hud.getLevel() == 4) {

                handler.addObject(new SmartEnemy(r.nextInt(GameClass.WIDTH), r.nextInt(GameClass.HEIGHT), ID.SmartEnemy, handler));
            }

            if (hud.getLevel() == 5) {

                handler.addObject(new BossEnemy((GameClass.WIDTH / 2) - 48, -120, ID.BossEnemy, handler));
            }

        }

    }
}
