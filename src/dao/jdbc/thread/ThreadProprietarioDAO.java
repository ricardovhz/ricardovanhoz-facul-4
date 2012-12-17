/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc.thread;

import bancodados.jdbc.thread.ThreadBanco;
import dao.LogradouroDAO;
import dao.ProprietarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Proprietario;

/**
 *
 * @author desenv01
 */
public class ThreadProprietarioDAO extends AbstractThreadDAO implements ProprietarioDAO {

    private LogradouroDAO dao;

    public ThreadProprietarioDAO(ThreadBanco banco) {
        super(banco);
        this.dao = new ThreadLogradouroDAO(banco);
    }

    @Override
    public List<Proprietario> getProprietarios() {
        List<Proprietario> props = new ArrayList<>();
        try {
            String stat = "select * from proprietario";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                props.add(fillProprietario(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return props;
    }

    @Override
    public List<Proprietario> findAllByNome(String nome) {
        List<Proprietario> props = new ArrayList<>();
        try {
            String stat = "select * from proprietario where nome like '%" + nome + "%'";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                props.add(fillProprietario(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    @Override
    public int findIdByNome(String nome) {
        try {
            String stat = "select codigo from proprietario where nome='" + nome + "'";
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return rs.getByte(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Proprietario findById(int id) {
        try {
            String stat = "select * from proprietario where codigo=" + id;
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return fillProprietario(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insertProprietario(Proprietario proprietario) {
        try {
            StringBuilder st = new StringBuilder("insert into proprietario (codigo,nome,identidade,codlog,numero,telefone,email) values (");
            st.append(getNextId());
            st.append(",'");
            st.append(proprietario.getNome());
            st.append("','");
            st.append(proprietario.getIdentidade());
            st.append("',");
            st.append(proprietario.getEndereco().getCodlog());
            st.append(",");
            st.append(proprietario.getNumero());
            st.append(",'");
            st.append(proprietario.getTel());
            st.append("','");
            st.append(proprietario.getEmail());
            st.append("')");
            banco.executeUpdate(st.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ThreadProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteProprietario(int id) {
        String stat = "delete from proprietario where codigo=" + id;
        banco.executeUpdate(stat);
    }

    @Override
    public int getNextId() throws SQLException {
        String stat = "select max(codigo) from proprietario";
        ResultSet rs = banco.executeSelect(stat);
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;
    }

    private Proprietario fillProprietario(ResultSet rs) throws SQLException {
        Proprietario pro = new Proprietario();

        pro.setCodigo(rs.getInt("codigo"));
        pro.setNome(rs.getString("nome"));
        pro.setIdentidade(rs.getString("identidade"));
        pro.setEndereco(dao.findById(rs.getInt("codlog")));
        pro.setNumero(rs.getInt("numero"));
        pro.setTel(rs.getString("telefone"));
        pro.setEmail(rs.getString("email"));

        return pro;
    }
}
