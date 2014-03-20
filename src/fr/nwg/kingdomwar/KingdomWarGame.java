package fr.nwg.kingdomwar;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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
        world.setSystem(new ShootingSystem());
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime());
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
