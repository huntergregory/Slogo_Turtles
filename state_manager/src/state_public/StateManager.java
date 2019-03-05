package state_public;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateManager {



    private TurtleManager myTurtleManager;
    private GlobalCommands myCommands;
    private GlobalVariables myVariables;
    private CommandHistory myCommandHistory;
    private InputTranslator myInputTranslator;
    private PaletteManager myPaletteManager;
    private Palette myBackgroundColor;

    public StateManager() throws ParserException {
        myVariables = new GlobalVariables();
        myTurtleManager = new TurtleManager(myVariables, myPaletteManager);
        myCommands = new GlobalCommands();
        myCommandHistory = new CommandHistory();
        myInputTranslator = new InputTranslator();
        myPaletteManager = new PaletteManager();
        myBackgroundColor = myPaletteManager.getDefaultBackgroundColor();
    }

    public TurtleManager getTurtleManager() {
        return myTurtleManager;
    }

    public GlobalVariables getVariables() {
        return myVariables;
    }

    public GlobalCommands getCommands() {
        return myCommands;
    }

    public CommandHistory getCommandHistory() {
        return myCommandHistory;
    }

    public Palette getBackgroundColor() {
        return myBackgroundColor;
    }

    public InputTranslator getInputTranslator() {
        return myInputTranslator;
    }

    public PaletteManager getPaletteManager() {
        return myPaletteManager;
    }

}
