package fr.nwg.kingdomwar.system;

import com.artemis.Entity;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class PlacingSystem extends VoidEntitySystem {

    private final OrthographicCamera camera;

    public PlacingSystem(KingdomWarWorld kingdomWarWorld) {
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void processSystem() {
//        Vector3 positionVector = new Vector3(this.x, this.y, 0);
//        camera.unproject(positionVector);
//        Entity tower = EntityFactory.createTowerEntity((KingdomWarWorld) world, positionVector);
//        tower.changedInWorld();
    }
}
