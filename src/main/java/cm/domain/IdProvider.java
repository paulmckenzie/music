package cm.domain;

@FunctionalInterface
public interface IdProvider {
    Long nextId();
}
