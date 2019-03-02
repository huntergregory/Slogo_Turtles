package state_public;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateManager {

    public static final List<Palette> defaultPalettes = Collections.unmodifiableList(
        new ArrayList<>() {{
            add(new Palette(0));
        }}
    );

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
        myPalettes = new ArrayList<>();
        myPalettes.addAll(defaultPalettes);
        myBackgroundColor = myPalettes.get(0);
    }



}
