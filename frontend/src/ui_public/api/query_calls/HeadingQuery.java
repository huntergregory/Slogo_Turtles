package ui_public.api.query_calls;

import ui_public.api.FrontendAPICall;

public class HeadingQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getHeading();
    }
}
