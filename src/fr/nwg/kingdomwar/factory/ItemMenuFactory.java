package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.component.ButtonComponent;
import fr.nwg.kingdomwar.component.DrawingComponent;
import fr.nwg.kingdomwar.component.ui.ListenerComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.component.SizeComponent;
import fr.nwg.kingdomwar.interfaces.CreateTowerListener;


public class ItemMenuFactory {

    public static Entity createTowerMenuButton(World world) {

        Entity button = world.createEntity();
        button.addComponent(new DrawingComponent(1, 1, 1, 1));
        button.addComponent(new PositionComponent(760, 560));
        button.addComponent(new SizeComponent(30, 30));
        button.addComponent(new ButtonComponent());
        button.addComponent(new ListenerComponent(new CreateTowerListener()));
        button.addToWorld();

        return button;

    }
}
