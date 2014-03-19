package fr.nwg.kingdomwar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.nwg.kingdomwar.KingdomWarGame;

public class DesktopStarter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Kingdom War";
        cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 600;
        new LwjglApplication(new KingdomWarGame(), cfg);
    }
}
