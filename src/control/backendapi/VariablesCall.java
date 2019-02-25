package control.backendapi;

import parser.GlobalVariables;
import java.util.Map;

public class VariablesCall extends BackendAPICall {

    public VariablesCall(String language) {}

    @Override
    public Map<String, Double> call() {
        return GlobalVariables.getInstance().getVariablesGroup().getMap();
    }
}
