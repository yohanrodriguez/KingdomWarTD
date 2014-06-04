package fr.nwg.kingdomwar.world;

import com.artemis.World;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.JMontemmerde.Grid;

public class KingdomWarWorld extends World {

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Grid grid;

    public KingdomWarWorld() {
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setToOrtho(false);
        shapeRenderer = new ShapeRenderer();
        grid = new Grid(Constants.GRID_ROWS, Constants.GRID_COLUMNS);
    }

    @Override
    public void process() {
        //setDelta
        super.process();
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

    public Grid getGrid() {
        return grid;
    }
}
