package external;

public interface ExternalAPICall <R, P> {

    R call(P param);

}
