package fr.nwg.kingdomwar.component;

import com.artemis.Component;

public class FiringRateComponent extends Component {
    public float delay;

    public FiringRateComponent(int delay) {
        this.delay = (delay < 0) ? 200 : delay;
    }
}
