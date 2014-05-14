package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.misc.DeadEntityComponent;

public class RemoveEntityFromWorldSystem extends EntityProcessingSystem {

    public RemoveEntityFromWorldSystem() {
        super(Aspect.getAspectForAll(DeadEntityComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        entity.deleteFromWorld();
    }
}
