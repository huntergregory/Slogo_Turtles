package state_public;

public interface ReturnConsumer<R, P> {
    R accept(P val);
}
