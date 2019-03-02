package state_public;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class StateManager {

    private List<Turtle> myTurtles;

    private GlobalVariables myVariables;
    private GlobalCommands myCommands;
    private CommandHistory myCommandHistory;
    private Palette myBackgroundColor;
    private List<Palette> myPalettes;

    public StateManager() {
        myTurtles = new ArrayList<>();
        myVariables = new GlobalVariables();
        myCommands = new GlobalCommands();
        myCommandHistory = new CommandHistory();
        myBackgroundColor = new Palette();
        myPalettes = new ArrayList<Palette>();
    }



}
