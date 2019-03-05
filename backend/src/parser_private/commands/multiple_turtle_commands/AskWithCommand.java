package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import parser_private.commands.control_commands.ListCommand;
import state_public.CommandInter;

import java.util.ArrayList;
import java.util.List;

public class AskWithCommand extends MultipleTurtlesCommand {

    private CommandInter myCondition;
    private ListCommand myCommands;

    public AskWithCommand(List<CommandInter> params) {
        super(params);
        myCondition = params.get(0);
        myCommands = (ListCommand)params.get(1);
    }

    @Override
    public double execute() {
        List<Integer> valid_ids = myStateManager.getTurtleManager()
                                    .getTurtlesWithCondition(turtle -> myCondition.execute() != 0);
        return ask(valid_ids, myCommands);
    }
}
