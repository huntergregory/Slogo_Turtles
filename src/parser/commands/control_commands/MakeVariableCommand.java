package parser.commands.control_commands;

import parser.Command;
import parser.EvalCommand;
import java.util.List;

public class MakeVariableCommand extends EvalCommand {

    private Command myContents;

    public MakeVariableCommand(List<Command> params) {
        myContents = params.get(0);
    }

    @Override
    public double runCommand() {
        return myContents.execute();
    }
}
