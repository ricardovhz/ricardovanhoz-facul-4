/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc.thread;

import bancodados.jdbc.thread.ThreadBanco;
import javax.swing.JTable;
import tablemodel.AbstractTableModel;

/**
 *
 * @author desenv01
 */
public abstract class AbstractThreadDAO {

    protected ThreadBanco banco;

    public AbstractThreadDAO(ThreadBanco banco) {
        this.banco = banco;
    }

}
