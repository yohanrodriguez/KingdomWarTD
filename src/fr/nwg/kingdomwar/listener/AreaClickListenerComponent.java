package fr.nwg.kingdomwar.listener;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;

public class AreaClickListenerComponent extends Component {

    public PositionComponent position;
    public SizeComponent size;

    public AreaClickListenerComponent(PositionComponent position, SizeComponent size) {
        this.position = position;
        this.size = size;
    }

    public boolean isClicked(int x, int y) {
        return  x >= position.getRealPositionX() &&
                x < position.getRealPositionX() + size.width &&
                y >= position.getRealPositionY() &&
                y < position.getRealPositionY() + size.height;
    }
}