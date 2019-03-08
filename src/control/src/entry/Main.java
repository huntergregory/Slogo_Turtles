package entry;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import parser_public.CommandParser;
import state_public.ParserException;
import state_public.StateManager;
import ui_public.FeatureType;
import ui_public.UIBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 750;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";
    private static final String NEW_WORKSPACE_KEY = "n";

    private Stage myStage;
    private Scene myScene;
    private BorderPane myBorderPane;
    private ToolBar myToolBar;
    private TabPane myTabPane;
    private List<UIBuilder> myWorkspaces;

    public Main() {
        myWorkspaces = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        myStage = stage;
        initStage();
        createWorkspace();
        showStage();
        UIBuilder.addStyle(myScene);
    }

    private void initStage() {
        myTabPane = new TabPane();
        myTabPane.getTabs().addListener((ListChangeListener) c -> enforceTabPolicy());
                /*new ListChangeListener<Tab>() {
            //couldn't do a lambda because the listener class would be ambiguous
            @Override
            public void onChanged(Change<? extends Tab> change) {
                enforceTabPolicy();
            }
        });*/
        createToolbar();

        myBorderPane = new BorderPane();
        myBorderPane.setTop(myToolBar);
        myBorderPane.setCenter(myTabPane);
    }

    private void enforceTabPolicy() {
        boolean oneTabLeft = myTabPane.getTabs().size() == 1;
        System.out.println(oneTabLeft);
        var policy = (oneTabLeft) ? TabPane.TabClosingPolicy.UNAVAILABLE : TabPane.TabClosingPolicy.SELECTED_TAB;
        myTabPane.setTabClosingPolicy(policy);
    }

    private void createToolbar() {
        myToolBar = new ToolBar();
        makeButton("New", e -> createWorkspace());
        makeButton("Load", e -> {}); //FIXME
        makeButton("Customize...", e -> showFeatureCheckboxes());
    }

    private void makeButton(String text, EventHandler<ActionEvent> handler) {
        var button = new Button(text);
        button.setOnAction(handler);
        myToolBar.getItems().add(button);
    }

    private void showFeatureCheckboxes() {
        int currentTab = myTabPane.getSelectionModel().getSelectedIndex();
        UIBuilder currentWorkspace = myWorkspaces.get(currentTab);
        //TODO: add checkboxes and listeners
        //TODO: keep track of current features and their order
    }

    private void showStage() {
        myScene = new Scene(myBorderPane, WIDTH, HEIGHT, BACKGROUND);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
    }

    private void createWorkspace() {

        try {
            StateManager stateManager = new StateManager();
            CommandParser backend = new CommandParser(stateManager);
            UIBuilder workspace = new UIBuilder(myWorkspaces.size(), backend, stateManager, WIDTH, HEIGHT);
            addTab(workspace);
            myWorkspaces.add(workspace);
        } catch (ParserException e) {
            e.printStackTrace(); //FIXME
        }
    }

    private void addToolBar() {
        ToolBar toolBar = new ToolBar();

        Button button1 = new Button("Button 1");
        toolBar.getItems().add(button1);
    }

    private void addTab(UIBuilder workspace) {
        Tab tab = new Tab("Tab " + 1); //TODO fix name
        tab.setContent(workspace.getContent());
        myTabPane.getTabs().add(tab);
    }

    private void handleKeyInput(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (keyCode.equals(KeyCode.N))
            return; //FIXME

        /*stage.addEventHandler(KeyEvent.KEY_PRESSED, (k) -> {
            if (k.getText().equals(NEW_WORKSPACE_KEY)) {
                if (k.isMetaDown()) {
                    createWorkspace();
                }
            }
        });*/
    }

}
