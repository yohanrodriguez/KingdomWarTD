package fr.nwg.kingdomwar.component.misc;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by eptwalabha on 22/06/2014.
 */
public class DrawingTextComponent extends Component {

    public String textToDisplay;
    public Color color;

    public DrawingTextComponent(String text, Color color) {
        textToDisplay = text;
        this.color = color;
    }
}
