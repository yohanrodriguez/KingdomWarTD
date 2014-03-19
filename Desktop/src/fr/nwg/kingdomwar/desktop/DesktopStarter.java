package fr.nwg.kingdomwar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.KingdomWarGame;

public class DesktopStarter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Kingdom War";
        cfg.useGL20 = true;
        cfg.width = (int) Constants.WORLD_WIDTH;
        cfg.height = (int) Constants.WORLD_HEIGHT;
        new LwjglApplication(new KingdomWarGame(), cfg);
    }
}
