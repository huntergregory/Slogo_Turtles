package control.frontendapi.query_calls;

import control.frontendapi.FrontendAPICall;

public class XPositionQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getX();
    }
}
