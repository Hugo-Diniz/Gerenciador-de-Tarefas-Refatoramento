package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class ListaVaziaException extends Exception {
    public ListaVaziaException(String message) {
        super(ConsoleColors.sentencaColorida(ConsoleColors.RED, message));
    }
}
