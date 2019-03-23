package ui_private.displays;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import parser.CommandParser;
import state.ParserException;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class constructs the CommandTerminal and its various buttons
 * @author Carter Gay
 * @author Hunter Gregory
 */
public class CommandTerminal {
    private static final String PROMPT = "Enter Command";
    private static final String HELP_URL_1 = "https://www2.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
    private static final String HELP_URL_2 = "https://www2.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands2_J2W.php";
    private static final double COMMAND_STRETCH_WIDTH = 1000;
    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_STRETCH_HEIGHT = 60;

    private Button myParseButton;
    private Button myUndoButton;
    private Button myHelpButton;
    private TextArea myCommandInput;
    private String myCommand;
    private CommandParser myBackend;

    /**
     * Constructor for CommandTerminal and its buttons for parsing, help, and undo. Creates listeners for buttons
     * @param backend
     */
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
        myCommandInput.setPromptText(PROMPT);
        myBackend = backend;
    }

    /**
     * Populate the text of the CommandTerminal
     * @param command
     */
    public void setText(String command) {
        myCommandInput.setText(command);
    }

    /**
     * Send the text to the CommandParser when Parse button is pressed
     */
    public void sendToParser() {
        myCommand = myCommandInput.getText();
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
            Desktop.getDesktop().browse(new URL(HELP_URL_1).toURI());
            Desktop.getDesktop().browse(new URL(HELP_URL_2).toURI());
        }
        catch (URISyntaxException | IOException e) {
            System.out.println("Couldn't reach help url.");
        }
    }

    /**
     * Add the CommandTerminal and buttons to the GridPane
     * @return
     */
    public Pane getPane() {
        var gridPane = new GridPane();
        myCommandInput.setPrefWidth(COMMAND_STRETCH_WIDTH); //stretch out text area
        setButtonSize(myParseButton);
        setButtonSize(myUndoButton);
        setButtonSize(myHelpButton);
        gridPane.addRow(0, myCommandInput, myParseButton, myUndoButton, myHelpButton);
        return gridPane;
    }

    private void setButtonSize(Button button) {
        button.setMinWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_STRETCH_HEIGHT);
    }
}
