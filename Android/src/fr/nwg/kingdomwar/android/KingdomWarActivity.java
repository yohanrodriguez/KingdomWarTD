package fr.nwg.kingdomwar.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import fr.nwg.kingdomwar.KingdomWarGame;

public class KingdomWarActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        initialize(new KingdomWarGame(), cfg);
    }
}