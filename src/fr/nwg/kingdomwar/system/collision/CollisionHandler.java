package fr.nwg.kingdomwar.system.collision;

import com.artemis.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: eptwalabha
 * Date: 01/03/14
 * Time: 09:58
 */
public abstract class CollisionHandler {


    protected List<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();

    public void clearAllCollisionListener() {
        collisionListeners = new ArrayList<CollisionListener>();
    }

    public boolean addCollisionListener(CollisionListener collisionListener) {
        return collisionListeners.add(collisionListener);
    }

    public boolean removeCollisionListener(CollisionListener collisionListener) {
        return collisionListeners.remove(collisionListener);
    }

    public abstract void collide(Entity entityA, Entity entityB);
}
