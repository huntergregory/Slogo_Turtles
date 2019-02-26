package ui_private;

import external.ExecutionContext;
import external.ExternalAPICall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ui_public.RequiredExternalAPICallsFront;

import java.io.IOException;

public class ControlPanel {

        private Font font = new Font("verdana",12);
        protected VBox paneBox;

        private Selector myCommandInput;
        private Button parseButton;
        private String myCommand;

        private Selector myTurtleImageChooser;
        private Button turtleImageButton;
        private String myTurtleImage;

        private Selector myLanguageChooser;
        private Button languageButton;
        private String myLanguage;

        private ComboBox<String> userCommandsDropBox;
        private ComboBox<String> variablesDropBox;

        private ColorSelector myBackgroundColorChooser;
        private Button backgroundButton;
        private Color myBackgroundColor;

        private ColorSelector myPenColorChooser;
        private Button penButton;
        private Color myPenColor;

        private Button helpButton;

        ObservableList<String> pastCommandsList = FXCollections.observableArrayList("fd 50");
        ObservableList<String> turtleList = FXCollections.observableArrayList("tan_turtle.png");
        ObservableList<String> languageList = FXCollections.observableArrayList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish","Urdu");
        ObservableList<String> userCommandsList = FXCollections.observableArrayList("fd 50");
        ObservableList<String> variablesList = FXCollections.observableArrayList();

        private ExecutionContext executionContext;

        public ControlPanel(double WIDTH, double HEIGHT, ExecutionContext executionContext) {
            this.executionContext = executionContext;
            init(WIDTH, HEIGHT);
        }

        private void init(double WIDTH, double HEIGHT) {
            paneBox = new VBox();
            VBox controlBox = new VBox(30);

            addCommandInput(controlBox);
            addTurtleImageChooser(controlBox);
            addLanguageChooser(controlBox);
            addUserCommandsDropBox(controlBox);
            addVariablesDropBox(controlBox);
            addBackGroundColorChooser(controlBox);
            addPenColorChooser(controlBox);
            addHelpButton(controlBox);

            setupPaneBox(WIDTH, HEIGHT, controlBox);

            handleButtonInput();
        }

        private void addCommandInput(VBox controlBox) {
            myCommandInput = new Selector(controlBox, "PARSE", "Enter Command", pastCommandsList);
            parseButton = myCommandInput.getButton();

        }

        private void addTurtleImageChooser(VBox controlBox) {
            myTurtleImageChooser = new Selector(controlBox, "APPLY", "Choose Turtle", turtleList);
            turtleImageButton = myTurtleImageChooser.getButton();
        }

        private void addLanguageChooser(VBox controlBox) {
            myLanguageChooser = new Selector(controlBox, "APPLY", "Choose Language", languageList);
            languageButton = myLanguageChooser.getButton();
        }

        private void addUserCommandsDropBox(VBox controlBox) {
            userCommandsDropBox = new ComboBox<String>();
            userCommandsDropBox.setPromptText("VIew User Commands");
            userCommandsDropBox.setEditable(true);
            userCommandsDropBox.setVisibleRowCount(3);
            userCommandsDropBox.setItems(userCommandsList);
            controlBox.getChildren().add(userCommandsDropBox);
        }

        private void addVariablesDropBox(VBox controlBox) {
            variablesDropBox = new ComboBox<String>();
            variablesDropBox.setPromptText("VIew Variables");
            variablesDropBox.setEditable(true);
            variablesDropBox.setVisibleRowCount(3);
            variablesDropBox.setItems(variablesList);
            controlBox.getChildren().add(variablesDropBox);
        }

        private void addBackGroundColorChooser(VBox controlBox) {
            myBackgroundColorChooser =  new ColorSelector(controlBox, "Background");
            backgroundButton = myBackgroundColorChooser.getButton();
        }

        private void addPenColorChooser(VBox controlBox) {
            myPenColorChooser =  new ColorSelector(controlBox, "Pen Color");
            penButton = myPenColorChooser.getButton();
        }

        private void addHelpButton(VBox controlBox) {
            helpButton = new Button("HELP");
            helpButton.setFont(font);
            controlBox.getChildren().add(helpButton);
        }

        private void setupPaneBox(double WIDTH, double HEIGHT, VBox controlBox) {
            paneBox.setPadding(new Insets(30,30,30,30));
            paneBox.getChildren().add(controlBox);
            paneBox.setStyle("-fx-background-color: #027a50;");
            paneBox.setLayoutX(0);
            paneBox.setLayoutY(0);
            paneBox.setPrefWidth(WIDTH);
            paneBox.setPrefHeight(HEIGHT);
        }

        private void handleButtonInput() {
            parseButton.setOnAction((event) -> {
                myCommand = myCommandInput.getInput();
                System.out.println(myCommand);
                handleParse(myCommand);
            });
            turtleImageButton.setOnAction((event) -> {
                myTurtleImage = myTurtleImageChooser.getInput();
            });
            languageButton.setOnAction((event) -> {
                myLanguage = myLanguageChooser.getInput();
            });
            backgroundButton.setOnAction((event) -> {
                myBackgroundColor = myBackgroundColorChooser.getColor();
            });
            penButton.setOnAction((event) -> {
                myPenColor = myPenColorChooser.getColor();
            });
            helpButton.setOnAction((event) -> {
                String url_open ="https://www2.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        private void handleParse(String input) {
            try {
                ExternalAPICall<Void, String> parseCall = (ExternalAPICall<Void, String>) executionContext.getExternalAPICall(RequiredExternalAPICallsFront.PARSE);
                parseCall.call(input);
            } catch (Exception e) {

            }
        }

        public String getMyCommand() {
            return myCommand;
        }

        public String getMyTurtleImage() {
            return myTurtleImage;
        }

        public String getMyLanguage() {
            return myLanguage;
        }

        public Color getMyBackgroundColor() {
            return myBackgroundColor;
        }

        public Color getMyPenColor() {
            return myPenColor;
        }

        public void addPastCommand(String command) {
            pastCommandsList.add(command);
        }

        public void addUserCommand(String command) {
            userCommandsList.add(command);
        }

        public void addVariable(String variable) {
            variablesList.add(variable);
        }

        public VBox getPaneBox() {
            return paneBox;
        }

    public void setExecutionContext(ExecutionContext executionContext) {
            this.executionContext = executionContext;
    }
}