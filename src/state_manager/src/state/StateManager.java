package state;

/**
 * Class to keep track of the state of a simulation
 * @author David Miron
 */
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
        myPaletteManager = new PaletteManager();
        myTurtleManager = new TurtleManager(myVariables, myPaletteManager);
        myCommands = new GlobalCommands();
        myCommandHistory = new CommandHistory();
        myInputTranslator = new InputTranslator();
        myBackgroundColor = myPaletteManager.getDefaultBackgroundColor();
    }

    /**
     * Get the turtle manager
     * @return The turtle manager
     */
    public TurtleManager getTurtleManager() {
        return myTurtleManager;
    }

    /**
     * Get the global variables
     * @return The global variables
     */
    public GlobalVariables getVariables() {
        return myVariables;
    }

    /**
     * Get the global commands
     * @return The global commands
     */
    public GlobalCommands getCommands() {
        return myCommands;
    }

    /**
     * Get the history of commands
     * @return The command history
     */
    public CommandHistory getCommandHistory() {
        return myCommandHistory;
    }

    /**
     * Get the input translator
     * @return The input translator
     */
    public InputTranslator getInputTranslator() {
        return myInputTranslator;
    }

    /**
     * Get the palette manager
     * @return The palette manager
     */
    public PaletteManager getPaletteManager() {
        return myPaletteManager;
    }

    /**
     * Set the background color
     * @param index The index of the color to use
     */
    public void setBackgroundColor(int index) {
        myBackgroundColor.getColorProperty().set(myPaletteManager.getPalette(index).getColor());
    }
}
