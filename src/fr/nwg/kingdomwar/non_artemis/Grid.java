package fr.nwg.kingdomwar.non_artemis;

import com.artemis.Entity;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private float width;
    private float height;
    private int rowCount;
    private int columnsCount;

    private List<Cell> cells;
    private int[][] placementLayer;

    public Grid(int gridRows, int gridColumns, float gridWidth, float gridHeight) {
        this.rowCount = gridRows;
        this.columnsCount = gridColumns;
        width = gridWidth;
        height = gridHeight;
        cells = new ArrayList<Cell>();
        placementLayer = new int[gridColumns][gridRows];

        for(int i = 0; i < gridColumns; ++i)
            for(int j = 0; j < gridRows; ++j) {
                placementLayer[i][j] = 1;
            }
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
        if (cell != null) {
            cells.remove(cell);
        }
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
        return placementLayer[column][row];
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

    public float getGridHeight() {
        return height;
    }

    public boolean isSpaceAvailable(int column, int row) {
        return isInside(column, row) && getCellAt(column, row) == null && getTopoAt(column, row) == 1;
    }

    public boolean isInside(int column, int row) {
        return row >= 0 && column >= 0 && row < getRowsCount() && column < getColumnsCount();
    }

    public void setTopo(Rail rail) {
        DestinationComponent previousDestination = null;
        for(DestinationComponent destination : rail.getAllDestinations()) {
            int row = this.getRowFromPosition(destination.y);
            int column = this.getColumnFromPosition(destination.x);

            if (previousDestination != null) {
                int previousRow = this.getRowFromPosition(previousDestination.y);
                int previousColumn = this.getColumnFromPosition(previousDestination.x);
                drawFromTo(column, row, previousColumn, previousRow);
            }
            previousDestination = destination;
        }
    }

    private void drawFromTo(int columnA, int rowA, int columnB, int rowB) {
        int minX = (columnA > columnB) ? columnB : columnA;
        int maxX = (columnA > columnB) ? columnA : columnB;
        int minY = (rowA > rowB) ? rowB : rowA;
        int maxY = (rowA > rowB) ? rowA : rowB;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                this.placementLayer[x][y] = 0;
            }
        }
    }
}
