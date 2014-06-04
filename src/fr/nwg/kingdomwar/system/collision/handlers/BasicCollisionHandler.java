package fr.nwg.kingdomwar.system.collision.handlers;

import com.artemis.Entity;
import fr.nwg.kingdomwar.system.collision.CollisionHandler;
import fr.nwg.kingdomwar.system.collision.CollisionListener;

/**
 * User: eptwalabha
 * Date: 01/03/14
 * Time: 15:10
 */
public class BasicCollisionHandler extends CollisionHandler {

    @Override
    public void collide(Entity entityA, Entity entityB) {
        for (CollisionListener collisionListener : collisionListeners)
            collisionListener.hasCollide(entityA, entityB);
    }
}
