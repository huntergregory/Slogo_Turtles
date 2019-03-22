package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class ProductCommand extends MultiParamMathCommand {

    public ProductCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the product of two parameters
     * @return The product
     */
    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double rtn = myExpressions.get(0).execute();
        for (int i = 1; i < myExpressions.size(); i++) {
            rtn *= myExpressions.get(i).execute();
        }
        return rtn;
    }
}