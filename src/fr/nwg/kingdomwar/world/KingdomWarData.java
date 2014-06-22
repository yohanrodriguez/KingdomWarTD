package fr.nwg.kingdomwar.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.non_artemis.Grid;
import fr.nwg.kingdomwar.non_artemis.LibgdxUtils;
import fr.nwg.kingdomwar.non_artemis.Rail;
import fr.nwg.kingdomwar.non_artemis.TowerType;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Eptwalabha
 * Date: 07/06/2014
 * Time: 00:56
 */

public class KingdomWarData {
    public int playerScore;
    public int playerLifePoints;
    public int playerMoney;

    private static KingdomWarData instance = null;

    private TextureAtlas atlas;
    private ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Grid grid;
    private Map<String, Rail> rails;
    private TowerType currentTowerType;
    public float timeScale = 1;
    public BitmapFont font;

    private KingdomWarData() {
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setToOrtho(false);
        shapeRenderer = new ShapeRenderer();
        grid = new Grid(Constants.GRID_ROWS, Constants.GRID_COLUMNS, Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT);
        rails = new HashMap<String, Rail>();
        atlas = new TextureAtlas("test.txt");
        spriteBatch = new SpriteBatch();
        font = LibgdxUtils.createFont("calibri.png", "calibri.fnt", "fonts");
        playerScore = 0;
        playerLifePoints = 200;
        playerMoney = 400;
        currentTowerType = new TowerType();
        currentTowerType.setBuildingCost(125).setFiringRate(250);
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

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Grid getGrid() {
        return grid;
    }

    public void addRail(String railName, Rail rail) {
        rails.put(railName, rail);
        grid.setTopo(rail);
    }

    public Rail getRail(String railName) {
        return (rails.containsKey(railName)) ? rails.get(railName) : null;
    }

    public Sprite getSprite(String region) {
        return atlas.createSprite(region);
    }

    public void dispose() {
        atlas.dispose();
        spriteBatch.dispose();
    }

    public boolean canAfford(int amount) {
        return this.playerMoney >= amount;
    }

    public TowerType getSelectedTowerType() {
        return currentTowerType;
    }

    public void changeCurrentTowerType(TowerType towerType) {
        currentTowerType = towerType;
    }
}