package fr.nwg.kingdomwar.system.misc;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
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

    public MovingToDestinationSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SpeedComponent.class, DestinationComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent position = positionComponentMapper.get(entity);
        SpeedComponent speed = speedComponentMapper.get(entity);
        DestinationComponent destination = destinationComponentMapper.get(entity);

        //System.out.println("avant, x = " + position.getRealPositionX() + ", y = " + position.getRealPositionY());
        float realSpeed = speed.speed * world.getDelta() / 10f;
        //System.out.println("speed = " + speed.speed + ", speed review = " + realSpeed);

        float deltaX = destination.x - position.getRealPositionX();
        float deltaY = destination.y - position.getRealPositionY();
        float distance = deltaX * deltaX + deltaY * deltaY;
        float ratio = (realSpeed * realSpeed) / distance;

        if (ratio > 1) {
            entity.addComponent(new DestinationReachedComponent());
            entity.changedInWorld();
            ratio = 1;
        }

        position.x += deltaX * ratio;
        position.y += deltaY * ratio;
        //System.out.println("après, x = " + position.getRealPositionX() + ", y = " + position.getRealPositionY());
    }
}
