package control.frontendapi.query_calls;

import control.frontendapi.FrontendAPICall;

public class ShowingQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getTurtleIsShowing() ? 1 : 0;
    }
}
