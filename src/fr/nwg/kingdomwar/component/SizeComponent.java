package fr.nwg.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;

public class SizeComponent extends Component{

    public int width;
    public int height;

    public SizeComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
