/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.server;

import bancodados.server.CommandException;
import bancodados.server.IServer;
import bancodados.server.response.Line;
import bancodados.server.response.Response;
import dao.ProprietarioDAO;
import dao.VeiculoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Proprietario;
import modelo.Veiculo;

/**
 *
 * @author desenv01
 */
public class ServerVeiculoDAO extends AbstractServerDAO implements VeiculoDAO {
    
    private ProprietarioDAO dao;

    public ServerVeiculoDAO(IServer server) {
        super(server);
        this.dao = new ServerProprietarioDAO(server);
    }

    @Override
    public List<Veiculo> getVeiculos() {
        List<Veiculo> result = new ArrayList<>();
        String stm = "select * from veiculo";
        try {
            Response response = server.executeCommand(stm);
            Iterator<Line> it = response.iterator();
            while (it.hasNext()) {
                result.add(fillVeiculo(it.next()));
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void insertVeiculo(Veiculo veiculo) {
        try {
            StringBuilder sb = new StringBuilder("insert into veiculo (codigo,descr,placa,chassi,codpro) values (");
            sb.append(getNextId());
            sb.append(",'");
            sb.append(veiculo.getDescricao());
            sb.append("','");
            sb.append(veiculo.getPlaca());
            sb.append("',");
            sb.append(veiculo.getChassi());
            sb.append(",");
            sb.append(veiculo.getProprietario().getCodigo());
            sb.append(")");
            server.executeCommand(sb.toString());
        } catch (CommandException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteVeiculo(int id) {
        try {
            String stm = "delete from veiculo where codigo="+id;
            server.executeCommand(stm);
        } catch (CommandException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() throws SQLException {
        try {
            String stm = "select max(codigo) from veiculo";
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
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Proprietario getProprietarioFromId(int id) {
        return dao.findById(id);
    }

    @Override
    public Veiculo findById(int id) {
        try {
            String stm = "select * from veiculo where codigo="+id;
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            if (it.hasNext()) {
                return fillVeiculo(it.next());
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Veiculo findByDescricao(String descricao) {
        try {
            String stm = "select * from veiculo where descr='"+descricao+"'";
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            if (it.hasNext()) {
                return fillVeiculo(it.next());
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int findIdByDescricao(String descricao) {
        try {
            String stm = "select id from veiculo where descr='"+descricao+"'";
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            if (it.hasNext()) {
                return it.next().getInt(1);
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Veiculo fillVeiculo(Line line) {
        Veiculo vei = new Veiculo();

        vei.setCodigo(line.getInt("codigo"));
        vei.setDescricao(line.getString("descr"));
        vei.setChassi(line.getString("chassi"));
        vei.setPlaca(line.getString("placa"));
        vei.setProprietario(getProprietarioFromId(line.getInt("codpro")));

        return vei;
    }

    @Override
    public List<Veiculo> findAllByDescricao(String descricao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
