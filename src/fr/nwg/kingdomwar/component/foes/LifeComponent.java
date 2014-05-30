package fr.nwg.kingdomwar.component.foes;

import com.artemis.Component;

public class LifeComponent extends Component {

    public float maxLife;
    public float life;

    public LifeComponent(float life) {
        this.life = life;
        maxLife = life;
    }

    public LifeComponent(float life, float maxLife) {
        this.life = life;
        this.maxLife = maxLife;
    }
}
