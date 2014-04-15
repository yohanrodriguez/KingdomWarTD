package fr.nwg.kingdomwar.factory;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.*;
import fr.nwg.kingdomwar.component.listener.MouseMovedListenerComponent;
import fr.nwg.kingdomwar.listener.UpdatePositionComponentListener;
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
        bullet.addComponent(new TimeToLiveComponent(10000));
        bullet.addComponent(new VelocityComponent(positionComponent, aiming.position));
        bullet.addToWorld();
        return bullet;
    }

    public static Entity createEntityPlacementShape(KingdomWarWorld world, PositionComponent cursorPosition) {
        Entity placementShape = world.createEntity();
        placementShape.addComponent(new DrawingComponent(1, 1, 1, 1));
        placementShape.addComponent(new SizeComponent(50, 50));

        PositionComponent positionComponent = new PositionComponent(cursorPosition, -25, -25);

        placementShape.addComponent(positionComponent);
        placementShape.addToWorld();

        return placementShape;
    }

    public static Entity createCursorEntity(KingdomWarWorld world) {
        Entity cursor = world.createEntity();
        cursor.addComponent(new CursorListenerComponent());
        PositionComponent cursorPosition = new PositionComponent();
        cursor.addComponent(cursorPosition);
        cursor.addComponent(new MouseMovedListenerComponent(new UpdatePositionComponentListener()));
        cursor.addToWorld();
        return cursor;
    }

    public static Entity createGrid(KingdomWarWorld world, int rows, int columns) {
        Entity grid = world.createEntity();
        grid.addComponent(new RowsComponent(rows));
        grid.addComponent(new ColumnsComponent(columns));

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
