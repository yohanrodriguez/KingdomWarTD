package fr.nwg.kingdomwar.component.listener;

import com.artemis.Component;
import fr.nwg.kingdomwar.listener.Listener;

public abstract class EventListenerComponent extends Component{
    protected Listener listener;

    public EventListenerComponent(Listener listener) {
        this.listener = listener;
    }
}
