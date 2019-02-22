package control.frontendapi.query_calls;

import control.frontendapi.FrontendAPICall;

public class YPositionQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getY();
    }
}
