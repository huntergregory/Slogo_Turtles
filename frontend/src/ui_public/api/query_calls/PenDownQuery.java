package ui_public.api.query_calls;

import ui_public.api.FrontendAPICall;

public class PenDownQuery extends FrontendAPICall {
    @Override
    public double call() {
       return ui.getPenIsDown() ? 1 : 0;
    }
}
