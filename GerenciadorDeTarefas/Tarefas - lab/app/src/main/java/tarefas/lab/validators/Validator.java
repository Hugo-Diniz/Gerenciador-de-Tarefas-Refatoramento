package tarefas.lab.validators;

public interface Validator<T> {
    boolean validate(T data);
}
