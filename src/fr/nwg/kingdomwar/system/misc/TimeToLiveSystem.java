package fr.nwg.kingdomwar.system.misc;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.DelayedEntityProcessingSystem;
import fr.nwg.kingdomwar.component.misc.DeadEntityComponent;
import fr.nwg.kingdomwar.component.misc.TimeToLiveComponent;

public class TimeToLiveSystem extends DelayedEntityProcessingSystem {

    @Mapper
    ComponentMapper<TimeToLiveComponent> timeToLiveComponentMapper;

    public TimeToLiveSystem() {
        super(Aspect.getAspectForAll(TimeToLiveComponent.class));
    }

    @Override
    protected float getRemainingDelay(Entity entity) {
        return timeToLiveComponentMapper.get(entity).timeToLive;
    }

    @Override
    protected void processDelta(Entity entity, float delay) {
        timeToLiveComponentMapper.get(entity).timeToLive -= delay;
    }

    @Override
    protected void processExpired(Entity entity) {
        entity.addComponent(new DeadEntityComponent());
        entity.changedInWorld();
    }
}
