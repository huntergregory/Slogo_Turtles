package parser.commands.turtle_commands;

import parser.ActionCommand;
import parser.Command;
import java.util.List;

public class ForwardCommand extends ActionCommand {

    private Command myDistance;

    public ForwardCommand(List<Command> params) {
        super(params);
        this.myDistance = params.get(0);
    }

    @Override
    public double runCommand() {
        System.out.println("Executing Forward with myDistance " + myDistance.execute());
        //new ForwardCall(myDistance.execute()).call();
        return 0;
    }
}
