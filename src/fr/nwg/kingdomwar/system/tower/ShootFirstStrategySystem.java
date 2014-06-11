package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.tower.AimingComponent;
import fr.nwg.kingdomwar.component.tower.ShootFirstStrategyComponent;
import fr.nwg.kingdomwar.component.tower.TargetListComponent;

/**
 * Created by bwaaaaaaaaaaaa on 11/06/2014.
 */
public class ShootFirstStrategySystem extends EntityProcessingSystem{
    @Mapper
    ComponentMapper<TargetListComponent> targetListComponentMapper;

    public ShootFirstStrategySystem() {
        super(Aspect.getAspectForAll(ShootFirstStrategyComponent.class, TargetListComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        TargetListComponent targetList = targetListComponentMapper.get(entity);
        entity.removeComponent(AimingComponent.class);
        if(targetList.targetList.size() > 0) {
            entity.addComponent(new AimingComponent(targetList.targetList.get(0).getComponent(PositionComponent.class)));
        }
        entity.changedInWorld();
    }
}