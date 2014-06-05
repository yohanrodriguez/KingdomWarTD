package fr.nwg.kingdomwar.component.collision;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

/**
 * User: Eptwalabha
 * Date: 05/06/2014
 * Time: 23:12
 */
public class CircleCollisionComponent extends Component {

    public PositionComponent position;
    public float radius;

    public CircleCollisionComponent(PositionComponent position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    public boolean intersects(CircleCollisionComponent circleCollisionComponent) {
        float deltaX = circleCollisionComponent.position.getRealPositionX() - position.getRealPositionX();
        float deltaY = circleCollisionComponent.position.getRealPositionY() - position.getRealPositionY();
        return (deltaX * deltaX + deltaY * deltaY) <= (radius * radius + circleCollisionComponent.radius + circleCollisionComponent.radius);
    }
}
