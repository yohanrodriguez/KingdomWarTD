package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.tower.PerceptionComponent;

public class PerceptionSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PerceptionComponent> perceptionComponentMapper;

    @Override
    protected void process(Entity entity) {

    }

    public PerceptionSystem() {
        super(Aspect.getAspectForAll(PerceptionComponent.class));
    }
}
