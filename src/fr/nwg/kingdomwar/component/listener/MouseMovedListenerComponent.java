package fr.nwg.kingdomwar.component.listener;

import com.artemis.Component;
import fr.nwg.kingdomwar.listener.Listener;
import fr.nwg.kingdomwar.listener.MouseMovedListener;
import fr.nwg.kingdomwar.listener.UpdatePositionComponentListener;

public class MouseMovedListenerComponent extends Component {
    private final MouseMovedListener listener;

    public MouseMovedListenerComponent(MouseMovedListener listener) {
        this.listener = listener;
    }

    public void run(int x, int y) {
        listener.run(x, y);
    }
}
