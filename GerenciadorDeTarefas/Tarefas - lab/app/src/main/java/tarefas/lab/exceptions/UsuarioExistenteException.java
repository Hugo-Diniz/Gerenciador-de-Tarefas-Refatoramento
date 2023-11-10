package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException(String message) {
        super(ConsoleColors.RED + message + ConsoleColors.RESET + "\n");
    }
    
}
