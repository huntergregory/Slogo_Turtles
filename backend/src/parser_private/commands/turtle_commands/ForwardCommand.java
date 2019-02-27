package parser_private.commands.turtle_commands;

import parser_private.ActionCommand;
import parser_private.Command;
import parser_public.StateList;
import parser_private.TurtleState;

import java.util.List;

public class ForwardCommand extends ActionCommand {

    private Command myDistance;

    public ForwardCommand(List<Command> params) {
        super(params);
        this.myDistance = params.get(0);
    }

    @Override
    public double runCommand() {
        System.out.println("Executing Forward with distance " + myDistance.execute());
        move();
        return Math.abs(myDistance.execute());
    }

    private void move() {
        TurtleState newState = new TurtleState(StateList.getInstance().getLastState()); // Copy last state to new state
        double heading = Math.toRadians(newState.getHeading());
        newState.setX(newState.getX() + Math.sin(heading) * myDistance.execute());
        newState.setY(newState.getY() - Math.cos(heading) * myDistance.execute());

        StateList.getInstance().addState(newState);
    }
}
