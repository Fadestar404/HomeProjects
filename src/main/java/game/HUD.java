package game;

import game.GameClass.STATE;
import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    private Menu menu;
    
    private GameClass gameClass;

    public static float HEALTH = 100;

    private float GreenValue = 255f;

    private int level = 1;

    private int score = 0;

    public void tick() {

        HEALTH = GameClass.Clamp(HEALTH, 0, 100);

        GreenValue = GameClass.Clamp(GreenValue, 0, 255);

        GreenValue = (HEALTH * 2);

        score++;
        
        
        if(HEALTH==0){
            
           System.exit(1);
              
           
            }

    }

    public void render(Graphics g) {

        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);

        g.setColor(new Color(75, (int) GreenValue, 0));
        g.fillRect(15, 15, (int) HEALTH * 2, 32);

        g.setColor(Color.green);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score : " + score, 15, 65);
        g.drawString("Level : " + level, 15, 85);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
