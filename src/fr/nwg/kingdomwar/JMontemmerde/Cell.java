package fr.nwg.kingdomwar.JMontemmerde;

import com.artemis.Entity;

public class Cell {

    public int row;
    public int column;
    public Entity entity;

    public Cell(Entity entity, int row, int column) {
        this.row = row;
        this.column = column;
        this.entity = entity;
    }
}
