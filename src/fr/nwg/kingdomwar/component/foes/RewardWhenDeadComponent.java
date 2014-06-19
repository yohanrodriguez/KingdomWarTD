package fr.nwg.kingdomwar.component.foes;

import com.artemis.Component;

/**
 * Created by eptwalabha on 18/06/2014.
 */
public class RewardWhenDeadComponent extends Component {
    public int points = 0;
    public int thunes = 50;

    public RewardWhenDeadComponent(int points) {
        this.points = points;
    }
}
