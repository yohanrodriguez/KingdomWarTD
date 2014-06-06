package fr.nwg.kingdomwar.non_artemis;

import com.artemis.Entity;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private float width;
    private float height;
    private int rowCount;
    private int columnsCount;

    private List<Cell> cells;
    private int[][] topo;

    public Grid(int gridRows, int gridColumns, float gridWidth, float gridHeight) {
        this.rowCount = gridRows;
        this.columnsCount = gridColumns;
        width = gridWidth;
        height = gridHeight;
        cells = new ArrayList<Cell>();
        topo = new int[gridColumns][gridRows];

        for(int i = 0; i < gridColumns; ++i)
            for(int j = 0; j < gridRows; ++j)
                topo[i][j] = (Math.random() < 0.2) ? 0 : 1;
    }

    private SizeComponent getCellSizeFromWorldSize() {
        int width = (int) (Constants.WORLD_WIDTH / columnsCount);
        int height = (int) (Constants.WORLD_HEIGHT / rowCount);
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
        return (int) Math.floor(positionY / (height / rowCount));
    }

    public int getColumnFromPosition(float positionX) {
        return (int) Math.floor(positionX / (width / columnsCount));
    }

    public SizeComponent getCellSize() {
        return new SizeComponent((int) (width / columnsCount), (int) (height / rowCount));
    }

    public int getTopoAt(int column, int row) {
        return topo[column][row];
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public int getRowsCount() {
        return rowCount;
    }

    public float getGridWidth() {
        return width;
    }

    public float getGridHeigth() {
        return height;
    }
}
