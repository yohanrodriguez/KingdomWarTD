package fr.nwg.kingdomwar;

import com.badlogic.gdx.ApplicationListener;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.system.DrawingShapeSystem;
import fr.nwg.kingdomwar.system.PlacingSystem;
import fr.nwg.kingdomwar.system.PrepareProcessSystem;
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
        EntityFactory.createTowerEntity(world);
    }

    @Override
    public void render() {
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
