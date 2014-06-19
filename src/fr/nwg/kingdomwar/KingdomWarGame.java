package fr.nwg.kingdomwar;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import fr.nwg.kingdomwar.factory.RailFactory;
import fr.nwg.kingdomwar.system.collision.handlers.DealDamageCollisionHandler;
import fr.nwg.kingdomwar.system.foes.SpawnFoeSystem;
import fr.nwg.kingdomwar.system.graphics.*;
import fr.nwg.kingdomwar.system.tower.*;
import fr.nwg.kingdomwar.system.collision.handlers.FillTargetListHandler;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EnemyFactory;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.input.MyInputProcessor;
import fr.nwg.kingdomwar.system.collision.CollisionPair;
import fr.nwg.kingdomwar.system.collision.CollisionSystem;
import fr.nwg.kingdomwar.system.foes.LifeRemovalSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayCollisionDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayPositionDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayRadiusDebugSystem;
import fr.nwg.kingdomwar.system.graphics.debug.DisplayRailDebugSystem;
import fr.nwg.kingdomwar.system.input.InputGarbageCollectorSystem;
import fr.nwg.kingdomwar.system.misc.*;

import java.util.ArrayList;

public class KingdomWarGame implements ApplicationListener {
    private World world;
    private TextureAtlas atlas;
    @Override
    public void create() {

        atlas = new TextureAtlas("test.txt");

        world = new World();
        world.initialize();
        world.setManager(new GroupManager());

        RailFactory.getSimpleRail();
        world.setSystem(new SpawnFoeSystem());

        setCollisions();
        world.setSystem(new PrepareProcessSystem(), false);

        world.setSystem(new PlacingSystem());
        world.setSystem(new DrawingGridSystem());
        world.setSystem(new DrawingShapeSystem());
        world.setSystem(new DisplayLifeSystem());
        world.setSystem(new DrawingSpriteSystem());

        world.setSystem(new MovingToDestinationSystem());
        world.setSystem(new MovingBulletSystem());
        world.setSystem(new LifeRemovalSystem());
        world.setSystem(new ShootingSystem());
        world.setSystem(new TimeToLiveSystem());
        world.setSystem(new DestinationReachedSystem());
        world.setSystem(new ShootFirstStrategySystem());

        //addDebugSystems();
        world.setSystem(new InputGarbageCollectorSystem(), false);

        // dernier système à appeler!!!
        world.setSystem(new CleanTowersTargetListsSystem());
        world.setSystem(new RemoveEntityFromWorldSystem(), false);

        Entity inputEntity = EntityFactory.createInputEntity(world);
        PositionComponent cursorPosition = inputEntity.getComponent(CursorPositionComponent.class).position;

        //input
        MyInputProcessor inputProcessor = new MyInputProcessor(KingdomWarData.getInstance().getCamera(), cursorPosition);
        inputProcessor.addTouchedUpListener(inputEntity);
        Gdx.input.setInputProcessor(inputProcessor);

//        EntityFactory.createEntityPlacementShape(world, cursorPosition);

        EnemyFactory.createBasicEnemy(world);

        world.setSystem(new DrawHUDSystem());
    }

    private void setCollisions() {

        CollisionSystem collisionSystem = new CollisionSystem();

        // collision entre les balles et les ennemis
        CollisionPair pairBulletsFoes = new CollisionPair(world, Constants.Groups.BULLET, Constants.Groups.FOES);
        pairBulletsFoes.addCollisionHandler(new DealDamageCollisionHandler());

        CollisionPair pairTowersFoes = new CollisionPair(world, Constants.Groups.TOWERS, Constants.Groups.FOES);
        pairTowersFoes.addCollisionHandler(new FillTargetListHandler());

        collisionSystem.addNewCollisionPair(pairBulletsFoes);
        collisionSystem.addNewCollisionPair(pairTowersFoes);
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
        world.setDelta(Gdx.graphics.getDeltaTime() * 1000 * KingdomWarData.getInstance().timeScale);
        world.getSystem(PrepareProcessSystem.class).process();
        world.getSystem(RemoveEntityFromWorldSystem.class).process();
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
        world.dispose();
        KingdomWarData.getInstance().dispose();
    }
}
