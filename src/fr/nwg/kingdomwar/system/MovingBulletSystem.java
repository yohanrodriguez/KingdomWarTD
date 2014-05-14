package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.component.physic.VelocityComponent;

public class MovingBulletSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<VelocityComponent> velocityComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;
    @Mapper
    ComponentMapper<SpeedComponent> speedComponentMapper;

    public MovingBulletSystem() {
        super(Aspect.getAspectForAll(VelocityComponent.class, PositionComponent.class, SpeedComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent position = positionComponentMapper.get(entity);
        VelocityComponent velocity = velocityComponentMapper.get(entity);
        SpeedComponent speed = speedComponentMapper.get(entity);

        position.x += velocity.x * speed.speed * world.delta / 1000;
        position.y += velocity.y * speed.speed * world.delta / 1000;
    }
}
