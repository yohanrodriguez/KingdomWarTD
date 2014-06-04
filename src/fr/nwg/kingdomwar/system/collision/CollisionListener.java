package fr.nwg.kingdomwar.system.collision;

import com.artemis.Entity;

/**
 * User: eptwalabha
 * Date: 21/02/14
 * Time: 23:16
 */
public interface CollisionListener {

    public void hasCollide(Entity entityA, Entity entityB);
}
