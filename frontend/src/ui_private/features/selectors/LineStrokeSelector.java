package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui_private.turtles.LineStroke;

import java.util.ArrayList;

public class LineStrokeSelector extends Selector<String> {
    private static final ObservableList STROKES = constructList();
    private static final String TITLE = "Line Stroke";

    private static ObservableList constructList() {
        ArrayList<String> strokes = new ArrayList<>();
        for (LineStroke stroke : LineStroke.values())
            strokes.add(stroke.getText());
        return FXCollections.observableArrayList(strokes);
    }


    @Override
    protected ObservableList getItemList() {
        return null;
    }

    @Override
    protected void handleItemSelected(String item) {
        var stroke = getLineStroke(item);
        if (stroke != null)
            return; //myTurtleDisplay.setStroke(stroke) //FIXME
    }

    private LineStroke getLineStroke(String item) {
        for (LineStroke stroke : LineStroke.values()) {
            if (stroke.getText().equals(item))
                return stroke;
        }
        return null;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
