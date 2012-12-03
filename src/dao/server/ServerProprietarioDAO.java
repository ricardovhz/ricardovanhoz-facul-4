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
import dao.ProprietarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Proprietario;

/**
 *
 * @author desenv01
 */
public class ServerProprietarioDAO implements ProprietarioDAO {

    private LogradouroDAO dao;
    private IServer server;

    public ServerProprietarioDAO(IServer server) {
        this.server = server;
        dao = new ServerLogradouroDAO(server);
    }

    @Override
    public List<Proprietario> getProprietarios() {
        String stm = "select * from proprietario";
        List<Proprietario> props = new ArrayList<>();
        try {
            Response response = server.executeCommand(stm);
            Iterator<Line> it = response.iterator();
            while (it.hasNext()) {
                props.add(fillProprietario(it.next()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommandException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    @Override
    public int findIdByNome(String nome) {
        try {
            String stm = "select codigo from proprietario where nome = '" + nome + "'";
            Response response = server.executeCommand(stm);
            Iterator<Line> it = response.iterator();
            if (it.hasNext()) {
                return it.next().getInt(1);
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Proprietario findById(int id) {
        String stm = "select * from proprietario where codigo=" + id;
        try {
            Response response = server.executeCommand(stm);
            Iterator<Line> it = response.iterator();
            if (it.hasNext()) {
                return fillProprietario(it.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommandException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insertProprietario(Proprietario proprietario) {
        try {
            StringBuilder sb = new StringBuilder("insert into proprietario (codigo,nome,identidade,codlog,numero,telefone,email) values (");
            sb.append(getNextId());
            sb.append(",'");
            sb.append(proprietario.getNome());
            sb.append("','");
            sb.append(proprietario.getIdentidade());
            sb.append("',");
            sb.append(proprietario.getEndereco().getCodlog());
            sb.append(",");
            sb.append(proprietario.getNumero());
            sb.append(",'");
            sb.append(proprietario.getTel());
            sb.append("','");
            sb.append(proprietario.getEmail());
            sb.append("')");
            server.executeCommand(sb.toString());
        } catch (CommandException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteProprietario(int id) {
        try {
            String stm = "delete from proprietario where codigo="+id;
            server.executeCommand(stm);
        } catch (CommandException ex) {
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() throws SQLException {
        try {
            String stm = "select max(codigo) from proprietario";
            Response response = server.executeCommand(stm);
            Iterator<Line> it = response.iterator();
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
            Logger.getLogger(ServerProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    private Proprietario fillProprietario(Line line) throws SQLException {
        Proprietario pro = new Proprietario();

        pro.setCodigo(line.getInt("codigo"));
        pro.setNome(line.getString("nome"));
        pro.setIdentidade(line.getString("identidade"));
        pro.setEndereco(dao.findById(line.getInt("codlog")));
        pro.setNumero(line.getInt("numero"));
        pro.setTel(line.getString("telefone"));
        pro.setEmail(line.getString("email"));

        return pro;
    }
}
