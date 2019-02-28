package ui_private.displays;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import parser_public.CommandParser;
import parser_public.ParserException;

import java.awt.*;

public class CommandTerminal {
    public static final double PARSE_BUTTON_HEIGHT = 50;
    private static final String PROMPT = "Enter Command";

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
        }
    }

    public Node getPane() {
        return myCommandInput; //FIXME: need to return some pane including commandinput and parsebutton
    }
}
