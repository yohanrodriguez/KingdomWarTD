package fr.nwg.kingdomwar.component.foes;

import com.artemis.Component;

/**
 * Created by eptwalabha on 18/06/2014.
 */
public class PenaltyWhenWinComponent extends Component {
    public int lifePoints = 0;

    public PenaltyWhenWinComponent(int lifePoints) {
        this.lifePoints = lifePoints;
    }
}
