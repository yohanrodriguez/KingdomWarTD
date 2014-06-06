package fr.nwg.kingdomwar;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import fr.nwg.kingdomwar.factory.RailFactory;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.system.graphics.DrawingGridSystem;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EnemyFactory;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.input.MyInputProcessor;
import fr.nwg.kingdomwar.system.collision.CollisionPair;
import fr.nwg.kingdomwar.system.collision.CollisionSystem;
import fr.nwg.kingdomwar.system.collision.handlers.DealDamageCollisionHandler;
import fr.nwg.kingdomwar.system.foes.AddEnemySystem;
import fr.nwg.kingdomwar.system.foes.LifeRemovalSystem;
import fr.nwg.kingdomwar.system.graphics.DisplayLifeSystem;
import fr.nwg.kingdomwar.system.graphics.DrawingShapeSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayCollisionDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayPositionDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayRadiusDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayRailDebugSystem;
import fr.nwg.kingdomwar.system.input.InputGarbageCollectorSystem;
import fr.nwg.kingdomwar.system.misc.*;
import fr.nwg.kingdomwar.system.tower.MovingBulletSystem;
import fr.nwg.kingdomwar.system.tower.PerceptionSystem;
import fr.nwg.kingdomwar.system.tower.PlacingSystem;
import fr.nwg.kingdomwar.system.tower.ShootingSystem;

public class KingdomWarGame implements ApplicationListener {
    private World world;

    @Override
    public void create() {
        world = new World();
        world.initialize();
        world.setManager(new GroupManager());

        RailFactory.getSimpleRail();
        setCollisions();
        world.setSystem(new PrepareProcessSystem(), false);

        world.setSystem(new DrawingGridSystem());
        world.setSystem(new DrawingShapeSystem());
        world.setSystem(new DisplayLifeSystem());
        addDebugSystems();
        world.setSystem(new MovingBulletSystem());
        world.setSystem(new LifeRemovalSystem());
        // world.setSystem(new DealRandomDamageEveryHalfSecondSystem());
        world.setSystem(new ShootingSystem());
        world.setSystem(new TimeToLiveSystem());
        world.setSystem(new DestinationReachedSystem());
        world.setSystem(new MovingToDestinationSystem());
        world.setSystem(new PerceptionSystem());

        world.setSystem(new PlacingSystem());

        world.setSystem(new AddEnemySystem());

        world.setSystem(new InputGarbageCollectorSystem(), false);
        world.setSystem(new RemoveEntityFromWorldSystem());

        Entity inputEntity = EntityFactory.createInputEntity(world);
        PositionComponent cursorPosition = inputEntity.getComponent(CursorPositionComponent.class).position;

        //input
        MyInputProcessor inputProcessor = new MyInputProcessor(KingdomWarData.getInstance().getCamera(), cursorPosition);
        inputProcessor.addTouchedUpListener(inputEntity);
        Gdx.input.setInputProcessor(inputProcessor);

        EntityFactory.createEntityPlacementShape(world, cursorPosition);

        EnemyFactory.createBasicEnemy(world);

    }

    private void setCollisions() {

        CollisionSystem collisionSystem = new CollisionSystem();

        // collision entre les balles et les ennemis
        CollisionPair pairBulletsFoes = new CollisionPair(world, Constants.Groups.BULLET, Constants.Groups.FOES);
        pairBulletsFoes.addCollisionHandler(new DealDamageCollisionHandler());

        collisionSystem.addNewCollisionPair(pairBulletsFoes);
        world.setSystem(collisionSystem);

    }

    private void addDebugSystems() {
        world.setSystem(new DisplayPositionDebugSystem());
        world.setSystem(new DisplayRadiusDebugSystem());
        world.setSystem(new DisplayCollisionDebugSystem());
        world.setSystem(new DisplayRailDebugSystem());
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime() * 1000);
        world.getSystem(PrepareProcessSystem.class).process();
        world.process();
        world.getSystem(InputGarbageCollectorSystem.class).process();
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
