package entry;

import backendapi.ParseCall;
import external.ExecutionContext;
import external.ExternalAPICall;
import frontendapi.rotate_angle_calls.LeftCall;
import javafx.application.Application;
import parser_public.CommandParser;
import parser_public.RequiredExternalAPICallsBack;
import ui_public.RequiredExternalAPICallsFront;
import ui_public.UIMain;
import frontendapi.move_distance_calls.ForwardCall;

import java.util.Timer;
import java.util.TimerTask;

/**
 * entry.Main entrypoint for program
 */
public class Main {

    public static void main(String args[]) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                go();
            }
        }, 3000);
        Application.launch(UIMain.class, args);



    }



    public static void go() {

        ExecutionContext executionContext = new ExecutionContext();
        executionContext.addExternalAPICall(RequiredExternalAPICallsBack.FORWARD, (ExternalAPICall<Double, Double>) distance -> new ForwardCall(distance).call());
        executionContext.addExternalAPICall(RequiredExternalAPICallsBack.LEFT, (ExternalAPICall<Double, Double>) degrees -> new LeftCall(degrees).call());

        CommandParser commandParser = new CommandParser(executionContext);

        ExecutionContext e2 = new ExecutionContext();
        try {
            e2.addExternalAPICall(RequiredExternalAPICallsFront.PARSE, (ExternalAPICall<Void, String>) input -> {
                try {
                    new ParseCall(input).call();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } return null;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UIMain.getInstance().setExecutionContext(e2);

        /**
        try {
            TimeUnit.SECONDS.sleep(5);
            commandParser.parseAndRun("fd 200");
        } catch (Exception e) {

        }*/


        //commandParser.parseAndRun("FD 37");

    }
}
