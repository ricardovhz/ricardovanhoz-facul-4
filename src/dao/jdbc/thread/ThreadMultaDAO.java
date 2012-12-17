/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc.thread;

import bancodados.jdbc.thread.ThreadBanco;
import dao.MultaDAO;
import dao.ProprietarioDAO;
import dao.VeiculoDAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Multa;
import modelo.Proprietario;
import modelo.Veiculo;
import tablemodel.AbstractTableModel;

/**
 *
 * @author desenv01
 */
public class ThreadMultaDAO extends AbstractThreadDAO implements MultaDAO {

    private ProprietarioDAO proprietarioDAO;
    private VeiculoDAO veiculoDAO;

    public ThreadMultaDAO(ThreadBanco banco) {
        super(banco);
        this.proprietarioDAO = new ThreadProprietarioDAO(banco);
        this.veiculoDAO = new ThreadVeiculoDAO(banco);
    }

    @Override
    public List<Multa> getMultas() {
        List<Multa> multas = new ArrayList<>();
        try {

            String stat = "select codigo,codpro,codvei,data,pontuacao,tipo from multa;";
            ResultSet rs = banco.executeSelect(stat);

            while (rs.next()) {
                multas.add(fillMulta(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return multas;
    }

    @Override
    public List<Multa> findByProprietario(String nomeProprietario) {
        List<Multa> multas = new ArrayList<>();
        try {
            String stat = "select m.codigo,m.codpro,m.codvei,m.data,m.pontuacao,m.tipo from multa m, proprietario p where m.codpro=p.codigo and p.nome like '%" + nomeProprietario + "%'";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                multas.add(fillMulta(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return multas;
    }

    @Override
    public List<Multa> findByVeiculo(String descricaoVeiculo) {
        List<Multa> multas = new ArrayList<>();
        try {
            String stat = "select m.codigo,m.codpro,m.codvei,m.data,m.pontuacao,m.tipo from multa m, veiculo v where m.codvei=v.codigo and v.descr like '%" + descricaoVeiculo + "%'";
            ResultSet rs = banco.executeSelect(stat);
            while (rs.next()) {
                multas.add(fillMulta(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThreadMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return multas;
    }

    @Override
    public void insertMulta(Multa multa) {
        try {
            StringBuilder st = new StringBuilder("insert into multa (codigo,codpro,codvei,data,pontuacao,tipo) values (");
            st.append(getNextId());
            st.append(",");
            st.append(multa.getProprietario().getCodigo());
            st.append(",");
            st.append(multa.getVeiculo().getCodigo());
            st.append(",'");
            st.append(new Date(multa.getData().getTime()));
            st.append("',");
            st.append(multa.getPontuacao());
            st.append(",'");
            st.append(multa.getTipo().name());
            st.append("')");
            banco.executeUpdate(st.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ThreadMultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteMulta(int codigo) {
        String stat = "delete from multa where codigo=" + codigo;
        banco.executeUpdate(stat);
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
        String stat = "select max(codigo) from multa";
        ResultSet rs = banco.executeSelect(stat);
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }

        return 1;
    }

    public Multa fillMulta(ResultSet rs) throws SQLException {
        Multa multa = new Multa();

        multa.setCodigo(rs.getInt(1));
        multa.setProprietario(getProprietarioFromId(rs.getInt(2)));
        multa.setVeiculo(getVeiculoFromId(rs.getInt(3)));
        multa.setData(rs.getDate(4));
        multa.setPontuacao(rs.getInt(5));
        multa.setTipo(Multa.tipoPontuacao.valueOf(rs.getString(6)));
        return multa;
    }
}
