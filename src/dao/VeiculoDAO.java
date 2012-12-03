/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Proprietario;
import modelo.Veiculo;

/**
 *
 * @author desenv01
 */
public interface VeiculoDAO {
    public List<Veiculo> getVeiculos();
    public void insertVeiculo(Veiculo veiculo);
    public void deleteVeiculo(int id);
    public int getNextId() throws SQLException;
    public Proprietario getProprietarioFromId(int id);
    public Veiculo findById(int id);
    public Veiculo findByDescricao(String descricao);
    public int findIdByDescricao(String descricao);
}
