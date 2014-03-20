package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.DestinationComponent;
import fr.nwg.kingdomwar.component.PositionComponent;

public class MovingBulletSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<DestinationComponent> destinationComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public MovingBulletSystem() {
        super(Aspect.getAspectForAll(DestinationComponent.class, PositionComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent position = positionComponentMapper.get(entity);
        DestinationComponent destination = destinationComponentMapper.get(entity);

        position.x += (destination.position.x - position.x) / 100 * world.delta;
        position.y += (destination.position.y - position.y) / 100 * world.delta;
    }
}
