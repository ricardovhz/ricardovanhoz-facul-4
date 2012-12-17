/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import bancodados.jdbc.BancoDados;
import dao.LogradouroDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Logradouro;

/**
 *
 * @author Administrador
 */
public class JDBCLogradouroDAO implements LogradouroDAO {

    private BancoDados banco;

    public JDBCLogradouroDAO(BancoDados banco) {
        this.banco = banco;
    }

    @Override
    public List<Logradouro> getLogradouros() {

        List<Logradouro> result = new ArrayList<>();

        try {
            Statement st = banco.getConn().createStatement();
            ResultSet rs = st.executeQuery("select * from logradouro");

            while (rs.next()) {
                result.add(fillLogradouro(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public void insertLogradouro(Logradouro logradouro) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("insert into logradouro values (?,?,?,?,?)");
            st.setInt(1, getNextId());
            st.setString(2, logradouro.getDescricao());
            st.setString(3, logradouro.getBairro());
            st.setString(4, logradouro.getCep());
            st.setString(5, logradouro.getCidade());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLogradouro(int id) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("delete from logradouro where codlog=?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getNextId() throws SQLException {
        Statement st = banco.getConn().createStatement();
        ResultSet rs = st.executeQuery("select max(codlog) from logradouro");
        if (rs.first()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

    public Logradouro fillLogradouro(ResultSet rs) throws SQLException {
        Logradouro log = new Logradouro();

        log.setCodlog(rs.getInt("codlog"));
        log.setDescricao(rs.getString("descr"));
        log.setBairro(rs.getString("bairro"));
        log.setCep(rs.getString("cep"));
        log.setCidade(rs.getString("cidade"));

        return log;
    }

    @Override
    public Logradouro findById(int id) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("select * from logradouro where codlog=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return fillLogradouro(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Logradouro> findByLogradouro(String logradouro) {
        List<Logradouro> result = new ArrayList<>();
        try {
            PreparedStatement st = banco.getConn().prepareStatement("select * from logradouro where descr like ?");
            st.setString(1, "%"+logradouro+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(fillLogradouro(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
