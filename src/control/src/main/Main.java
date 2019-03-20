package main;

import feature_grid_item.FeatureGridItem;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.CommandParser;
import state.ParserException;
import state.StateManager;
import ui.UIBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The main application of this software system. Combines all aspects of the software system into a workspace.
 * Manages multiple workspaces in a tab pane, and provides a menu that can be used to interact with the current workspace.
 * @author Hunter Gregory
 * @author David Miron (createWorkspace)
 */
public class Main extends Application {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 750;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";
    private static final double MENU_H_GAP = 10;
    private static final double MENU_V_GAP = 10;
    private static final String STYLE_SHEET = "style.css";
    private static final String FILE_CHOOSER_TITLE = "Open Example File";
    private static final String FILE_CHOOSER_INIT_DIR = "data/examples";

    private Stage myStage;
    private Scene myScene;
    private VBox myVBox;
    private MenuBar myMenuBar;
    private Menu myFeatureMenu;
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

        myVBox = new VBox(myMenuBar, myTabPane);
        changeFeatureMenu();

        showStage();

        UIBuilder.addStyle(myScene);
        this.addStyle();
    }

    private void initTabPane() {
        myTabPane = new TabPane();
        myTabPane.getTabs().addListener((ListChangeListener<? super Tab>) c -> enforceTabPolicy());
        myTabPane.getSelectionModel().selectedItemProperty().addListener(c -> changeFeatureMenu());
    }

    private void enforceTabPolicy() {
        boolean oneTabLeft = myTabPane.getTabs().size() == 1;
        var policy = (oneTabLeft) ? TabPane.TabClosingPolicy.UNAVAILABLE : TabPane.TabClosingPolicy.SELECTED_TAB;
        myTabPane.setTabClosingPolicy(policy);
    }

    private void changeFeatureMenu() {
        if (myFeatureMenu == null)
            return;

        if (myFeatureMenu.getItems().size() > 0)
            myFeatureMenu.getItems().remove(0);

        CustomMenuItem menuItem = new CustomMenuItem(getFeaturePane());
        menuItem.setHideOnClick(false);
        myFeatureMenu.getItems().add(menuItem);
    }


    private void createWorkspace() {
        try {
            StateManager stateManager = new StateManager();
            CommandParser backend = new CommandParser(stateManager);
            UIBuilder workspace = new UIBuilder(backend, stateManager, WIDTH, HEIGHT);
            addTab(workspace, myWorkspaces.size() + 1);
            myWorkspaces.add(workspace);
        } catch (ParserException e) {
            System.out.println("Workspace couldn't be created.");
        }
    }


    private void addTab(UIBuilder workspace, int id) {
        Tab tab = new Tab("Turtlespace " + id);
        tab.setContent(workspace.getContent());
        myTabPane.getTabs().add(tab);
    }


    private void setupMenuBar() {
        myMenuBar = new MenuBar();
        addFileMenu();
        addEmptyFeatureMenu();
    }

    private void addEmptyFeatureMenu() {
        myFeatureMenu = new Menu("Customize...");
        myFeatureMenu.getStyleClass().add("column-filter"); //TODO remove if css not working
        myMenuBar.getMenus().add(myFeatureMenu);
    }


    private void addFileMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newWorkspaceItem = new MenuItem("New Workspace");
        newWorkspaceItem.setOnAction(e -> createWorkspace());
        fileMenu.getItems().add(newWorkspaceItem);

        MenuItem loadItem = new MenuItem("Load...");
        loadItem.setOnAction(e -> loadFile());
        fileMenu.getItems().add(loadItem);
        myMenuBar.getMenus().add(fileMenu);
    }

    private void loadFile() {
        var chooser = new FileChooser();
        chooser.setTitle(FILE_CHOOSER_TITLE);
        chooser.setInitialDirectory(new File(FILE_CHOOSER_INIT_DIR));
        chooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", "*.logo"));
        File file = chooser.showOpenDialog(myStage);
        if (file != null)
            getCurrentWorkspace().setText(file);
    }

    private GridPane getFeaturePane() {
        var featurePane = new GridPane();
        featurePane.setHgap(MENU_H_GAP);
        featurePane.setVgap(MENU_V_GAP);

        String[] totalFeatures = getCurrentWorkspace().getFeatureNames();
        for (int k=0; k<totalFeatures.length; k++)
            addRow(totalFeatures[k], featurePane, k);

        return featurePane;
    }


    private void addRow(String text, GridPane pane, int row) {
        var featureButton = new FeatureGridItem(text, getCurrentWorkspace());
        pane.addRow(row, featureButton.getLabel(), featureButton.getButton());
    }


    private UIBuilder getCurrentWorkspace() {
        int currentTab = myTabPane.getSelectionModel().getSelectedIndex();
        return myWorkspaces.get(currentTab);
    }

    private void showStage() {
        myScene = new Scene(myVBox, WIDTH, HEIGHT, BACKGROUND);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
    }


    private void addStyle() {
        myScene.getStylesheets().add(STYLE_SHEET); //css not working for menu highlight
    }
}
