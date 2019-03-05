package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import parser_public.CommandParser;

public class MoveTurtleSelector extends Selector {
    private static final String TITLE = "Move Turtle";
    private static final ObservableList MOVEMENTS = FXCollections.observableArrayList("", "FD", "BK","RT","LT");

    @Override
    protected ObservableList getItemList() {
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
    protected String getLabelText() {
        return TITLE;
    }
}
