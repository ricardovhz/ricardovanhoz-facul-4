/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server;

import bancodados.IBanco;
import bancodados.server.response.Response;

/**
 *
 * @author desenv01
 */
public interface IServer extends IBanco {
    public Response executeCommand(String command) throws CommandException;
}
