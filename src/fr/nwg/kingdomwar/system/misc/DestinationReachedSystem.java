package fr.nwg.kingdomwar.system.misc;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;

public class DestinationReachedSystem extends EntityProcessingSystem {

    @Mapper
    private ComponentMapper<RailComponent> railComponentMapper;

    public DestinationReachedSystem() {
        super(Aspect.getAspectForAll(DestinationReachedComponent.class, RailComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        RailComponent rail = railComponentMapper.get(entity);
        DestinationComponent nextDestination = rail.getNextDestination();
        if (nextDestination != null) {
            entity.addComponent(nextDestination);
            rail.moveIndexForward();
        }
        entity.removeComponent(DestinationReachedComponent.class);
        entity.changedInWorld();
    }
}
