package fr.nwg.kingdomwar.component.tower;

import com.artemis.Component;

public class FiringRateComponent extends Component {
    public float delay;
    private float initialDelay;

    public FiringRateComponent(int delay) {
        this.delay = (delay < 0) ? 100 : delay;
        this.initialDelay = this.delay;
    }

    public void resetDelay() {
        this.delay = this.initialDelay;
    }
}
