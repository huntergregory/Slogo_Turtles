package parser_private;

import parser_private.commands.control_commands.ListCommand;

public class StoredUserDefinedCommand {

    private ListCommand myArguments;
    private ListCommand myBody;

    public StoredUserDefinedCommand(ListCommand myArguments, ListCommand myBody) {
        this.myArguments = myArguments;
        this.myBody = myBody;
    }

    public double run(ListCommand params) {
        int count = 0;
        for ()
        myArguments.doForEachCommand((cmd) -> {

        });
    }
}
