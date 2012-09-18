/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bancodados.BancoDados;
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
public class MultaDAO {

    private BancoDados banco;
    private ProprietarioDAO daoProprietario;
    private VeiculoDAO daoVeiculo;

    public MultaDAO(BancoDados banco) {
        this.banco = banco;
        daoProprietario = new ProprietarioDAO(banco);
        daoVeiculo = new VeiculoDAO(banco);
    }

    public List<Multa> getMultas() {

        List<Multa> result = new ArrayList<Multa>();

        try {
            Statement st = banco.getConn().createStatement();
            ResultSet rs = st.executeQuery("select * from multa");

            while (rs.next()) {
                result.add(fillMulta(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    public void insertMulta(Multa multa) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("insert into multa values (?,?,?,?,?)");
            st.setInt(1, multa.getProprietario().getCodigo());
            st.setInt(2, multa.getVeiculo().getCodigo());
            st.setDate(3, new Date(multa.getData().getTime()));
            st.setInt(4, multa.getPontuacao());
            st.setString(5, multa.getTipo().name());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Multa fillMulta(ResultSet rs) throws SQLException {
        Multa multa = new Multa();

        multa.setProprietario(getProprietarioFromId(rs.getInt(1)));
        multa.setVeiculo(getVeiculoFromId(rs.getInt(2)));
        multa.setData(rs.getDate(3));
        multa.setPontuacao(rs.getInt(4));
        multa.setTipo(Multa.tipoPontuacao.valueOf(rs.getString(5)));
        return multa;
    }

    private Proprietario getProprietarioFromId(int id) {
        return daoProprietario.findById(id);
    }

    private Veiculo getVeiculoFromId(int id) {
        return daoVeiculo.findById(id);
    }
}
