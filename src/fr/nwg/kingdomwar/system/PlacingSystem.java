package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.InputComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.input.InputManager;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class PlacingSystem extends VoidEntitySystem {

    private final InputManager inputManager;
    private final OrthographicCamera camera;

    public PlacingSystem(KingdomWarWorld kingdomWarWorld) {
        inputManager = kingdomWarWorld.getInputManager();
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void processSystem() {
        if(inputManager.isTouchedUp()) {
            Vector3 positionVector = inputManager.getTouchPosition();
            camera.unproject(positionVector);
            Entity tower = EntityFactory.createTowerEntity((KingdomWarWorld) world, positionVector);
            tower.changedInWorld();
        }
    }
}
