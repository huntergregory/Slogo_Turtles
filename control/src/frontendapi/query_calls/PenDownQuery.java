package frontendapi.query_calls;

import frontendapi.FrontendAPICall;

public class PenDownQuery extends FrontendAPICall {
    @Override
    public double call() {
       return 0; //ui.getPenIsDown() ? 1 : 0;
    }
}
