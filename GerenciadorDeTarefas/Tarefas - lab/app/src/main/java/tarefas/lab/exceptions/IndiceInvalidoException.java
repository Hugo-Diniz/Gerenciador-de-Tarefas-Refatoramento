package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class IndiceInvalidoException extends Exception {
    public IndiceInvalidoException(String message) {
        super(ConsoleColors.RED + message + ConsoleColors.RESET + "\n");
    }
}
