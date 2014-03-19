package fr.nwg.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;

public class RectangleComponent extends Component{

    public int width;
    public int height;
    public int x;
    public int y;

    public RectangleComponent(int width, int height, int positionX, int positionY) {
        this.width = width;
        this.height = height;
        this.x = positionX;
        this.y = positionY;
    }
}
