/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 808012
 */
public class MySql extends BancoDados {

    public MySql() {
        setDriverJDBC("com.mysql.jdbc.Driver");
        setDataBase("jdbc:mysql://localhost/test");
        setConn(initConn());
    }

    public MySql(String driverJDBC, String dataBase) {
        setDriverJDBC(driverJDBC);
        setDataBase(dataBase);
        setConn(initConn());
    }

    private Connection initConn() {
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "rootroot");
        try {
            Driver drv;
            drv = (Driver) Class.forName(getDriverJDBC()).newInstance();
            return drv.connect(getDataBase(), props);

        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;

    }
}
