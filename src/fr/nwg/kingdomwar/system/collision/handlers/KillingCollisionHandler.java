package fr.nwg.kingdomwar.system.collision.handlers;

import com.artemis.Entity;
import fr.nwg.kingdomwar.component.misc.DeadEntityComponent;
import fr.nwg.kingdomwar.system.collision.CollisionHandler;
import fr.nwg.kingdomwar.system.collision.CollisionListener;

/**
 * User: eptwalabha
 * Date: 02/03/14
 * Time: 00:31
 */
public class KillingCollisionHandler extends CollisionHandler {
    @Override
    public void collide(Entity entityA, Entity entityB) {
        entityA.addComponent(new DeadEntityComponent());
        entityA.changedInWorld();

        for (CollisionListener collisionListener : collisionListeners)
            collisionListener.hasCollide(entityA, entityB);

        this.clearAllCollisionListener();
    }
}
