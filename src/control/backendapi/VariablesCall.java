package control.backendapi;

import parser.GlobalVariables;
import java.util.Map;

public class VariablesCall {

    public VariablesCall(String language) {}

    public Map<String, Double> call() {
        return GlobalVariables.getInstance().getVariablesGroup().getMap();
    }
}
