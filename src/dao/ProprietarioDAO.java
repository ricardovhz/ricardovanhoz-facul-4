/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bancodados.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Proprietario;

/**
 *
 * @author Administrador
 */
public class ProprietarioDAO {

    private LogradouroDAO dao;
    private BancoDados banco;

    public ProprietarioDAO(BancoDados banco) {
        this.banco = banco;
        dao = new LogradouroDAO(banco);
    }

    public List<Proprietario> getProprietarios() {

        List<Proprietario> result = new ArrayList<Proprietario>();

        try {
            Statement st = banco.getConn().createStatement();
            ResultSet rs = st.executeQuery("select * from proprietario");

            while (rs.next()) {
                result.add(fillProprietario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    public int findIdByNome(String nome) {
        final String sql = "select codigo from proprietario where nome = ?";

        try {
            PreparedStatement st = banco.getConn().prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public Proprietario findById(int id) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("select * from proprietario where codigo=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return fillProprietario(rs);
            else 
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    public void insertProprietario(Proprietario proprietario) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("insert into proprietario (codigo,nome,identidade,codlog,numero,telefone,email) values (?,?,?,?,?,?,?)");
            st.setInt(1, getNextId());
            st.setString(2, proprietario.getNome());
            st.setString(3, proprietario.getIdentidade());
            st.setInt(4, proprietario.getEndereco().getCodlog());
            st.setInt(5, proprietario.getNumero());
            st.setString(6, proprietario.getTel());
            st.setString(7, proprietario.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProprietario(int id) {
        try {
            PreparedStatement st = banco.getConn().prepareStatement("delete from proprietario where codigo=?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNextId() throws SQLException {
        Statement st = banco.getConn().createStatement();
        ResultSet rs = st.executeQuery("select max(codigo) from proprietario");
        if (rs.first()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

    public Proprietario fillProprietario(ResultSet rs) throws SQLException {
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
