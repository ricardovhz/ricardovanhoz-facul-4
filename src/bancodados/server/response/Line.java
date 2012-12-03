/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server.response;

import java.util.Date;

/**
 *
 * @author desenv01
 */
public interface Line {
        public Object getObject(int columnIndex);
        public String getString(int columnIndex);
        public int getInt(int columnIndex);
        public Date getDate(int columnIndex);
        public double getDouble(int columnIndex);
        
        public Object getObject(String columnName);
        public String getString(String columnName);
        public int getInt(String columnName);
        public Date getDate(String columnName);
        public double getDouble(String columnName);
}
