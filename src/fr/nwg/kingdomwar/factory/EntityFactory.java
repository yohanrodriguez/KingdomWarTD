package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import fr.nwg.kingdomwar.component.DrawingComponent;
import fr.nwg.kingdomwar.component.InputComponent;
import fr.nwg.kingdomwar.component.RectangleComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class EntityFactory {
    public static void createTowerEntity(KingdomWarWorld world) {
        Entity tower = world.createEntity();
        tower.addComponent(new DrawingComponent(255, 255, 255, 1));
        tower.addComponent(new RectangleComponent(50, 50, 50, 50));
        tower.addComponent(new InputComponent());
        tower.addToWorld();
    }
}
