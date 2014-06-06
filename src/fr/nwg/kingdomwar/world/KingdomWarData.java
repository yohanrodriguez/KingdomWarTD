package fr.nwg.kingdomwar.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.non_artemis.Grid;
import fr.nwg.kingdomwar.non_artemis.Rail;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Eptwalabha
 * Date: 07/06/2014
 * Time: 00:56
 */

public class KingdomWarData {
    private static KingdomWarData instance = null;

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Grid grid;
    private Map<String, Rail> rails;

    private KingdomWarData() {
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setToOrtho(false);
        shapeRenderer = new ShapeRenderer();
        grid = new Grid(Constants.GRID_ROWS * 5, Constants.GRID_COLUMNS * 5, Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT);
        rails = new HashMap<String, Rail>();
    }

    public static synchronized KingdomWarData getInstance() {
        if (instance == null) {
            instance = new KingdomWarData();
        }
        return instance;
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

    public void addRail(String railName, Rail rail) {
        rails.put(railName, rail);
    }

    public Rail getRail(String railName) {
        return (rails.containsKey(railName)) ? rails.get(railName) : null;
    }
}