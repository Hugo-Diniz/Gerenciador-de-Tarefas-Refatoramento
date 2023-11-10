package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class DataInvalidaException extends Exception {
    public DataInvalidaException(String message) {
        super(ConsoleColors.RED + message + ConsoleColors.RESET + "\n");
    }
    
}
