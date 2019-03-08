package state_public;

import java.awt.*;

public class StateManager {


    private TurtleManager myTurtleManager;
    private GlobalCommands myCommands;
    private GlobalVariables myVariables;
    private CommandHistory myCommandHistory;
    private InputTranslator myInputTranslator;
    private PaletteManager myPaletteManager;
    private Palette myBackgroundColor;
    private Palette myPenColor;

    public StateManager() throws ParserException {
        myVariables = new GlobalVariables();
        myPaletteManager = new PaletteManager();
        myTurtleManager = new TurtleManager(myVariables, myPaletteManager);
        myCommands = new GlobalCommands();
        myCommandHistory = new CommandHistory();
        myInputTranslator = new InputTranslator();
        myBackgroundColor = myPaletteManager.getDefaultBackgroundColor();
        myPenColor = myPaletteManager.getDefaultPenColor();
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

    public void setBackgroundColor(int index) {
        myBackgroundColor = myPaletteManager.getPalette(index);
    }

}
