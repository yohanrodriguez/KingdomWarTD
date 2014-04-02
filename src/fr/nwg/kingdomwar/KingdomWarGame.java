package fr.nwg.kingdomwar;

import com.artemis.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import fr.nwg.kingdomwar.component.CursorListenerComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
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
        world.setSystem(new PlacingSystem(world));
        world.setSystem(new MovingBulletSystem());
        world.setSystem(new AimingUpdateSystem(world));
        world.setSystem(new ShootingSystem());
        world.setSystem(new UpdatePositionFromCursorPositionListenersSystem(world));

        Entity cursorEntity = EntityFactory.createCursorEntity(world);
        PositionComponent cursorPosition = cursorEntity.getComponent(PositionComponent.class);

        EntityFactory.createEntityPlacementShape(world, cursorPosition);
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime() * 1000);
        world.getSystem(PrepareProcessSystem.class).process();
        world.process();
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
