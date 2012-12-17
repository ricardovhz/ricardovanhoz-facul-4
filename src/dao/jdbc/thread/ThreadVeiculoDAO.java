/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc.thread;

import bancodados.jdbc.thread.ThreadBanco;
import dao.ProprietarioDAO;
import dao.VeiculoDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Proprietario;
import modelo.Veiculo;
import tablemodel.AbstractTableModel;

/**
 *
 * @author desenv01
 */
public class ThreadVeiculoDAO extends AbstractThreadDAO implements VeiculoDAO {

    private ProprietarioDAO dao;

    public ThreadVeiculoDAO(ThreadBanco banco) {
        super(banco);
        dao = new ThreadProprietarioDAO(banco);
    }

    @Override
    public List<Veiculo> getVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        try {

            String stat = "select * from veiculo";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                veiculos.add(fillVeiculo(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ThreadVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculos;
    }

    @Override
    public List<Veiculo> findAllByDescricao(String descricao) {
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            String stat = "select * from veiculo where descr like '%" + descricao + "%'";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                veiculos.add(fillVeiculo(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculos;
    }

    @Override
    public void insertVeiculo(Veiculo veiculo) {
        try {
            StringBuilder st = new StringBuilder("insert into veiculo (codigo,descr,placa,chassi,codpro) values (");
            st.append(getNextId());
            st.append(",'");
            st.append(veiculo.getDescricao());
            st.append("','");
            st.append(veiculo.getPlaca());
            st.append("',");
            st.append(veiculo.getChassi());
            st.append(",");
            st.append(veiculo.getProprietario().getCodigo());
            st.append(")");
            banco.executeUpdate(st.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ThreadVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteVeiculo(int id) {
        String stat = "delete from veiculo where codigo=" + id;
        banco.executeUpdate(stat);
    }

    @Override
    public int getNextId() throws SQLException {
        String stat = "select max(codigo) from veiculo";
        ResultSet rs = banco.executeSelect(stat);
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;
    }

    @Override
    public Proprietario getProprietarioFromId(int id) {
        return dao.findById(id);
    }

    @Override
    public Veiculo findById(int id) {
        try {
            String stat = "select * from veiculo where codigo=" + id;
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return fillVeiculo(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Veiculo findByDescricao(String descricao) {
        try {
            String stat = "select * from veiculo where descr = '" + descricao + "'";
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return fillVeiculo(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public int findIdByDescricao(String descricao) {
        try {
            String stat = "select codigo from veiculo where descr='" + descricao + "'";
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
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
}
