package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControlPanel {

        protected Font font = new Font("verdana",12);
        protected VBox paneBox;

        private HBox userCommandsHBox;
        private HBox variablesHBox;
        private HBox helpHBox;

        private Selector myCommandInput;
        private Selector myTurtleImageChooser;
        private Selector myBackgroundColorChooser;
        private Selector myPenColorChooser;
        private Selector myLanguageChooser;

        private DropBox userCommandsDropBox;
        private DropBox variablesDropBox;

        private SlogoButton helpButton;

        ObservableList<String> turtleList = FXCollections.observableArrayList("a","b","c");
        ObservableList<String> colorList = FXCollections.observableArrayList("blue","red","yellow");
        ObservableList<String> languageList = FXCollections.observableArrayList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish","Syntax","Urdu");
        ObservableList<String> userCommandsList = FXCollections.observableArrayList("a","b","c");
        ObservableList<String> variablesList = FXCollections.observableArrayList("a","b","c");

        public ControlPanel(int WIDTH, int HEIGHT) {
            init(WIDTH, HEIGHT);
        }

        protected void init(int WIDTH, int HEIGHT) {
            paneBox = new VBox();
            VBox controlBox = new VBox();

            myCommandInput = new Selector(controlBox, "PARSE", "Enter Command", colorList);
            myTurtleImageChooser = new Selector(controlBox, "APPLY", "Choose Turtle", colorList);
            myBackgroundColorChooser = new Selector(controlBox, "APPLY", "Choose Background Color", colorList);
            myPenColorChooser = new Selector(controlBox, "APPLY", "Choose Pen Color", colorList);
            myLanguageChooser = new Selector(controlBox, "APPLY", "Choose Language", languageList);

            userCommandsHBox = new HBox();
            userCommandsDropBox = new DropBox(userCommandsHBox, "View User Commands", colorList);
            controlBox.getChildren().add(userCommandsHBox);

            variablesHBox = new HBox();
            variablesDropBox = new DropBox(variablesHBox, "View Variables", colorList);
            controlBox.getChildren().add(variablesHBox);

            helpHBox = new HBox();
            helpButton = new SlogoButton(helpHBox, "HELP");
            controlBox.getChildren().add(helpHBox);

            paneBox.setPadding(new Insets(30,30,30,30));
            paneBox.getChildren().add(controlBox);
            paneBox.setStyle("-fx-background-color: #027a50;");
            paneBox.setLayoutX(0);
            paneBox.setLayoutY(0);
            paneBox.setPrefWidth(WIDTH / 3);
            paneBox.setPrefHeight(HEIGHT);

        }
}

