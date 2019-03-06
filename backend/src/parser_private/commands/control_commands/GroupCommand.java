package parser_private.commands.control_commands;

import parser_private.Command;
import parser_private.commands.math_commands.ConstantCommand;
import state_public.CommandInter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GroupCommand extends Command {

    public GroupCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double execute() {
        CommandInter model = mySubCommands.get(0);
        List<CommandInter> commandList = new ArrayList<>();
        List<CommandInter> nextParams = new ArrayList<>();
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

    private void sequentialBuild(CommandInter model, List<CommandInter> nextParams, List<CommandInter> commandList) {
        commandList.add(model);
        int numParams = model.size();
        int countAbs = 1;
        int countPar = 0;
        while (countAbs < mySubCommands.size()) {
            while (countPar < numParams) {
                if (countAbs < mySubCommands.size()) {
                    nextParams.add(mySubCommands.get(countAbs));
                    countAbs++;
                    countPar++;
                }
                else {
                    nextParams.add(new ConstantCommand(0)); // Fills in for missing params at end
                    countAbs++;
                }
            }
            countPar = 0;
            addCommand(model, nextParams, commandList);
            nextParams = new ArrayList<>();
        }
    }

    private void multiInputBuild(CommandInter model, List<CommandInter> nextParams, List<CommandInter> commandList) {
        nextParams.addAll(model.getParams());
        nextParams.addAll(mySubCommands.subList(1, mySubCommands.size()));
        addCommand(model, nextParams, commandList);
    }

    private void addCommand(CommandInter model, List<CommandInter> nextParams, List<CommandInter> commandList) {
        try {
            CommandInter newCommand = model.getClass().getConstructor(List.class).newInstance(nextParams);
            commandList.add(newCommand); //TODO account for user defined commands
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            //handle error
        }
    }
}
