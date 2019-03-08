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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import parser_public.CommandParser;
import state_public.ParserException;
import state_public.StateManager;
import ui_public.UIBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 750;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";
    private static final double MENU_H_GAP = 10;
    private static final double MENU_V_GAP = 10;
    private static final String STYLE_SHEET = "style.css";
    private static final String NEW_WORKSPACE_KEY = "n";

    private Stage myStage;
    private Scene myScene;
    private BorderPane myBorderPane;
    //private ToolBar myToolBar;
    private MenuBar myMenuBar;
    private GridPane myFeaturePane;
    private TabPane myTabPane;
    private List<UIBuilder> myWorkspaces;

    public Main() {
        myWorkspaces = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        myStage = stage;
        initTabPane();
        createWorkspace();
        setupMenuBar();
        initBorderPane();

        showStage();

        UIBuilder.addStyle(myScene);
        this.addStyle();
    }

    private void initTabPane() {
        myTabPane = new TabPane();
        myTabPane.getTabs().addListener((ListChangeListener) c -> enforceTabPolicy());
    }

    private void initBorderPane() {
        myBorderPane = new BorderPane();
        myBorderPane.setTop(myMenuBar);
        myBorderPane.setCenter(myTabPane);
    }

    private void setupMenuBar() {
        myMenuBar = new MenuBar();
        addFileMenu();
        initFeaturePane();
        addFeatureMenu();
    }

    private void addFileMenu() {
        Menu fileMenu = new Menu("File");
        myMenuBar.getMenus().add(fileMenu);
    }

    private void initFeaturePane() {
        myFeaturePane = new GridPane();
        myFeaturePane.setHgap(MENU_H_GAP);
        myFeaturePane.setVgap(MENU_V_GAP);
        String[] features = getCurrentWorkspace().getFeatureNames();
        for (int k=0; k<features.length; k++) {
            addRow(features[k], k);
        }
    }

    private void addRow(String text, int row) {
        var featureButton = new FeatureGridItem(text, getCurrentWorkspace());
        myFeaturePane.addRow(row, featureButton.getLabel(), featureButton.getButton());
    }

    private void addFeatureMenu() {
        Menu featureMenu = new Menu("Customize...");
        featureMenu.getStyleClass().add("column-filter");

        CustomMenuItem customizeItem = new CustomMenuItem(myFeaturePane);
        customizeItem.setHideOnClick(false);
        featureMenu.getItems().add(customizeItem);
        myMenuBar.getMenus().add(featureMenu);
    }

    /*private void initFeatureHBox() {
        myFeatureHBox = new HBox();
        VBox leftFeatureVBox = createFeatureVBox();
        VBox rightCheckBoxVBox = createFeatureVBox();
        myFeatureHBox.getChildren().addAll(leftFeatureVBox, rightCheckBoxVBox);
    }

    private VBox createFeatureVBox() {
        VBox box = new VBox();
        for (FeatureType type : FeatureType.values()) {

        }
        return box; //FIXME
    }*/

    private void enforceTabPolicy() {
        boolean oneTabLeft = myTabPane.getTabs().size() == 1;
        var policy = (oneTabLeft) ? TabPane.TabClosingPolicy.UNAVAILABLE : TabPane.TabClosingPolicy.SELECTED_TAB;
        myTabPane.setTabClosingPolicy(policy);
    }

    /*private void createToolbar() {
        myToolBar = new ToolBar();
        makeButton("New", e -> createWorkspace());
        makeButton("Load", e -> {}); //FIXME
        makeButton("Customize...", e -> showFeatureCheckboxes());
    }*/

    private void makeButton(String text, EventHandler<ActionEvent> handler) {
        var button = new Button(text);
        button.setOnAction(handler);
        //myToolBar.getItems().add(button);
    }

    private void showFeatureCheckboxes() {
        //myBorderPane.setCenter(myFeaturePane);

        getCurrentWorkspace();


        //TODO: add checkboxes and listeners
        //TODO: keep track of current features and their order
    }

    private UIBuilder getCurrentWorkspace() {
        int currentTab = myTabPane.getSelectionModel().getSelectedIndex();
        return myWorkspaces.get(currentTab);
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
            UIBuilder workspace = new UIBuilder(backend, stateManager, WIDTH, HEIGHT);
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

    private void addStyle() {
        myScene.getStylesheets().add(STYLE_SHEET); //css not working for menu highlight
    }
}
