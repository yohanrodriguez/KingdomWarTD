package fr.nwg.kingdomwar.system.collision;

import com.artemis.World;
import fr.nwg.kingdomwar.system.collision.handlers.TowerDetectionCollision;

/**
 * User: eptwalabha
 * Date: 01/03/14
 * Time: 20:00
 */
public class CollisionHandlerFactory {

    public static CollisionPair getCollisionPlayerPoint(World world, String groupA, String groupB) {
        CollisionPair collisionPlayerPoint = new CollisionPair(world, groupA, groupB);
        return collisionPlayerPoint;
    }

    public static CollisionPair getTowerDetectionWithFoes(World world, String towers, String foes) {
        CollisionPair collisionTowerDetectionFoes = new CollisionPair(world, towers, foes);
        collisionTowerDetectionFoes.addCollisionHandler(new TowerDetectionCollision());
        return collisionTowerDetectionFoes;
    }
}
