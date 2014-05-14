package fr.nwg.kingdomwar.component.graphics;

import com.artemis.Component;

public class DrawingTypeComponent extends Component {
    public enum DrawingType {
        RECT,
        ELLIPSE, CIRCLE
    }

    public DrawingType drawingType;

    public DrawingTypeComponent(DrawingType drawingType) {
        this.drawingType = drawingType;
    }
}
