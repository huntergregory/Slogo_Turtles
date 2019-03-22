package parser_private.commands.multiple_turtle_commands;

import parser_private.commands.control_commands.ListCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class AskWithCommand extends MultipleTurtlesCommand {

    private ICommand myCondition;
    private ListCommand myCommands;

    public AskWithCommand(List<ICommand> params) {
        super(params);
        myCondition = params.get(0);
        myCommands = (ListCommand)params.get(1);
    }

    /**
     * Run a set of commands who satisfy some condition
     * @return The result of the last executed command
     */
    @Override
    public double execute() {
        List<Integer> valid_ids = myStateManager.getTurtleManager()
                                    .getTurtlesWithCondition(turtle -> myCondition.execute() != 0);
        return ask(valid_ids, myCommands);
    }
}
