package ui_public.api.query_calls;

import ui_public.api.FrontendAPICall;

public class ShowingQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getTurtleIsShowing() ? 1 : 0;
    }
}
