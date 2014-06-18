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
                topo[i][j] = (Math.random() < 0.1) ? 0 : 1;
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

    public void addEntityAt(Entity entity, int column, int row, int width, int height) {

        int hw = (int) Math.floor(width / 2);
        int hh = (int) Math.floor(height / 2);
        int firstRow = (row - hh >= 0) ? row - hh : 0;
        int firstColumn = (column - hw > 0) ? column - hw : 0;
        int lastRow = firstRow + height;
        int lastColumn = firstColumn + width;

        // à dégager.
        for (int c = firstColumn; c < lastColumn; c++) {
            for (int r = firstRow; r < lastRow; r++) {
                Cell cell = this.getCellAt(c, r);

                if (cell != null)
                    cells.remove(cell);
                cells.add(new Cell(entity, c, r));
            }
        }

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

    public float getGridHeight() {
        return height;
    }

    public boolean isSpaceAvailable(int column, int row) {
        return this.getCellAt(column, row) == null && this.getTopoAt(column, row) == 1;
    }

    public boolean isSpaceAvailable(int column, int row, int width, int height) {

        int hw = (int) Math.floor(width / 2);
        int hh = (int) Math.floor(height / 2);
        int firstRow = (row - hh >= 0) ? row - hh : 0;
        int firstColumn = (column - hw > 0) ? column - hw : 0;
        int lastRow = firstRow + height;
        int lastColumn = firstColumn + width;

        if (lastRow >= this.rowCount || lastColumn >= this.columnsCount)
            return false;

        for (int c = firstColumn; c < lastColumn; c++)
            for (int r = firstRow; r < lastRow; r++)
                if (!isSpaceAvailable(c, r))
                    return false;

        return true;
    }
}
