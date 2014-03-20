package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.*;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class EntityFactory {
    public static Entity createTowerEntity(KingdomWarWorld world, Vector3 position) {
        Entity tower = world.createEntity();
        tower.addComponent(new DrawingComponent(255, 255, 255, 1));
        tower.addComponent(new SizeComponent(50, 50));
        tower.addComponent(new PositionComponent(position));
        tower.addComponent(new InputComponent());
        tower.addComponent(new AimingComponent(new PositionComponent(300,400)));
        tower.addComponent(new FiringRateComponent(200));
        tower.addToWorld();
        return tower;
    }

    public static Entity createBullet(KingdomWarWorld world, PositionComponent position, AimingComponent aiming) {
        Entity bullet = world.createEntity();
        bullet.addComponent(new DrawingComponent(255, 0, 0, 1));
        bullet.addComponent(new SizeComponent(5, 5));
        bullet.addComponent(new PositionComponent(position));
        bullet.addComponent(new DestinationComponent(aiming.position));
        bullet.addToWorld();
        return bullet;
    }
}
