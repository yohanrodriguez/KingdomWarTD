package fr.nwg.kingdomwar.non_artemis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by eptwalabha on 19/06/2014.
 */
public class LibgdxUtils {
    public static BitmapFont createFont(String fntPng, String fntFnt, String fntPath) {
        String path = fntPath == "" ? "" : fntPath + "/";
        String fntPngPath = path + fntPng;
        String fntFntPath = path + fntFnt;
        return new BitmapFont(Gdx.files.internal(fntFntPath),Gdx.files.internal(fntPngPath),false);
    }
}
