/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import bancodados.jdbc.BancoDados;
import dao.MultaDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Multa;
import modelo.Proprietario;
import modelo.Veiculo;

/**
 *
 * @author Administrador
 */
public class JDBCMultaDAO implements MultaDAO {

    private BancoDados banco;
    private JDBCProprietarioDAO daoProprietario;
    private JDBCVeiculoDAO daoVeiculo;

    public JDBCMultaDAO(BancoDados banco) {
        this.banco = banco;
        daoProprietario = new JDBCProprietarioDAO(banco);
        daoVeiculo = new JDBCVeiculoDAO(banco);
    }

    @Override
    public List<Multa> getMultas() {

        List<Multa> result = new ArrayList<Multa>();

        try {
            Statement st = banco.getConn().createStatement();
            ResultSet rs = st.executeQuery("select codigo,codpro,codvei,data,pontuacao,tipo from multa");

            while (rs.next()) {
                result.add(fillMulta(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public void insertMulta(Multa multa) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("insert into multa (codigo,codpro,codvei,data,pontuacao,tipo) values (?,?,?,?,?,?)");
            st.setInt(1, getNextId());
            st.setInt(2, multa.getProprietario().getCodigo());
            st.setInt(3, multa.getVeiculo().getCodigo());
            st.setDate(4, new Date(multa.getData().getTime()));
            st.setInt(5, multa.getPontuacao());
            st.setString(6, multa.getTipo().name());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteMulta(int codigo) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("delete from multa where codigo=?");
            st.setInt(1, codigo);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public Proprietario getProprietarioFromId(int id) {
        return daoProprietario.findById(id);
    }

    @Override
    public Veiculo getVeiculoFromId(int id) {
        return daoVeiculo.findById(id);
    }

    @Override
    public int getNextId() throws SQLException {
        Statement st = banco.getConn().createStatement();
        ResultSet rs = st.executeQuery("select max(codigo) from multa");
        if (rs.first()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

}
