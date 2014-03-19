package fr.nwg.kingdomwar.android;

import android.app.Activity;
import android.os.Bundle;

public class KingdomWarActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
