/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class CsvLine implements Line {

    private String[] columns;
    private String[] line;

    public CsvLine(String line,String[] columns) {
        this.line = line.split(",");
        this.columns = columns;
    }

    @Override
    public String getString(int columnIndex) {
        return line[columnIndex - 1];
    }

    @Override
    public Object getObject(int columnIndex) {
        return getString(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) {
        return Integer.parseInt(getString(columnIndex));
    }

    @Override
    public double getDouble(int columnIndex) {
        return Double.parseDouble(getString(columnIndex));
    }
    
    private int getColumnIndex(String name) {
        int i=0;
        for (String column : columns) {
            if (column.equals(name)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public String getString(String columnName) {
        return line[getColumnIndex(columnName)];
    }

    @Override
    public Object getObject(String columnName) {
        return getString(columnName);
    }

    @Override
    public int getInt(String columnName) {
        return Integer.parseInt(getString(columnName));
    }

    @Override
    public double getDouble(String columnName) {
        return Double.parseDouble(getString(columnName));
    }

    @Override
    public Date getDate(int columnIndex) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(getString(columnIndex));
        } catch (ParseException ex) {
            Logger.getLogger(CsvLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Date getDate(String columnName) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(getString(columnName));
        } catch (ParseException ex) {
            Logger.getLogger(CsvLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
