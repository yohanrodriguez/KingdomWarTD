package fr.nwg.kingdomwar;

import com.artemis.Entity;
import com.artemis.EntityManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.listener.InputListener;
import fr.nwg.kingdomwar.system.*;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class KingdomWarGame implements ApplicationListener {
    private KingdomWarWorld world;

    @Override
    public void create() {
        world = new KingdomWarWorld();
        world.initialize();
        world.setSystem(new PrepareProcessSystem(world), false);
        world.setSystem(new DrawingShapeSystem(world));
        world.setSystem(new MovingBulletSystem());
        world.setSystem(new ShootingSystem());
        world.setSystem(new TimeToLiveSystem());

        world.setSystem(new RemoveEntityFromWorldSystem());

        PlacingSystem placingSystem = new PlacingSystem(world);
        world.setSystem(placingSystem, false);
        AimingUpdateSystem aimingUpdateSystem = new AimingUpdateSystem(world);
        world.setSystem(aimingUpdateSystem, false);
        UpdatePositionFromCursorPosition updatePositionFromCursorPosition = new UpdatePositionFromCursorPosition(world);
        world.setSystem(updatePositionFromCursorPosition, false);

        InputListener inputListener = new InputListener();
        Gdx.input.setInputProcessor(inputListener);

        inputListener.register(updatePositionFromCursorPosition);
        inputListener.register(aimingUpdateSystem);
        inputListener.register(placingSystem);

        Entity cursorEntity = EntityFactory.createCursorEntity(world);
        PositionComponent cursorPosition = cursorEntity.getComponent(PositionComponent.class);

        EntityFactory.createEntityPlacementShape(world, cursorPosition);
        Entity grid = EntityFactory.createGrid(world, Constants.GRID_ROWS, Constants.GRID_COLUMNS);
        EntityFactory.createCellsFromGrid(world, grid);
        //ItemMenuFactory.createTowerMenuButton(world);

    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime() * 1000);
        world.getSystem(PrepareProcessSystem.class).process();
        world.process();
        //System.out.println("nbr d'entités = " + world.getManager(EntityManager.class).getActiveEntityCount());
    }

    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
