package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.misc.TimeToLiveComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.component.physic.VelocityComponent;
import fr.nwg.kingdomwar.component.tower.AimingComponent;
import fr.nwg.kingdomwar.component.grid.ColumnsComponent;
import fr.nwg.kingdomwar.component.grid.GridPosititionComponent;
import fr.nwg.kingdomwar.component.grid.RowsComponent;
import fr.nwg.kingdomwar.component.tower.FiringRateComponent;
import fr.nwg.kingdomwar.component.tower.PerceptionComponent;
import fr.nwg.kingdomwar.listener.AreaClickListenerComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

import static fr.nwg.kingdomwar.Constants.*;

public class EntityFactory {
    public static Entity createTowerEntity(KingdomWarWorld world, Vector3 position, PositionComponent aimingPosition) {
        Entity tower = world.createEntity();
        SizeComponent size = world.getGrid().getCellSize();
        tower.addComponent(size);
        tower.addComponent(new PositionComponent(position, 0, 0));
        tower.addComponent(new DrawingComponent(255, 255, 255, 1));
//        tower.addComponent(new AimingComponent(aimingPosition));
        tower.addComponent(new PerceptionComponent(200));
        tower.addComponent(new FiringRateComponent(200));
        tower.addToWorld();
        return tower;
    }

    public static Entity createBullet(KingdomWarWorld world, PositionComponent position, AimingComponent aiming) {
        Entity bullet = world.createEntity();
        SizeComponent size = world.getGrid().getCellSize();
        bullet.addComponent(new DrawingComponent(255, 0, 0, 1));
        bullet.addComponent(new SizeComponent(5, 5));

        PositionComponent positionComponent = new PositionComponent(position, size.width/2, size.height/2);

        positionComponent.origin = position;
        bullet.addComponent(new CircleCollisionComponent(positionComponent, 2.5f));
        bullet.addComponent(positionComponent);
        bullet.addComponent(new SpeedComponent(500));
        bullet.addComponent(new TimeToLiveComponent(10000));
        bullet.addComponent(new VelocityComponent(positionComponent, aiming.position));
        bullet.addToWorld();

        GroupManager manager = world.getManager(GroupManager.class);
        manager.add(bullet, Constants.Groups.BULLET);
        return bullet;
    }

    public static Entity createEntityPlacementShape(KingdomWarWorld world, PositionComponent cursorPosition) {
        Entity placementShape = world.createEntity();
        SizeComponent size = world.getGrid().getCellSize();
        placementShape.addComponent(new DrawingComponent(1, 1, 1, 1));
        placementShape.addComponent(size);

        PositionComponent positionComponent = new PositionComponent(cursorPosition, -size.width / 2, -size.height / 2);

        placementShape.addComponent(positionComponent);
        placementShape.addToWorld();

        return placementShape;
    }

    public static Entity createInputEntity(KingdomWarWorld world) {
        Entity input = world.createEntity();
        CursorPositionComponent cursorPosition = new CursorPositionComponent();
        input.addComponent(cursorPosition);
        input.addToWorld();
        return input;
    }
}
