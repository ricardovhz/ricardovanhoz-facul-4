/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bancodados.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Veiculo;

/**
 *
 * @author Administrador
 */
public class VeiculoDAO {

    private BancoDados banco;

    public VeiculoDAO(BancoDados banco) {
        this.banco = banco;
    }

    public List<Veiculo> getVeiculos() {

        List<Veiculo> result = new ArrayList<Veiculo>();

        try {
            Statement st = banco.getConn().createStatement();
            ResultSet rs = st.executeQuery("select * from veiculo");

            while (rs.next()) {
                result.add(fillVeiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    public void insertVeiculo(Veiculo veiculo) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("insert into veiculo values (?,?,?,?)");
            st.setInt(1, getNextId());
            st.setString(2, veiculo.getDescricao());
            st.setDouble(3, veiculo.getChassi());
            st.setInt(4, veiculo.getCodpro());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() throws SQLException {
        Statement st = banco.getConn().createStatement();
        ResultSet rs = st.executeQuery("select max(codigo) from veiculo");
        if (rs.first()) {
            return rs.getInt(1)+1;
        } else {
            return 0;
        }
    }

    public Veiculo fillVeiculo(ResultSet rs) throws SQLException {
        Veiculo vei = new Veiculo();

        vei.setCodigo(rs.getInt("codigo"));
        vei.setDescricao(rs.getString("descr"));
        vei.setChassi(rs.getDouble("chassi"));
        vei.setCodpro(rs.getInt("codpro"));

        return vei;
    }
}
