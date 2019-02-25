package parser_private.commands.turtle_commands;

import parser_private.ActionCommand;
import parser_private.Command;

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
        //new ForwardCall(myDistance.execute()).call();
        return 0;
    }
}
