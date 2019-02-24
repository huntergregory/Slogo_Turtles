package parser.commands.control_commands;

import parser.Command;
import parser.EvalCommand;

import java.util.List;

public class RepeatCommand extends EvalCommand {

    private Command myTotalIter;
    private Command myList;
    private Command myCurrentIter; // :repcount, TODO make an iterable command? i.e. each execution it iterates itself?

    public RepeatCommand(List<Command> params) {
        super(params);
        myTotalIter = params.get(0);
        myList = params.get(1);
        myCurrentIter = null; // TODO call to parser to store, update :repcount as user defined variable but make sure to save/load previous value
    }

    @Override
    public double runCommand() {
        for (int i = 0; i < myTotalIter.execute() - 1; i++) {
            myList.execute();
        }
        return myList.execute(); // Final iteration
    }
}
