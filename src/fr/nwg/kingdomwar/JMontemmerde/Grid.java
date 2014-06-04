package fr.nwg.kingdomwar.JMontemmerde;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.grid.GridPosititionComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final SizeComponent cellSize;
    private int rows;
    private int columns;
    private List<Cell> cells;
    private int[][] topo;

    public Grid(int gridRows, int gridColumns) {
        this.rows = gridRows;
        this.columns = gridColumns;
        cells = new ArrayList<Cell>();
        this.cellSize = this.getCellSizeFromWorldSize();
        topo = new int[Constants.GRID_COLUMNS][Constants.GRID_ROWS];

        for(int i = 0; i < Constants.GRID_COLUMNS; ++i)
            for(int j = 0; j < Constants.GRID_ROWS; ++j)
                topo[i][j] = (i == 4) ? 0 : 1;
    }

    public void createCellsEntity(KingdomWarWorld world) {
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

    private SizeComponent getCellSizeFromWorldSize() {
        int width = (int) (Constants.WORLD_WIDTH / columns);
        int height = (int) (Constants.WORLD_HEIGHT / rows);
        System.out.println("width:" + width + ", height:" + height);
        return new SizeComponent(width, height);
    }

    private PositionComponent getPositionFromGridPosition(int gridX, int gridY, SizeComponent cellSize) {
        int x = gridX * cellSize.width;
        int y = gridY * cellSize.height;
        return new PositionComponent(x, y);
    }

    public void addEntityAt(Entity entity, int column, int row) {
        Cell cell = this.getCellAt(column, row);
        if (cell != null)
            cells.remove(cell);
        cells.add(new Cell(entity, column, row));
    }

    private Cell getCellAt(int column, int row) {
        for (Cell cell : cells)
            if (cell.row == row && cell.column == column)
                return cell;
        return null;
    }

    public int getRowFromPosition(float positionY) {
        return (int) Math.floor(positionY / cellSize.height);
    }

    public int getColumnFromPosition(float positionX) {
        return (int) Math.floor(positionX / cellSize.width);
    }

    public SizeComponent getCellSize() {
        return cellSize;
    }

    public int getTopoAt(int column, int row) {
        return topo[column][row];
    }
}
