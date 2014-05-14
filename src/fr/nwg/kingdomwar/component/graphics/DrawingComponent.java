package fr.nwg.kingdomwar.component.graphics;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DrawingComponent extends Component {
    
    public Color color;
    public ShapeRenderer.ShapeType shapeType;

    public DrawingComponent(float red, float green, float blue, float alpha) {
        color = new Color(red, green, blue, alpha);
    }
}
