package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class BuscarTarefaException extends Exception {
    public BuscarTarefaException(String message) {
        super(ConsoleColors.sentencaColorida(ConsoleColors.RED, message));
    }
}
