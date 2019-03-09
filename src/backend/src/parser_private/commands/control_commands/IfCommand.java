package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class IfCommand extends Command {

    private ICommand myExpression;
    private ICommand myBody;

    public IfCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double execute() {
        if (myExpression.execute() != 0) {
            return myBody.execute();
        }
        return 0;
    }
}
