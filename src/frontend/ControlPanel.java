package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

        ObservableList<String> turtleList = FXCollections.observableArrayList();
        ObservableList<String> colorList = FXCollections.observableArrayList("blue","red","yellow");
        ObservableList<String> languageList = FXCollections.observableArrayList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish","Syntax","Urdu");
        ObservableList<String> userCommandsList = FXCollections.observableArrayList();
        ObservableList<String> variablesList = FXCollections.observableArrayList();

        ControlPanel(int WIDTH, int HEIGHT) {
            init(WIDTH, HEIGHT);
        }

        private void init(int WIDTH, int HEIGHT) {
            paneBox = new VBox();
            VBox controlBox = new VBox(30);

            myCommandInput = new Selector(controlBox, "PARSE", "Enter Command", colorList);
            parseButton = myCommandInput.getButton();

            myTurtleImageChooser = new Selector(controlBox, "APPLY", "Choose Turtle", colorList);
            turtleImageButton = myTurtleImageChooser.getButton();

            myLanguageChooser = new Selector(controlBox, "APPLY", "Choose Language", languageList);
            languageButton = myLanguageChooser.getButton();

            userCommandsDropBox = new ComboBox<String>();
            userCommandsDropBox.setPromptText("VIew USer Commands");
            userCommandsDropBox.setEditable(true);
            userCommandsDropBox.setVisibleRowCount(3);
            userCommandsDropBox.setItems(colorList);
            controlBox.getChildren().add(userCommandsDropBox);

            variablesDropBox = new ComboBox<String>();
            variablesDropBox.setPromptText("VIew Variables");
            variablesDropBox.setEditable(true);
            variablesDropBox.setVisibleRowCount(3);
            variablesDropBox.setItems(colorList);
            controlBox.getChildren().add(variablesDropBox);

            myBackgroundColorChooser =  new ColorSelector(controlBox, "Background");
            backgroundButton = myBackgroundColorChooser.getButton();

            myPenColorChooser =  new ColorSelector(controlBox, "Pen Color");
            penButton = myPenColorChooser.getButton();

            helpButton = new Button("HELP");
            helpButton.setFont(font);
            controlBox.getChildren().add(helpButton);

            paneBox.setPadding(new Insets(30,30,30,30));
            paneBox.getChildren().add(controlBox);
            paneBox.setStyle("-fx-background-color: #027a50;");
            paneBox.setLayoutX(0);
            paneBox.setLayoutY(0);
            paneBox.setPrefWidth(WIDTH / 3);
            paneBox.setPrefHeight(HEIGHT);

            handleButtonInput();

        }

        private void handleButtonInput() {
            parseButton.setOnAction((event) -> {
                myCommand = myCommandInput.getInput();
                System.out.println(myCommand);
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

            });
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

}