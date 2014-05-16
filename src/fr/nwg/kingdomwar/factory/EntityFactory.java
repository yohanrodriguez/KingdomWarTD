package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.Constants;
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
import fr.nwg.kingdomwar.listener.AreaClickListenerComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

import static fr.nwg.kingdomwar.Constants.*;

public class EntityFactory {
    public static Entity createTowerEntity(KingdomWarWorld world, Vector3 position, PositionComponent aimingPosition) {
        Entity tower = world.createEntity();
        SizeComponent size = getCellSizeFromWorldSize(GRID_ROWS, GRID_COLUMNS);
        tower.addComponent(size);
        tower.addComponent(new PositionComponent(position, -((size.width)/2), -((size.height)/2)));
        tower.addComponent(new DrawingComponent(255, 255, 255, 1));
        tower.addComponent(new AimingComponent(aimingPosition));
        tower.addComponent(new FiringRateComponent(100));
        tower.addToWorld();
        return tower;
    }

    public static Entity createBullet(KingdomWarWorld world, PositionComponent position, AimingComponent aiming) {
        Entity bullet = world.createEntity();
        SizeComponent size = getCellSizeFromWorldSize(GRID_ROWS, GRID_COLUMNS);
        bullet.addComponent(new DrawingComponent(255, 0, 0, 1));
        bullet.addComponent(new SizeComponent(5, 5));
        PositionComponent positionComponent = new PositionComponent(position, size.width/2, size.height/2);
        positionComponent.origin = position;
        bullet.addComponent(positionComponent);
        bullet.addComponent(new SpeedComponent(500));
        bullet.addComponent(new TimeToLiveComponent(10000));
        bullet.addComponent(new VelocityComponent(positionComponent, aiming.position));
        bullet.addToWorld();
        return bullet;
    }

    public static Entity createEntityPlacementShape(KingdomWarWorld world, PositionComponent cursorPosition) {
        Entity placementShape = world.createEntity();
        SizeComponent size = getCellSizeFromWorldSize(GRID_ROWS, GRID_COLUMNS);
        placementShape.addComponent(new DrawingComponent(1, 1, 1, 1));
        placementShape.addComponent(size);

        PositionComponent positionComponent = new PositionComponent(cursorPosition, -size.width/2, -size.height/2);

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

    public static Entity createGrid(KingdomWarWorld world, int rows, int columns) {
        Entity grid = world.createEntity();
        grid.addComponent(new RowsComponent(rows));
        grid.addComponent(new ColumnsComponent(columns));

        grid.addComponent(new AreaClickListenerComponent(new PositionComponent(), new SizeComponent((int)WORLD_WIDTH / 2, (int)WORLD_HEIGHT / 2)));

        grid.addToWorld();
        return grid;
    }

    public static void createCellsFromGrid(KingdomWarWorld world, Entity grid) {
        int rows = grid.getComponent(RowsComponent.class).rows;
        int columns = grid.getComponent(ColumnsComponent.class).columns;
        SizeComponent cellSize = getCellSizeFromWorldSize(rows, columns);

        for (int i=0; i < rows; ++i){
            for (int j=0; j < columns; ++j) {
                Entity cell = world.createEntity();
                cell.addComponent(new GridPosititionComponent(i, j));
                cell.addComponent(getPositionFromGridPosition(i, j, cellSize));
                DrawingComponent drawingComponent = new DrawingComponent(255, 255, 255, 1);
                drawingComponent.shapeType = ShapeRenderer.ShapeType.Line;
                cell.addComponent(drawingComponent);
                cell.addComponent(cellSize);
                cell.addToWorld();
            }
        }
    }

    private static SizeComponent getCellSizeFromWorldSize(int rows, int columns) {
        int width = (int) (Constants.WORLD_WIDTH / rows);
        int height = (int) (Constants.WORLD_HEIGHT / columns);
        return new SizeComponent(width, height);
    }

    private static PositionComponent getPositionFromGridPosition(int gridX, int gridY, SizeComponent cellSize) {
        int x = gridX * cellSize.width;
        int y = gridY * cellSize.height;
        return new PositionComponent(x, y);
    }
}
