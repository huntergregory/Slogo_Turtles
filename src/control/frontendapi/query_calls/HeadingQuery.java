package control.frontendapi.query_calls;

import control.frontendapi.FrontendAPICall;

public class HeadingQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getHeading();
    }
}
