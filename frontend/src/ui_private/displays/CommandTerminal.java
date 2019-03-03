package ui_private.displays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import parser_public.CommandParser;

import java.util.ArrayList;

public class CommandTerminal {
    public static final double PARSE_BUTTON_HEIGHT = 50;
    private static final String PROMPT = "Enter Command";
    public static final double MAX_WIDTH = 500;
    public static final double V_GAP = 30;

    private GridPane myPane;
    private ArrayList<Node> myChildren;
    private Button myParseButton;
    private TextArea myCommandInput;
    private String myCommand;

    public CommandTerminal() {
        myParseButton = new Button("PARSE");
        myParseButton.setOnAction((event) -> sendToParser());
        myCommandInput = new TextArea();
        myCommandInput.setPrefRowCount(10);
        myCommandInput.setPrefColumnCount(10);
        myCommandInput.setPromptText(PROMPT);//can barely ever see prompt, maybe we just set the text
    }

    private void sendToParser() {
        myCommand = myCommandInput.getText();
        System.out.println(myCommand); //TODO remove before submission
        myCommandInput.setText("");
        myCommandInput.setPromptText(PROMPT);
        try {
            CommandParser.getInstance().parseAndRun(myCommand);
        }
        catch (ParserException e) {
            myCommandInput.setPromptText("--" + e.getMessage() + "-- Enter a valid command");
        }
    }

    public Pane getPane() {
        var gridPane = new GridPane();
        myCommandInput.setPrefWidth(1000); //stretch out text area //FIXME magic number
        myParseButton.setMinWidth(100); //FIXME magic number
        myParseButton.setPrefHeight(60); //stretch out height //FIXME magic number
        gridPane.addRow(0, myCommandInput, myParseButton);
        return gridPane; //FIXME: need to return some pane including commandinput and parsebutton
    }
}
