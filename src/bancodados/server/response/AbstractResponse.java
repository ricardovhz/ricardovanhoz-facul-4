/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server.response;

/**
 *
 * @author desenv01
 */
public abstract class AbstractResponse implements Response {

    protected String status;

    public AbstractResponse(String status) {
        this.status = status;
    }

    public AbstractResponse(String status, String response) {
        this(status);
        this.process(response);
    }

    @Override
    public String getStatus() {
        return status;
    }

    protected abstract void process(String response);
}
