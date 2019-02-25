package control.backendapi;

import parser.GlobalVariables;

public class VariablesCall extends BackendAPICall {

    public VariablesCall(String language) {}

    @Override
    public double call() {
        //return GlobalVariables.getInstance().getVariablesGroup().getMap();
        return 0; //TODO fix this
    }
}
