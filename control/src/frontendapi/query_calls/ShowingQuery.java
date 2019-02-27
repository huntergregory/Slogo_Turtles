package frontendapi.query_calls;

import frontendapi.FrontendAPICall;

public class ShowingQuery extends FrontendAPICall {
    @Override
    public double call() {
        return 0; //ui.getTurtleIsShowing() ? 1 : 0;
    }
}
