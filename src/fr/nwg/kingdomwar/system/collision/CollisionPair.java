package fr.nwg.kingdomwar.system.collision;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: eptwalabha
 * Date: 01/03/14
 * Time: 10:01
 */
public class CollisionPair {

    private ImmutableBag<Entity> groupA;
    private ImmutableBag<Entity> groupB;
    private List<CollisionHandler> collisionHandlers;
    private CollisionSystem collisionSystem;
    private int numberOfCollision;

    public CollisionPair(World world, String groupA, String groupB, CollisionHandler... collisionHandlers) {
        this.groupA = world.getManager(GroupManager.class).getEntities(groupA);
        this.groupB = world.getManager(GroupManager.class).getEntities(groupB);
        this.collisionHandlers = new ArrayList<CollisionHandler>();
        Collections.addAll(this.collisionHandlers, collisionHandlers);
    }

    public int process() {

        numberOfCollision = 0;

        for (Entity entityA : groupA)
            for (Entity entityB : groupB)
                checkAndProcessCollisionsBetween(entityA, entityB);

        return numberOfCollision;
    }

    private void checkAndProcessCollisionsBetween(Entity entityA, Entity entityB) {
        if (entityA == entityB)
            return;

        if (entitiesCollide(entityA, entityB)) {
            triggerAllCollisionHandlers(entityA, entityB);
            numberOfCollision++;
        }
    }

    private void triggerAllCollisionHandlers(Entity entityA, Entity entityB) {
        for (CollisionHandler collisionHandler : collisionHandlers)
            collisionHandler.collide(entityA, entityB);
    }

    private boolean entitiesCollide(Entity entityA, Entity entityB) {
        if (collisionSystem == null)
            return false;

        CircleCollisionComponent circleCollisionComponentA = collisionSystem.getEntityCircleCollisionFor(entityA);
        CircleCollisionComponent circleCollisionComponentB = collisionSystem.getEntityCircleCollisionFor(entityB);

        return circleCollisionComponentA != null && circleCollisionComponentB != null && circleCollisionComponentA.intersects(circleCollisionComponentB);
    }

    public void setCollisionSystem(CollisionSystem collisionSystem) {
        this.collisionSystem = collisionSystem;
    }

    public boolean addCollisionHandler(CollisionHandler collisionHandler) {
        return collisionHandlers.add(collisionHandler);
    }

    public boolean removeCollisionHandler(CollisionHandler collisionHandler) {
        return collisionHandlers.remove(collisionHandler);
    }
}
