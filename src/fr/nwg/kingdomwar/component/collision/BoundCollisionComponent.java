package fr.nwg.kingdomwar.component.collision;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

/**
 * User: eptwalabha
 * Date: 21/02/14
 * Time: 00:22
 */
public class BoundCollisionComponent extends Component {

    public PositionComponent position;
    public float width;
    public float height;

    public BoundCollisionComponent(PositionComponent position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(BoundCollisionComponent boundCollisionComponent) {

        float xA = position.getRealPositionX();
        float yA = position.getRealPositionY();
        float xB = boundCollisionComponent.position.getRealPositionX();
        float yB = boundCollisionComponent.position.getRealPositionY();

        return  (xA + width >= xB) && (xA <= xB + boundCollisionComponent.width) &&
                (yA - height <= yB) && (yA >= yB - boundCollisionComponent.height);
    }

    public float getCoefficient(BoundCollisionComponent boundCollisionComponentB) {

        float posXA = position.getRealPositionX() + width / 2f;
        float posYA = position.getRealPositionY() - height / 2f;

        float posXB = boundCollisionComponentB.position.getRealPositionX() + boundCollisionComponentB.width / 2f;
        float posYB = boundCollisionComponentB.position.getRealPositionY() - boundCollisionComponentB.height / 2f;

        if (posXB - posXA == 0)
            return 10f;

        return (posYB - posYA) / (posXB - posXA);
    }

    public float getCoefficient() {
        return height / width;
    }
}
