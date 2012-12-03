/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Logradouro;

/**
 *
 * @author desenv01
 */
public interface LogradouroDAO {
    public List<Logradouro> getLogradouros();
    public void insertLogradouro(Logradouro logradouro);
    public void deleteLogradouro(int id);
    public int getNextId() throws SQLException;
    public Logradouro findById(int id);
}
