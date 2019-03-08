package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;
import ui_private.turtles.LineStroke;

import java.util.ArrayList;

public class LineStrokeSelector extends Selector {
    private static final ObservableList STROKES = constructList();

    private static ObservableList constructList() {
        ArrayList<String> strokes = new ArrayList<>();
        for (LineStroke stroke : LineStroke.values())
            strokes.add(stroke.getText());
        return FXCollections.observableArrayList(strokes);
    }

    public LineStrokeSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return STROKES;
    }

    @Override
    protected void handleItemSelected(String item) {
        var stroke = getLineStroke(item);
        if (stroke != null)
            return; //TurtleManager.getInstance().setStroke(stroke) //FIXME
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    private LineStroke getLineStroke(String item) {
        for (LineStroke stroke : LineStroke.values()) {
            if (stroke.getText().equals(item))
                return stroke;
        }
        return null;
    }
}
