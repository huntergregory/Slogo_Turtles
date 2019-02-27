package parser_public;

import java.util.ArrayList;
import java.util.List;

public class StateList {

    private List<TurtleState> myPendingStates;
    private double myDisplayWidth;
    private double myDisplayHeight;

    private static StateList instance;

    private StateList() {
        myPendingStates = new ArrayList<>();
    }

    public static StateList getInstance() {
        if (instance == null)
            instance = new StateList();
        return instance;
    }

    public void initialize(double dispHeight, double dispWidth) {
        this.myDisplayHeight = dispHeight;
        this.myDisplayWidth = dispWidth;
        myPendingStates.add(new TurtleState());
    }

    public void addState(TurtleState newState) {
        myPendingStates.add(newState);
    }

    public List<TurtleState> getList() {
        return myPendingStates;
    }

    public TurtleState getLastState() { //TODO for multiple turtles this could accept a TurtleID param
        return myPendingStates.get(myPendingStates.size() - 1);
    }

    double getDispWidth() {
        return myDisplayWidth;
    }

    double getDispHeight() {
        return myDisplayHeight;
    }
}
