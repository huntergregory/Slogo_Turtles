package control.backendapi;

import parser.ParserException;

public abstract class BackendAPICall {

    public abstract double call() throws ParserException;

}
