package parser_private.commands.control_commands;

import parser_private.Command;
import parser_private.commands.math_commands.ConstantCommand;
import state.ICommand;
import state.IUserCommand;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand extends Command {

    public GroupCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        ICommand model = mySubCommands.get(0);
        List<ICommand> commandList = new ArrayList<>();
        List<ICommand> nextParams = new ArrayList<>();
        if (model.size() == 0) {
            commandList = mySubCommands;
        }
        else if (myStateManager.getInputTranslator().hasMultiInputGrouping(model.getClass().getName())) {
            multiInputBuild(model, nextParams, commandList);
        }
        else {
            sequentialBuild(model, nextParams, commandList);
        }
        ListCommand newCommandList = new ListCommand(commandList);
        newCommandList.injectStateManager(myStateManager);
        return newCommandList.execute();
    }

    private void sequentialBuild(ICommand model, List<ICommand> nextParams, List<ICommand> commandList) {
        commandList.add(model);
        int numParams = model.size();
        int countAbs = 1;
        int countPar = 0;
        while (countAbs < mySubCommands.size()) {
            while (countPar < numParams) {
                if (countAbs < mySubCommands.size())
                    nextParams.add(mySubCommands.get(countAbs));
                else
                    nextParams.add(new ConstantCommand(0)); // Fills in for missing params at end
                countAbs++;
                countPar++;
            }
            countPar = 0;
            addCommand(model, nextParams, commandList);
            nextParams = new ArrayList<>();
        }
    }

    private void multiInputBuild(ICommand model, List<ICommand> nextParams, List<ICommand> commandList) {
        nextParams.addAll(model.getParams());
        nextParams.addAll(mySubCommands.subList(1, mySubCommands.size()));
        addCommand(model, nextParams, commandList);
    }

    private void addCommand(ICommand model, List<ICommand> nextParams, List<ICommand> commandList) {
        try {
            ICommand newCommand;
            if (model instanceof IUserCommand) {
                newCommand = ((IUserCommand) model).getNewInstance();
                ((IUserCommand) newCommand).assignParams(nextParams);
            }
            else {
                newCommand = model.getClass().getConstructor(List.class).newInstance(nextParams);
            }
            commandList.add(newCommand);
        } catch (Exception e) {
            //handle error
        }
    }
}
