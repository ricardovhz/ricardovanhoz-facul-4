/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Multa;
import modelo.Proprietario;
import modelo.Veiculo;

/**
 *
 * @author desenv01
 */
public interface MultaDAO {
    public List<Multa> getMultas();
    public List<Multa> findByProprietario(String nomeProprietario);
    public List<Multa> findByVeiculo(String descricaoVeiculo);
    public void insertMulta(Multa multa);
    public void deleteMulta(int codigo);
    public Proprietario getProprietarioFromId(int id);
    public Veiculo getVeiculoFromId(int id);
    public int getNextId() throws SQLException;
}
