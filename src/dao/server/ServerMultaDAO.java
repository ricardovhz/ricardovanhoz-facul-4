/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.server;

import bancodados.server.CommandException;
import bancodados.server.IServer;
import bancodados.server.response.Line;
import bancodados.server.response.Response;
import dao.MultaDAO;
import dao.ProprietarioDAO;
import dao.VeiculoDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Multa;
import modelo.Proprietario;
import modelo.Veiculo;

/**
 *
 * @author desenv01
 */
public class ServerMultaDAO extends AbstractServerDAO implements MultaDAO {

    private ProprietarioDAO proprietarioDAO;
    private VeiculoDAO veiculoDAO;

    public ServerMultaDAO(IServer server) {
        super(server);
        this.proprietarioDAO = new ServerProprietarioDAO(server);
        this.veiculoDAO = new ServerVeiculoDAO(server);
    }

    @Override
    public List<Multa> getMultas() {
        List<Multa> list = new ArrayList<>();
        String stm = "select * from multa";
        try {
            Response result = server.executeCommand(stm);
            Iterator<Line> it = result.iterator();
            while (it.hasNext()) {
                list.add(fillMulta(it.next()));
            }
        } catch (CommandException ex) {
            Logger.getLogger(ServerMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public void insertMulta(Multa multa) {
        try {
            StringBuilder sb = new StringBuilder("insert into multa (codigo,codpro,codvei,data,pontuacao,tipo) values (");
            sb.append(getNextId());
            sb.append(",");
            sb.append(multa.getProprietario().getCodigo());
            sb.append(",");
            sb.append(multa.getVeiculo().getCodigo());
            sb.append(",'");
            sb.append(new Date(multa.getData().getTime()));
            sb.append("',");
            sb.append(multa.getPontuacao());
            sb.append(",'");
            sb.append(multa.getTipo().name());
            sb.append("')");
            server.executeCommand(sb.toString());
        } catch (CommandException ex) {
            Logger.getLogger(ServerMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteMulta(int codigo) {
        try {
            String stm = "delete from multa where codigo=" + codigo;
            server.executeCommand(stm);
        } catch (CommandException ex) {
            Logger.getLogger(ServerMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Proprietario getProprietarioFromId(int id) {
        return proprietarioDAO.findById(id);
    }

    @Override
    public Veiculo getVeiculoFromId(int id) {
        return veiculoDAO.findById(id);
    }

    @Override
    public int getNextId() throws SQLException {
        try {
            String stm = "select max(codigo) from multa";
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
            Logger.getLogger(ServerMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Multa fillMulta(Line line) {
        Multa multa = new Multa();

        multa.setCodigo(line.getInt("codigo"));
        multa.setProprietario(getProprietarioFromId(line.getInt("codpro")));
        multa.setVeiculo(getVeiculoFromId(line.getInt("codvei")));
        multa.setData(line.getDate("data"));
        multa.setPontuacao(line.getInt("pontuacao"));
        multa.setTipo(Multa.tipoPontuacao.valueOf(line.getString("tipo")));
        return multa;

    }
}
