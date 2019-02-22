package parser.commands;

import control.frontendapi.move_distance_calls.ForwardCall;
import parser.ActionCommand;
import parser.Command;

import java.util.List;

public class ForwardCommand extends ActionCommand {

    private Command distance;

    public ForwardCommand(List<Command> params) {
        this.distance = params.get(0);
    }

    @Override
    public double execute() {
        System.out.println("Executing Forward with distance " + distance.execute());
        new ForwardCall(distance.execute()).call();
        return 0;
    }

}
