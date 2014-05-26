package fr.nwg.kingdomwar.system.misc;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.listener.MovingEntityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eptwalabha on 25/05/2014.
 */
public class MovingToDestinationSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;
    @Mapper
    ComponentMapper<SpeedComponent> speedComponentMapper;
    @Mapper
    ComponentMapper<DestinationComponent> destinationComponentMapper;
    private List<MovingEntityListener> listeners = new ArrayList<MovingEntityListener>();

    public MovingToDestinationSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SpeedComponent.class, DestinationComponent.class));
    }

    @Override
    protected void process(Entity entity) {

        PositionComponent position = positionComponentMapper.get(entity);
        SpeedComponent speed = speedComponentMapper.get(entity);
        DestinationComponent destination = destinationComponentMapper.get(entity);

        float realSpeed = speed.speed * world.getDelta() / 1000;
        float deltaX = destination.x - position.x;
        float deltaY = destination.y - position.y;
        float distance = deltaX * deltaX + deltaY * deltaY;
        float ratio = (realSpeed * realSpeed) / distance;
        boolean reach = false;

        if (ratio > 1) {
            entity.removeComponent(destination);
            ratio = 1;
            reach = true;
        }

        position.x += deltaX * ratio;
        position.y += deltaY * ratio;

        if (reach)
            for (MovingEntityListener listener : listeners)
                listener.entityHasReachDestination(entity);

    }

    public void addMovingEntityListener(MovingEntityListener listener) {
        listeners.add(listener);
    }
}
