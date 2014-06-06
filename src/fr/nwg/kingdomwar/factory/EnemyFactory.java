package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.foes.LifeComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingTypeComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.component.physic.VelocityComponent;

import static fr.nwg.kingdomwar.Constants.GRID_COLUMNS;
import static fr.nwg.kingdomwar.Constants.GRID_ROWS;

public class EnemyFactory extends EntityFactory {

    public static Entity createBasicEnemy(World world) {
        PositionComponent position = new PositionComponent();
        DrawingComponent drawingComponent = new DrawingComponent(0, .7f, .7f, 1);

        Entity basicEnemy = world.createEntity();
        basicEnemy.addComponent(position);
        basicEnemy.addComponent(new LifeComponent(150, 150));
        basicEnemy.addComponent(new SpeedComponent((float) (50f + Math.random() * 10f)));
        basicEnemy.addComponent(new DrawingTypeComponent(DrawingTypeComponent.DrawingType.ELLIPSE));
        SizeComponent size = getCellSizeFromWorldSize(GRID_ROWS, GRID_COLUMNS);
        basicEnemy.addComponent(size);

        basicEnemy.addComponent(new CircleCollisionComponent(position, size.width / 2));
        basicEnemy.addComponent(drawingComponent);
        basicEnemy.addComponent(RailFactory.getSimpleRail());
        basicEnemy.addComponent(new DestinationReachedComponent());
        basicEnemy.addToWorld();

        GroupManager manager = world.getManager(GroupManager.class);
        manager.add(basicEnemy, Constants.Groups.FOES);
        return basicEnemy;
    }

    //TODO copy/paste refacto
    private static SizeComponent getCellSizeFromWorldSize(int rows, int columns) {
        int width = (int) (Constants.WORLD_WIDTH / rows);
        int height = (int) (Constants.WORLD_HEIGHT / columns);
        return new SizeComponent(width, height);
    }

}
