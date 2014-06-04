package fr.nwg.kingdomwar;

import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EnemyFactory;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.input.MyInputProcessor;
import fr.nwg.kingdomwar.system.foes.AddEnemySystem;
import fr.nwg.kingdomwar.system.foes.LifeRemovalSystem;
import fr.nwg.kingdomwar.system.graphics.DisplayLifeSystem;
import fr.nwg.kingdomwar.system.graphics.DrawingShapeSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayPositionDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayRadiusDebugSystem;
import fr.nwg.kingdomwar.system.input.InputGarbageCollectorSystem;
import fr.nwg.kingdomwar.system.misc.*;
import fr.nwg.kingdomwar.system.test.DealRandomDamageEveryHalfSecondSystem;
import fr.nwg.kingdomwar.system.tower.MovingBulletSystem;
import fr.nwg.kingdomwar.system.tower.PerceptionSystem;
import fr.nwg.kingdomwar.system.tower.PlacingSystem;
import fr.nwg.kingdomwar.system.tower.ShootingSystem;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class KingdomWarGame implements ApplicationListener {
    private KingdomWarWorld world;
    private ImmutableBag<Entity> foes;

    @Override
    public void create() {
        world = new KingdomWarWorld();
        world.initialize();
        world.setManager(new GroupManager());
        foes = world.getManager(GroupManager.class).getEntities("FOES");
        world.setSystem(new PrepareProcessSystem(world), false);
        world.setSystem(new DrawingShapeSystem(world));
        world.setSystem(new DisplayLifeSystem(world));
//        addDebugSystems();
        world.setSystem(new MovingBulletSystem());
        world.setSystem(new LifeRemovalSystem());
        world.setSystem(new DealRandomDamageEveryHalfSecondSystem());
        world.setSystem(new ShootingSystem());
        world.setSystem(new TimeToLiveSystem());
        world.setSystem(new DestinationReachedSystem());
        world.setSystem(new MovingToDestinationSystem());
        world.setSystem(new PerceptionSystem());



        world.setSystem(new PlacingSystem(world));

        world.setSystem(new AddEnemySystem());

        world.setSystem(new RemoveEntityFromWorldSystem());
        world.setSystem(new InputGarbageCollectorSystem(), false);


        Entity inputEntity = EntityFactory.createInputEntity(world);
        PositionComponent cursorPosition = inputEntity.getComponent(CursorPositionComponent.class).position;

        //input
        MyInputProcessor inputProcessor = new MyInputProcessor(world.getCamera(), cursorPosition);
        inputProcessor.addTouchedUpListener(inputEntity);
        Gdx.input.setInputProcessor(inputProcessor);

        EntityFactory.createEntityPlacementShape(world, cursorPosition);

        EnemyFactory.createBasicEnemy(world);

        world.getGrid().createCellsEntity(world);
    }

    private void addDebugSystems() {
        world.setSystem(new DisplayPositionDebugSystem(world));
        world.setSystem(new DisplayRadiusDebugSystem(world));
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime() * 1000);
//        System.out.println("delta = " + world.getDelta());
        world.getSystem(PrepareProcessSystem.class).process();
        world.process();
        world.getSystem(InputGarbageCollectorSystem.class).process();

        if (System.currentTimeMillis() % 100 == 0)
            System.out.println("fps = " + Gdx.graphics.getFramesPerSecond());
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
