package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.InputComponent;
import fr.nwg.kingdomwar.input.InputManager;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class PlacingSystem extends EntityProcessingSystem {

    private final InputManager inputManager;

    public PlacingSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(InputComponent.class));
        inputManager = kingdomWarWorld.getInputManager();
    }

    @Override
    protected void process(Entity entity) {
        if(inputManager.isTouchedUp()) {
            System.out.println("Hey cocuou");
        }
    }
}
