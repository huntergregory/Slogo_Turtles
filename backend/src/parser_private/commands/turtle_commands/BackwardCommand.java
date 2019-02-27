package parser_private.commands.turtle_commands;

import parser_private.Command;
import parser_public.StateList;
import parser_public.TurtleState;

import java.util.List;

public class BackwardCommand extends Command {
    private Command myDistance;

    public BackwardCommand(List<Command> params) {
        super(params);
        this.myDistance = params.get(0);
    }

    @Override
    public double runCommand() {
        System.out.println("Executing Backward with distance " + myDistance.execute());
        move();
        return Math.abs(myDistance.execute());
    }

    private void move() { //TODO abstract duplicate code between this and forwardcommand
        TurtleState newState = new TurtleState(StateList.getInstance().getLastState()); // Copy last state to new state
        double heading = Math.toRadians(newState.getHeading());
        newState.setX(newState.getX() + Math.sin(heading) * myDistance.execute() * -1);
        newState.setY(newState.getY() - Math.cos(heading) * myDistance.execute() * -1);

        StateList.getInstance().addState(newState);
    }
}
