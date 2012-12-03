/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.jdbc;

import bancodados.IBanco;
import java.sql.Connection;

/**
 *
 * @author 808012
 */
public abstract class BancoDados implements IBanco {

    private String driverJDBC;
    private String dataBase;
    private Connection conn;

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

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
