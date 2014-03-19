package fr.nwg.kingdomwar.world;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.input.InputManager;
import fr.nwg.kingdomwar.input.LibgdxInputManager;

public class KingdomWarWorld extends World {

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private InputManager inputManager;

    public KingdomWarWorld() {
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setToOrtho(false);
        shapeRenderer = new ShapeRenderer();
        inputManager = new LibgdxInputManager();
    }

    @Override
    public void process() {
        //setDelta
        super.process();
        inputManager.reset();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}
