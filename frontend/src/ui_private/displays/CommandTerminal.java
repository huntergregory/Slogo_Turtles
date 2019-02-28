package ui_private.displays;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import parser_public.CommandParser;
import parser_public.ParserException;
import javafx.event.ActionEvent;
import java.awt.*;
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
        //myParseButton.setOnAction((event) -> sendToParser());
        myCommandInput = new TextArea();
        myCommandInput.setPrefRowCount(10);
        myCommandInput.setPrefColumnCount(10);
        myCommandInput.setPromptText(PROMPT);
    }

    private void sendToParser() {
        myCommand = myCommandInput.getText();
        System.out.println(myCommand); //TODO remove before submission
        try {
            CommandParser.getInstance().parseAndRun(myCommand);
        }
        catch (ParserException e) {
            // TODO handle parse error
            myCommandInput.setText("Enter a valid command");
        }
    }

    public Node getPane() {
        return myCommandInput; //FIXME: need to return some pane including commandinput and parsebutton
    }
}
