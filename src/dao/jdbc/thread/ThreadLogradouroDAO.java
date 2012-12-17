/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc.thread;

import bancodados.jdbc.thread.ThreadBanco;
import dao.LogradouroDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Logradouro;
import tablemodel.AbstractTableModel;

/**
 *
 * @author desenv01
 */
public class ThreadLogradouroDAO extends AbstractThreadDAO implements LogradouroDAO {

    public ThreadLogradouroDAO(ThreadBanco banco) {
        super(banco);
    }

    @Override
    public List<Logradouro> getLogradouros() {
        String stat = "select * from logradouro";
        List<Logradouro> logs = new ArrayList<>();
        ResultSet rs = banco.executeSelect(stat);
        try {
            while (rs.next()) {
                logs.add(fillLogradouro(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return logs;
    }

    @Override
    public List<Logradouro> findByLogradouro(String logradouro) {
        List<Logradouro> logs = new ArrayList<>();
        try {
            String stat = "select * from logradouro where descr like '%" + logradouro + "%'";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                logs.add(fillLogradouro(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;
    }

    @Override
    public void insertLogradouro(Logradouro logradouro) {
        try {
            StringBuilder stat = new StringBuilder("insert into logradouro values (");
            stat.append(getNextId());
            stat.append(",'");
            stat.append(logradouro.getDescricao());
            stat.append("','");
            stat.append(logradouro.getBairro());
            stat.append("','");
            stat.append(logradouro.getCep());
            stat.append("','");
            stat.append(logradouro.getCidade());
            stat.append("')");

            banco.executeUpdate(stat.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ThreadLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteLogradouro(int id) {
        String stat = "delete from logradouro where codlog=" + id;
        banco.executeUpdate(stat);
    }

    @Override
    public int getNextId() throws SQLException {
        try {
            String stat = "select max(codlog) from logradouro";
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return rs.getInt(1)+1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    @Override
    public Logradouro findById(int id) {
        try {
            String stat = "select * from logradouro where codlog="+id;
            ResultSet rs = banco.executeSelect(stat);
            if (rs.next()) {
                return fillLogradouro(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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
}