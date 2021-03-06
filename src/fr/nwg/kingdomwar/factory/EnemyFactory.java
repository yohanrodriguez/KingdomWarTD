package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.foes.LifeComponent;
import fr.nwg.kingdomwar.component.foes.PenaltyWhenWinComponent;
import fr.nwg.kingdomwar.component.foes.RewardWhenDeadComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingSpriteComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingTypeComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class EnemyFactory extends EntityFactory {

    public static Entity createBasicEnemy(World world) {
        PositionComponent position = new PositionComponent();
        DrawingComponent drawingComponent = new DrawingComponent(0, .7f, .7f, 1);

        Entity basicEnemy = world.createEntity();
        basicEnemy.addComponent(position);
        basicEnemy.addComponent(new RewardWhenDeadComponent(50, 10));
        basicEnemy.addComponent(new PenaltyWhenWinComponent(5));
        basicEnemy.addComponent(new LifeComponent(150, 150));
        basicEnemy.addComponent(new SpeedComponent(40f));
        basicEnemy.addComponent(new DrawingTypeComponent(DrawingTypeComponent.DrawingType.ELLIPSE));
        SizeComponent size = new SizeComponent(20,20);
        basicEnemy.addComponent(size);

        basicEnemy.addComponent(new CircleCollisionComponent(position, size.width));
        basicEnemy.addComponent(drawingComponent);
        basicEnemy.addComponent(new RailComponent(KingdomWarData.getInstance().getRail(Constants.Rails.GROUND_001)));
        basicEnemy.addComponent(new DestinationReachedComponent());

        basicEnemy.addComponent(new DrawingSpriteComponent(KingdomWarData.getInstance().getSprite(Constants.Textures.MONSTER_001), position, 40, 40));

        GroupManager manager = world.getManager(GroupManager.class);
        manager.add(basicEnemy, Constants.Groups.FOES);
        basicEnemy.addToWorld();
        return basicEnemy;
    }

    public static Entity createToughEnemy(World world) {
        PositionComponent position = new PositionComponent();
        DrawingComponent drawingComponent = new DrawingComponent(0, .7f, .7f, 1);

        Entity basicEnemy = world.createEntity();
        basicEnemy.addComponent(position);
        basicEnemy.addComponent(new RewardWhenDeadComponent(100, 30));
        basicEnemy.addComponent(new PenaltyWhenWinComponent(10));
        basicEnemy.addComponent(new LifeComponent(300, 300));
        basicEnemy.addComponent(new SpeedComponent(30f));
        basicEnemy.addComponent(new DrawingTypeComponent(DrawingTypeComponent.DrawingType.ELLIPSE));
        SizeComponent size = new SizeComponent(30, 30);
        basicEnemy.addComponent(size);

        basicEnemy.addComponent(new CircleCollisionComponent(position, size.width));
        basicEnemy.addComponent(drawingComponent);
        basicEnemy.addComponent(new RailComponent(KingdomWarData.getInstance().getRail(Constants.Rails.GROUND_001)));
        basicEnemy.addComponent(new DestinationReachedComponent());

        basicEnemy.addComponent(new DrawingSpriteComponent(KingdomWarData.getInstance().getSprite(Constants.Textures.MONSTER_003), position, 60, 60));

        GroupManager manager = world.getManager(GroupManager.class);
        manager.add(basicEnemy, Constants.Groups.FOES);
        basicEnemy.addToWorld();
        return basicEnemy;
    }
}
