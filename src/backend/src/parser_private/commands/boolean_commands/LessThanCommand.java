package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class LessThanCommand extends TwoParamBoolCommand {

    public LessThanCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return (myExpression1.execute() < myExpression2.execute()) ? 1 : 0;
    }
}
