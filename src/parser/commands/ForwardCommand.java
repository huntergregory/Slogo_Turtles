package parser.commands;

import control.frontendapi.MoveCall;
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
        new MoveCall(distance.execute()).call();
        return 0;
    }

}
