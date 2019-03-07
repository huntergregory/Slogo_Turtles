package ui_private.displays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import parser_public.CommandParser;

import java.awt.*;
import java.net.URL;

import state_public.ParserException;

import java.util.ArrayList;


public class CommandTerminal {
    public static final double PARSE_BUTTON_HEIGHT = 50;
    private static final String PROMPT = "Enter Command";
    public static final double MAX_WIDTH = 500;
    public static final double V_GAP = 30;

    private GridPane myPane;
    private ArrayList<Node> myChildren;
    private Button myParseButton;
    private Button myUndoButton;
    private Button myHelpButton;
    private TextArea myCommandInput;
    private String myCommand;
    private CommandParser myBackend;

    public CommandTerminal(CommandParser backend) {
        myParseButton = new Button("PARSE");
        myParseButton.setOnAction((event) -> sendToParser());
        myUndoButton = new Button("UNDO");
        myUndoButton.setOnAction((event) -> undoCommand());
        myHelpButton = new Button("HELP");
        myHelpButton.setOnAction((event) -> accessHelp());
        myCommandInput = new TextArea();
        myCommandInput.setPrefRowCount(10);
        myCommandInput.setPrefColumnCount(10);
        myCommandInput.setPromptText(PROMPT);//can barely ever see prompt, maybe we just set the text
        myBackend = backend;
    }

    private void sendToParser() {
        myCommand = myCommandInput.getText();
        System.out.println(myCommand); //TODO remove before submission
        myCommandInput.setText("");
        myCommandInput.setPromptText(PROMPT);
        try {
            myBackend.parseAndRun(myCommand);
        }
        catch (ParserException e) {
            myCommandInput.setPromptText("--" + e.getMessage() + "-- Enter a valid command");
        }
    }

    private void undoCommand() {

    }

    private void accessHelp() {
        try {
            Desktop.getDesktop().browse(new URL("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        var gridPane = new GridPane();
        myCommandInput.setPrefWidth(1000); //stretch out text area //FIXME magic number
        myParseButton.setMinWidth(100); //FIXME magic number
        myParseButton.setPrefHeight(60); //stretch out height //FIXME magic number
        myUndoButton.setMinWidth(100);
        myUndoButton.setPrefHeight(60);
        myHelpButton.setMinWidth(100);
        myHelpButton.setPrefHeight(60);
        gridPane.addRow(0, myCommandInput, myParseButton, myUndoButton, myHelpButton);
        return gridPane; //FIXME: need to return some pane including commandinput and parsebutton
    }
}
