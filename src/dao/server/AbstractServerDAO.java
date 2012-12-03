/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.server;

import bancodados.server.IServer;

/**
 *
 * @author desenv01
 */
public abstract class AbstractServerDAO {
    protected IServer server;
    
    public AbstractServerDAO(IServer server) {
        this.server = server;
    }
    
}
