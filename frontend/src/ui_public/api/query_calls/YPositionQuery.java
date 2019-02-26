package ui_public.api.query_calls;

import ui_public.api.FrontendAPICall;

public class YPositionQuery extends FrontendAPICall {
    @Override
    public double call() {
        return ui.getY();
    }
}
