package backendapi;

import parser_public.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class VariablesCall {

    public VariablesCall(String language) {}

    public Map<String, Double> call() {
        //return GlobalVariables.getInstance().getVariablesGroup().getMap();
        return new HashMap<>();
    }
}
