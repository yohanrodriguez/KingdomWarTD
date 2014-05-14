package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.component.misc.ButtonComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;

public class ItemMenuFactory {

    public static Entity createTowerMenuButton(World world) {

        Entity button = world.createEntity();
        button.addComponent(new DrawingComponent(1, 1, 1, 1));
        button.addComponent(new PositionComponent(760, 560));
        button.addComponent(new SizeComponent(30, 30));
        button.addComponent(new ButtonComponent());
        button.addToWorld();

        return button;

    }
}
