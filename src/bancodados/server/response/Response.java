/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server.response;

/**
 *
 * @author desenv01
 */
public interface Response extends Iterable<Line> {
    public String getStatus();
}
