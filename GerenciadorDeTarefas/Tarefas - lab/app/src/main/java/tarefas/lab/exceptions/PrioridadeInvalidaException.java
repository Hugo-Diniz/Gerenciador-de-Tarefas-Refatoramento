package tarefas.lab.exceptions;

import tarefas.lab.view.ConsoleColors;

public class PrioridadeInvalidaException extends Exception{
    public PrioridadeInvalidaException(String message) {
         super(ConsoleColors.sentencaColorida(ConsoleColors.RED, message));
    }
}
