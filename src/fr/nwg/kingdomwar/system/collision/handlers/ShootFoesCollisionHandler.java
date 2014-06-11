package fr.nwg.kingdomwar.system.collision.handlers;

import com.artemis.Component;
import com.artemis.ComponentType;
import com.artemis.Entity;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.tower.AimingComponent;
import fr.nwg.kingdomwar.system.collision.CollisionHandler;

/**
 * Created by eptwalabha on 11/06/2014.
 */
public class ShootFoesCollisionHandler extends CollisionHandler {
    @Override
    public void collide(Entity tower, Entity foe) {
        Component foePosition = foe.getComponent(ComponentType.getTypeFor(PositionComponent.class));
        tower.addComponent(new AimingComponent(((PositionComponent) foePosition)));
        tower.changedInWorld();
    }
}
