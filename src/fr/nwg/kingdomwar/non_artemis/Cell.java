package fr.nwg.kingdomwar.non_artemis;

import com.artemis.Entity;

public class Cell {

    public int row;
    public int column;
    public Entity entity;

    public Cell(Entity entity, int column, int row) {
        this.row = row;
        this.column = column;
        this.entity = entity;
    }
}
