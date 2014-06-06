package fr.nwg.kingdomwar.system.misc;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class PrepareProcessSystem extends VoidEntitySystem {

    private final Camera camera;

    public PrepareProcessSystem() {
        camera = KingdomWarData.getInstance().getCamera();
    }

    @Override
    protected void processSystem() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
    }
}
