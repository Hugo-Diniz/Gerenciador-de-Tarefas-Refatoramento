package tarefas.lab.validators;

public class PrioriadadeValidator implements Validator<String> {
    @Override
    public boolean validate(String data) {
        return (data == "1" || data == "2" || data == "3");
    }
}
    