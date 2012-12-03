/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Proprietario;

/**
 *
 * @author desenv01
 */
public interface ProprietarioDAO {
    public List<Proprietario> getProprietarios();
    public int findIdByNome(String nome);
    public Proprietario findById(int id);
    public void insertProprietario(Proprietario proprietario);
    public void deleteProprietario(int id);
    public int getNextId() throws SQLException;
}
