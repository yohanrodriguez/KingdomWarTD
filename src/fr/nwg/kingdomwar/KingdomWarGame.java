package fr.nwg.kingdomwar;

import com.artemis.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.factory.EnemyFactory;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.input.MyInputProcessor;
import fr.nwg.kingdomwar.system.foes.AddEnemySystem;
import fr.nwg.kingdomwar.system.graphics.DrawingShapeSystem;
import fr.nwg.kingdomwar.system.input.InputGarbageCollectorSystem;
import fr.nwg.kingdomwar.system.misc.PrepareProcessSystem;
import fr.nwg.kingdomwar.system.misc.RemoveEntityFromWorldSystem;
import fr.nwg.kingdomwar.system.misc.TimeToLiveSystem;
import fr.nwg.kingdomwar.system.tower.MovingBulletSystem;
import fr.nwg.kingdomwar.system.tower.PlacingSystem;
import fr.nwg.kingdomwar.system.tower.ShootingSystem;
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

        world.setSystem(new PlacingSystem());

        world.setSystem(new AddEnemySystem());

        world.setSystem(new RemoveEntityFromWorldSystem());
        world.setSystem(new InputGarbageCollectorSystem(), false);


        Entity inputEntity = EntityFactory.createInputEntity(world);
        CursorPositionComponent cursorPosition = inputEntity.getComponent(CursorPositionComponent.class);

        //input
        Gdx.input.setInputProcessor(new MyInputProcessor(world.getCamera(), inputEntity));

        //Cells
        EntityFactory.createEntityPlacementShape(world, cursorPosition.position);
        Entity grid = EntityFactory.createGrid(world, Constants.GRID_ROWS, Constants.GRID_COLUMNS);
        EntityFactory.createCellsFromGrid(world, grid);

        EnemyFactory.createBasicEnemy(world);
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime() * 1000);
        world.getSystem(PrepareProcessSystem.class).process();
        world.process();
        world.getSystem(InputGarbageCollectorSystem.class).process();
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
