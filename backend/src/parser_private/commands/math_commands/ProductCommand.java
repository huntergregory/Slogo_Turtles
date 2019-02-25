package parser_private.commands.math_commands;

import parser_private.Command;

import java.util.List;

public class ProductCommand extends TwoParamMathCommand {

    public ProductCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return myExpression1.execute() * myExpression2.execute();
    }
}