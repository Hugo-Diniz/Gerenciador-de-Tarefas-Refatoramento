package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class OpcaoInvalidaException extends Exception {
    public OpcaoInvalidaException(String message) {
        super(ConsoleColors.RED + message + ConsoleColors.RESET + "\n");
    }
    
}
