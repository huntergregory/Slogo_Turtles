package state_public;

public class StateManager {



    private TurtleManager myTurtleManager;
    private GlobalCommands myCommands;
    private GlobalVariables myVariables;
    private CommandHistory myCommandHistory;
    private InputTranslator myInputTranslator;
    private PaletteManager myPaletteManager;
    private Palette myUIColor;

    public StateManager() throws ParserException {
        myVariables = new GlobalVariables();
        myPaletteManager = new PaletteManager();
        myTurtleManager = new TurtleManager(myVariables, myPaletteManager);
        myCommands = new GlobalCommands();
        myCommandHistory = new CommandHistory();
        myInputTranslator = new InputTranslator();
        myUIColor = myPaletteManager.getDefaultBackgroundColor();
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
        return myUIColor;
    }

    public InputTranslator getInputTranslator() {
        return myInputTranslator;
    }

    public PaletteManager getPaletteManager() {
        return myPaletteManager;
    }

}
