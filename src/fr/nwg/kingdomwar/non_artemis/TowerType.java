package fr.nwg.kingdomwar.non_artemis;

/**
 * Created by eptwalabha on 18/06/2014.
 */
public class TowerType {
    private int buildingCost;
    private float firingRate;

    public TowerType() {

    }

    public TowerType setBuildingCost(int buildingCost) {
        this.buildingCost = buildingCost;
        return this;
    }

    public TowerType setFiringRate(int firingRate) {
        this.firingRate = firingRate;
        return this;
    }

    public int getBuildingCost() {
        return this.buildingCost;
    }

    public float getFiringRate() {
        return this.firingRate;
    }
}
