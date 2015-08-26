package exercise.domain;

@FunctionalInterface
public interface IdProvider {
    Long nextId();
}
