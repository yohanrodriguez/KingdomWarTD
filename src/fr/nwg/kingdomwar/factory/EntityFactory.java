package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.BulletPowerComponent;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingSpriteComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.misc.CostComponent;
import fr.nwg.kingdomwar.component.misc.TimeToLiveComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.component.physic.VelocityComponent;
import fr.nwg.kingdomwar.component.tower.*;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class EntityFactory {
    public static Entity createTowerEntity(World world, Vector3 position, PositionComponent aimingPosition) {
        Entity tower = world.createEntity();
        SizeComponent size = KingdomWarData.getInstance().getGrid().getCellSize();

        PositionComponent positionComponent = new PositionComponent(position, 0, 0);

        tower.addComponent(size);
        tower.addComponent(positionComponent);
//        tower.addComponent(new DrawingComponent(255, 255, 255, 1));
        tower.addComponent(new FiringRateComponent(600));
        tower.addComponent(new CircleCollisionComponent(positionComponent, 200));
        tower.addComponent(new TargetListComponent());
        tower.addComponent(new ShootFirstStrategyComponent());
        tower.addComponent(new DrawingSpriteComponent(KingdomWarData.getInstance().getSprite(Constants.Textures.TOWER), new PositionComponent(positionComponent, size.width, size.height), size.width, size.height));
        GroupManager manager = world.getManager(GroupManager.class);
        manager.add(tower, Constants.Groups.TOWERS);
        tower.addToWorld();
        return tower;
    }

    public static Entity createBullet(World world, PositionComponent position, AimingComponent aiming) {
        Entity bullet = world.createEntity();
        SizeComponent size = KingdomWarData.getInstance().getGrid().getCellSize();
        bullet.addComponent(new DrawingComponent(255, 0, 0, 1));
        bullet.addComponent(new SizeComponent(5, 5));
        bullet.addComponent(new BulletPowerComponent(10));

        PositionComponent positionComponent = new PositionComponent(position);

        positionComponent.origin = position;
        bullet.addComponent(new CircleCollisionComponent(positionComponent, 5f));
        bullet.addComponent(new DrawingSpriteComponent(KingdomWarData.getInstance().getSprite(Constants.Textures.BULLET), positionComponent, 10, 10));
        bullet.addComponent(positionComponent);
        bullet.addComponent(new SpeedComponent(750));
        bullet.addComponent(new TimeToLiveComponent(2000));
        bullet.addComponent(new VelocityComponent(positionComponent, aiming.position));


        GroupManager manager = world.getManager(GroupManager.class);
        manager.add(bullet, Constants.Groups.BULLET);
        bullet.addToWorld();
        return bullet;
    }

    public static Entity createEntityPlacementShape(World world, PositionComponent cursorPosition) {
        Entity placementShape = world.createEntity();
        SizeComponent size = KingdomWarData.getInstance().getGrid().getCellSize();
        placementShape.addComponent(new DrawingComponent(1, 1, 1, 1));
        placementShape.addComponent(size);

        PositionComponent positionComponent = new PositionComponent(cursorPosition, -size.width / 2, -size.height / 2);

        placementShape.addComponent(positionComponent);
        placementShape.addToWorld();

        return placementShape;
    }

    public static Entity createInputEntity(World world) {
        Entity input = world.createEntity();
        CursorPositionComponent cursorPosition = new CursorPositionComponent();
        input.addComponent(cursorPosition);
        input.addToWorld();
        return input;
    }
}
