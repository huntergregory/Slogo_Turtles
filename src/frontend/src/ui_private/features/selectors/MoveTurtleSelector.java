package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import ui_private.displays.CommandTerminal;

public class MoveTurtleSelector extends Selector {
    private static final ObservableList MOVEMENTS = FXCollections.observableArrayList("", "FD", "BK","RT","LT");

    public MoveTurtleSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return MOVEMENTS;
    }

    @Override
    protected void handleItemSelected(String item) {
        String myCommand = "";
        if (item.equals("FD")) {
            myCommand = "fd 50";
        }
        else if (item.equals("BK")) {
            myCommand = "bk 50";
        }
        else if (item.equals("RT")) {
            myCommand = "rt 90 fd 50";
        }
        else if (item.equals("LT")) {
            myCommand = "lt 90 fd 50";
        }/*
        try {
            CommandParser.getInstance().parseAndRun(myCommand);
        }
        catch (ParserException e) {

        }*/ //FIXME
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}
