package fr.nwg.kingdomwar.system.collision.handlers;

import com.artemis.ComponentType;
import com.artemis.Entity;
import fr.nwg.kingdomwar.component.tower.TargetListComponent;
import fr.nwg.kingdomwar.system.collision.CollisionHandler;

/**
 * Created by mwaaaaa on 11/06/2014.
 */
public class FillTargetListHandler extends CollisionHandler {
    @Override
    public void collide(Entity tower, Entity foe) {
        ComponentType componentType = ComponentType.getTypeFor(TargetListComponent.class);
        TargetListComponent targetList = (TargetListComponent) tower.getComponent(componentType);
        targetList.targetList.add(foe);
    }
}
