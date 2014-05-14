package fr.nwg.kingdomwar.component.graphics;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

public class SizeComponent extends Component{

    public int width;
    public int height;

    public SizeComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
