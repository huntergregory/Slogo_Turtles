package entry;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import parser_public.CommandParser;
import state_public.ParserException;
import state_public.StateManager;
import ui_public.UIMain;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static final String NEW_WORKSPACE_KEY = "n";

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
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (k) -> {
            if (k.getText().equals(NEW_WORKSPACE_KEY)) {
                if (k.isMetaDown()) {
                    createWorkspace();
                }
            }
        });
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
