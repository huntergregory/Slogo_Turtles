package entry;

import javafx.application.Application;
import javafx.stage.Stage;
import parser_public.CommandParser;
import state_public.ParserException;
import state_public.StateManager;
import ui_public.UIMain;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private List<UIMain> myWorkspaces;

    public Main() {
        myWorkspaces = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        createWorkspace();
    }

    private void createWorkspace() {
        Stage stage = new Stage();
        try {
            StateManager stateManager = new StateManager();
            CommandParser backend = new CommandParser(stateManager);
            UIMain workspace = new UIMain(myWorkspaces.size(), stage, backend, stateManager);
            myWorkspaces.add(workspace);
        } catch (ParserException e) {
            e.printStackTrace(); //FIXME
        }
    }

}
