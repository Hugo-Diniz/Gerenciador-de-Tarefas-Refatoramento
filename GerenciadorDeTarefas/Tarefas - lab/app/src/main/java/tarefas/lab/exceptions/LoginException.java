package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class LoginException extends Exception {
    public LoginException(String message) {
        super(ConsoleColors.RED + message + ConsoleColors.RESET + "\n");
    }

}
