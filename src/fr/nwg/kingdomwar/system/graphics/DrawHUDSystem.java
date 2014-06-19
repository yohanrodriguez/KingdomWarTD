package fr.nwg.kingdomwar.system.graphics;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.nwg.kingdomwar.world.KingdomWarData;

/**
 * Created by eptwalabha on 19/06/2014.
 */
public class DrawHUDSystem extends VoidEntitySystem {
    @Override
    protected void processSystem() {
        KingdomWarData instance = KingdomWarData.getInstance();
        SpriteBatch spriteBatch = instance.spriteBatch;
        BitmapFont font = instance.font;

        spriteBatch.begin();
        int x = 620;
        font.setScale(1);
        font.setColor(Color.RED);
        font.draw(spriteBatch, "<3 : " + instance.playerLifePoints, x, 550);
        font.setColor(Color.GREEN);
        font.draw(spriteBatch, "$$ : " + instance.playerThunes, x, 500);
        font.setColor(Color.YELLOW);
        font.draw(spriteBatch, "XP : " + instance.playerScore, x, 450);
        if(instance.timeScale == 0) {
            font.setColor(Color.RED);
            font.setScale(3);
            font.draw(spriteBatch, "GAME OVER", 50, 300);
        }
        spriteBatch.end();
    }
}
