/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 808012
 */
public class MySql {

    private String driverJDBC;
    private String dataBase;
    private Connection conn;
    private Statement statement;

    public MySql() {
        this.driverJDBC = "com.mysql.jdbc.Driver";
        this.dataBase = "jdbc:mysql://localhost/808012";
    }

    public static void main(String[] args) {
        MySql sql = new MySql();
        try {
            Driver drv;
            drv = (Driver) Class.forName(sql.driverJDBC).newInstance();
            Connection conn = drv.connect(sql.dataBase, null);
            conn.close();
            System.out.println("Certo!");

        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MySql(String driverJDBC, String dataBase) {
        this.driverJDBC = driverJDBC;
        this.dataBase = dataBase;
    }

    public String getDriverJDBC() {
        return driverJDBC;
    }

    public void setDriverJDBC(String driverJDBC) {
        this.driverJDBC = driverJDBC;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }
}
