package control.frontendapi.query_calls;

import control.frontendapi.FrontendAPICall;

public class PenDownQuery extends FrontendAPICall {
    @Override
    public double call() {
       return ui.getPenIsDown() ? 1 : 0;
    }
}
