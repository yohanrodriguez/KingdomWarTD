package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.tower.AimingComponent;
import fr.nwg.kingdomwar.component.tower.TargetListComponent;
import sun.security.acl.AclImpl;

import java.util.ArrayList;

/**
 * Created by eptwalabha on 11/06/2014.
 */
public class CleanTowersTargetListsSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<TargetListComponent> targetListComponentMapper;

    public CleanTowersTargetListsSystem() {
        super(Aspect.getAspectForAll(TargetListComponent.class));
    }

    @Override
    protected void process(Entity entity) {
//        entity.removeComponent(TargetListComponent.class);
//        entity.changedInWorld();
        TargetListComponent targetListComponent = targetListComponentMapper.get(entity);
        targetListComponent.targetList = new ArrayList<Entity>();
    }
}
