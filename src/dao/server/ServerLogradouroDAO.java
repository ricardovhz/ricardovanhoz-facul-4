/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.server;

import bancodados.server.CommandException;
import bancodados.server.IServer;
import bancodados.server.response.Line;
import bancodados.server.response.Response;
import dao.LogradouroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Logradouro;

/**
 *
 * @author desenv01
 */
public class ServerLogradouroDAO extends AbstractServerDAO implements LogradouroDAO {

    public ServerLogradouroDAO(IServer server) {
        super(server);
    }

    @Override
    public List<Logradouro> getLogradouros() {
        List<Logradouro> list = new ArrayList<>();
        String stm = "select * from logradouro";
        try {
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            while (it.hasNext()) {
                list.add(fillLogradouro(it.next()));
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void insertLogradouro(Logradouro logradouro) {
        try {
            StringBuilder sb = new StringBuilder("insert into logradouro values (");
            sb.append(getNextId());
            sb.append(",'");
            sb.append(logradouro.getDescricao());
            sb.append("','");
            sb.append(logradouro.getBairro());
            sb.append("','");
            sb.append(logradouro.getCep());
            sb.append("','");
            sb.append(logradouro.getCidade());
            sb.append("')");
            server.executeCommand(sb.toString());
        } catch (CommandException ex) {
            Logger.getLogger(ServerLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteLogradouro(int id) {
        try {
            String stm = "delete from logradouro where codlog=" + id;
            server.executeCommand(stm);
        } catch (CommandException ex) {
            Logger.getLogger(ServerLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() throws SQLException {
        try {
            String stm = "select max(codlog) from logradouro";
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            if (it.hasNext()) {
                try {
                    return (it.next()).getInt(1) + 1;
                } catch (NumberFormatException ex) {
                    return 1;
                }
            } else {
                return 1;
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex);
        }
    }

    @Override
    public Logradouro findById(int id) {
        try {
            String stm = "select * from logradouro where codlog=" + id;
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            if (it.hasNext()) {
                return fillLogradouro(it.next());
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerLogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public Logradouro fillLogradouro(Line line) {
        Logradouro log = new Logradouro();

        log.setCodlog(line.getInt("codlog"));
        log.setDescricao(line.getString("descr"));
        log.setBairro(line.getString("bairro"));
        log.setCep(line.getString("cep"));
        log.setCidade(line.getString("cidade"));

        return log;
    }

    @Override
    public List<Logradouro> findByLogradouro(String logradouro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
