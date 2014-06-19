package fr.nwg.kingdomwar.system.graphics;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.non_artemis.Grid;

/**
 * User: Eptwalabha
 * Date: 06/06/2014
 * Time: 23:20
 */
public class DrawingGridSystem extends VoidEntitySystem {

    private final ShapeRenderer shapeRenderer;
    private final Grid grid;

    public DrawingGridSystem() {
        shapeRenderer = KingdomWarData.getInstance().getShapeRenderer();
        grid = KingdomWarData.getInstance().getGrid();
    }


    @Override
    protected void processSystem() {

        drawGridFrame();
        drawGridTopography();

    }

    private void drawGridTopography() {
        int nbrColumns = grid.getColumnsCount();
        int nbrRows = grid.getRowsCount();
        float widthColumn = grid.getGridWidth() / nbrColumns;
        float heightRow = grid.getGridHeight() / nbrRows;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0f, 0f, 1f, 0.1f));

        for (int column = 0; column < nbrColumns; column++) {
            for (int row = 0; row < nbrRows; row++) {
                if (grid.getTopoAt(column, row) == 1) {
                    shapeRenderer.setColor(new Color(0.5f, 1f, 0.3f, 0.1f));
                } else {
                    shapeRenderer.setColor(new Color(0.8f, 0.8f, 0.8f, 0.1f));
                }
                shapeRenderer.rect(column * widthColumn, row * heightRow, widthColumn - 1, heightRow - 1);
            }
        }

        shapeRenderer.end();
    }

    private void drawGridFrame() {
        int nbrColumns = grid.getColumnsCount();
        int nbrRows = grid.getRowsCount();
        float widthColumn = grid.getGridWidth() / nbrColumns;
        float heightRow = grid.getGridHeight() / nbrRows;
        float columnPosition, rowPosition;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GRAY);

        for (int column = 0; column <= nbrColumns; column++) {
            columnPosition = column * widthColumn;
            shapeRenderer.line(columnPosition, 0, columnPosition, grid.getGridHeight());
            for (int row = 0; row <= nbrRows; row++) {
                rowPosition = row * heightRow;
                shapeRenderer.line(0, rowPosition, grid.getGridWidth(), rowPosition);
            }
        }

        shapeRenderer.end();
    }
}
