package fr.nwg.kingdomwar.system.collision;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * User: eptwalabha
 * Date: 28/02/14
 * Time: 00:09
 */
public class CollisionSystem extends EntitySystem {

    @Mapper
    ComponentMapper<CircleCollisionComponent> circleCollisionComponentMapper;

    private List<CollisionPair> listOfCollisionPairs;
    private long numberOfCollision = 0;

    public CollisionSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, CircleCollisionComponent.class));
        listOfCollisionPairs = new ArrayList<CollisionPair>();
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        numberOfCollision = 0;
        for (CollisionPair collisionPair : listOfCollisionPairs)
            numberOfCollision += collisionPair.process();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    public boolean addNewCollisionPair(CollisionPair collisionPair) {
        collisionPair.setCollisionSystem(this);
        return listOfCollisionPairs.add(collisionPair);
    }

    public CircleCollisionComponent getEntityCircleCollisionFor(Entity entity) {
        return circleCollisionComponentMapper.getSafe(entity);
    }

    public long getNumberOfCollision() {
        return numberOfCollision;
    }
}