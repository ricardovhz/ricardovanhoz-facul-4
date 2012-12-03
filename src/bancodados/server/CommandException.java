/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server;

/**
 *
 * @author desenv01
 */
public class CommandException extends Exception {

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
    
}
