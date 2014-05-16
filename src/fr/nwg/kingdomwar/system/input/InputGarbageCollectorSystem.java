package fr.nwg.kingdomwar.system.input;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.input.TouchedUpComponent;

/**
 * Created by manu on 16/05/14.
 */
public class InputGarbageCollectorSystem extends EntityProcessingSystem{
    public InputGarbageCollectorSystem() {
        super(Aspect.getAspectForAll(TouchedUpComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        entity.removeComponent(TouchedUpComponent.class);
        entity.changedInWorld();
    }
}
