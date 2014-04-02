package fr.nwg.kingdomwar.component;

import com.artemis.Component;

    public class VelocityComponent extends Component {

    public float x;
    public float y;

    public VelocityComponent(PositionComponent source, PositionComponent destination) {

        float deltaX = destination.getRealPositionX() - source.getRealPositionX();
        float deltaY = destination.getRealPositionY() - source.getRealPositionY();
        float hypotenuse = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        x = deltaX / hypotenuse;
        y = deltaY / hypotenuse;
    }
}
