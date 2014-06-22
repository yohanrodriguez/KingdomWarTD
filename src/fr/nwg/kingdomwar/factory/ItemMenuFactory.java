package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import fr.nwg.kingdomwar.component.misc.ButtonComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.misc.DrawingTextComponent;
import fr.nwg.kingdomwar.component.misc.TimeToLiveComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.component.physic.VelocityComponent;

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

    public static Entity createPositivePointIndicator(World world, PositionComponent positionComponent, String text) {
        Entity indicator = createPointIndicator(world, positionComponent);
        indicator.addComponent(new DrawingTextComponent(text, Color.GREEN));
        setIndicatorMovement(indicator, 0, 10f, 10f);
        indicator.addComponent(new TimeToLiveComponent(1000));
        indicator.addToWorld();
        return indicator;
    }

    public static Entity createNegativePointIndicator(World world, PositionComponent positionComponent, String text) {
        Entity indicator = createPointIndicator(world, positionComponent);
        indicator.addComponent(new DrawingTextComponent(text, Color.RED));
        setIndicatorMovement(indicator, 0, 10f, 10f);
        indicator.addComponent(new TimeToLiveComponent(1000));
        indicator.addToWorld();
        return indicator;
    }

    private static Entity createPointIndicator(World world, PositionComponent positionComponent) {
        Entity indicator = world.createEntity();
        PositionComponent position = new PositionComponent(positionComponent.getRealPositionX(), positionComponent.getRealPositionY());
        indicator.addComponent(position);
        return indicator;
    }

    private static Entity setIndicatorMovement(Entity indicator, float velocityX, float velocityY, float speed) {
        indicator.addComponent(new VelocityComponent(velocityX, velocityY));
        indicator.addComponent(new SpeedComponent(speed));
        return indicator;
    }
}
