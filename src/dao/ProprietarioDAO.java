/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Proprietario;

/**
 *
 * @author Administrador
 */
public class ProprietarioDAO {

    private Connection conn;

    public ProprietarioDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Proprietario> getProprietarios() {

        List<Proprietario> result = new ArrayList<Proprietario>();

        try {
            Statement st = conn.createStatement();
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

    public void insertProprietario(Proprietario proprietario) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into proprietario values (?,?,?,?,?,?)");
            st.setInt(1, getNextId());
            st.setString(2, proprietario.getNome());
            st.setInt(3, proprietario.getEndId());
            st.setInt(4, proprietario.getNumero());
            st.setString(5, proprietario.getTel());
            st.setString(6, proprietario.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select max(codigo) from proprietario");
        if (rs.first()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public Proprietario fillProprietario(ResultSet rs) throws SQLException {
        Proprietario pro = new Proprietario();

        pro.setCodigo(rs.getInt("codigo"));
        pro.setNome(rs.getString("nome"));
        pro.setEndId(rs.getInt("end_id"));
        pro.setNumero(rs.getInt("numero"));
        pro.setTel(rs.getString("tel"));
        pro.setEmail(rs.getString("email"));

        return pro;
    }
}
