package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.Constants;
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
        PositionComponent destination = new PositionComponent(42, 42);
        DrawingComponent drawingComponent = new DrawingComponent(0, .7f, .7f, 1);

        Entity basicEnemy = world.createEntity();
        basicEnemy.addComponent(position);
        basicEnemy.addComponent(new LifeComponent(100));
        basicEnemy.addComponent(new SpeedComponent(42));
        basicEnemy.addComponent(new VelocityComponent(position, destination));
        basicEnemy.addComponent(new DrawingTypeComponent(DrawingTypeComponent.DrawingType.ELLIPSE));
        SizeComponent size = getCellSizeFromWorldSize(GRID_ROWS, GRID_COLUMNS);
        basicEnemy.addComponent(size);
        basicEnemy.addComponent(drawingComponent);
        basicEnemy.addToWorld();
        return basicEnemy;
    }

    //TODO copy/paste refacto
    private static SizeComponent getCellSizeFromWorldSize(int rows, int columns) {
        int width = (int) (Constants.WORLD_WIDTH / rows);
        int height = (int) (Constants.WORLD_HEIGHT / columns);
        return new SizeComponent(width, height);
    }

}
