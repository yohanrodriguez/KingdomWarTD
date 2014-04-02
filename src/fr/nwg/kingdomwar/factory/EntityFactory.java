package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.*;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class EntityFactory {
    public static Entity createTowerEntity(KingdomWarWorld world, Vector3 position) {
        Entity tower = world.createEntity();
        tower.addComponent(new PositionComponent(position, -25, -25));
        tower.addComponent(new DrawingComponent(255, 255, 255, 1));
        tower.addComponent(new SizeComponent(50, 50));
        tower.addComponent(new InputComponent());
        tower.addComponent(new AimingComponent(new PositionComponent()));
        tower.addComponent(new FiringRateComponent(100));
        tower.addToWorld();
        return tower;
    }

    public static Entity createBullet(KingdomWarWorld world, PositionComponent position, AimingComponent aiming) {
        Entity bullet = world.createEntity();
        bullet.addComponent(new DrawingComponent(255, 0, 0, 1));
        bullet.addComponent(new SizeComponent(5, 5));
        PositionComponent positionComponent = new PositionComponent(position, 25, 25);
        positionComponent.origin = position;
        bullet.addComponent(positionComponent);
        bullet.addComponent(new SpeedComponent(500));
        bullet.addComponent(new VelocityComponent(positionComponent, aiming.position));
        bullet.addToWorld();
        return bullet;
    }

    public static Entity createEntityPlacementShape(KingdomWarWorld world, PositionComponent cursorPosition) {
        Entity placementShape = world.createEntity();
        placementShape.addComponent(new DrawingComponent(255, 255, 255, 1));
        placementShape.addComponent(new SizeComponent(50, 50));

        PositionComponent positionComponent = new PositionComponent(cursorPosition, -25, -25);

        placementShape.addComponent(positionComponent);
//        placementShape.addComponent(new CursorListenerComponent());
        placementShape.addToWorld();

        return placementShape;
    }

    public static Entity createCursorEntity(KingdomWarWorld world) {
        Entity cursor = world.createEntity();
        cursor.addComponent(new CursorListenerComponent());
        PositionComponent cursorPosition = new PositionComponent();
        cursor.addComponent(cursorPosition);
        cursor.addToWorld();
        return cursor;
    }
}
