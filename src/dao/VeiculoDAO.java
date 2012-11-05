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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Proprietario;
import modelo.Veiculo;

/**
 *
 * @author Administrador
 */
public class VeiculoDAO {

    private BancoDados banco;
    private ProprietarioDAO dao;

    public VeiculoDAO(BancoDados banco) {
        this.banco = banco;
        dao = new ProprietarioDAO(banco);
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
            PreparedStatement st = banco.getConn().prepareStatement("insert into veiculo (codigo,descr,placa,chassi,codpro) values (?,?,?,?,?)");
            st.setInt(1, getNextId());
            st.setString(2, veiculo.getDescricao());
            st.setString(3, veiculo.getChassi());
            st.setString(4, veiculo.getPlaca());
            st.setInt(5, veiculo.getProprietario().getCodigo());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVeiculo(int id) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("delete from veiculo where codigo=?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getNextId() throws SQLException {
        Statement st = banco.getConn().createStatement();
        ResultSet rs = st.executeQuery("select max(codigo) from veiculo");
        if (rs.first()) {
            return rs.getInt(1) + 1;
        } else {
            return 0;
        }
    }

    public Veiculo fillVeiculo(ResultSet rs) throws SQLException {
        Veiculo vei = new Veiculo();

        vei.setCodigo(rs.getInt("codigo"));
        vei.setDescricao(rs.getString("descr"));
        vei.setChassi(rs.getString("chassi"));
        vei.setPlaca(rs.getString("placa"));
        vei.setProprietario(getProprietarioFromId(rs.getInt("codpro")));

        return vei;
    }

    private Proprietario getProprietarioFromId(int id) {
        return dao.findById(id);
    }

    public Veiculo findById(int id) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("select * from veiculo where codigo=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return fillVeiculo(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Veiculo findByDescricao(String descricao) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("select * from veiculo where descr=?");
            st.setString(1, descricao);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return fillVeiculo(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int findIdByDescricao(String descricao) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("select codigo from veiculo where descr=?");
            st.setString(1, descricao);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
}
