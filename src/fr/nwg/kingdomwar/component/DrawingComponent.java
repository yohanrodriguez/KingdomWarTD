package fr.nwg.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public class DrawingComponent extends Component {
    
    public Color color;
    
    public DrawingComponent(float red, float green, float blue, float alpha) {
        color = new Color(red, green, blue, alpha);
    }
}
