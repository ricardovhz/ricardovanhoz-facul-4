/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public abstract class AbstractTableModel<T> extends javax.swing.table.AbstractTableModel {

    protected String[] columns;
    protected List<T> linhas;

    public List<T> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<T> linhas) {
        this.linhas = linhas;
        fireTableDataChanged();
    }
    
    public AbstractTableModel() {
        super();
        linhas = new ArrayList<T>();
    }
    
    public AbstractTableModel(String[] columns) {
        this();
        this.columns = columns;
    }
    
    public void add(T item) {
        if (item != null) {
            linhas.add(item);
            fireTableDataChanged();
        }
    }

    @Override
    public int getRowCount() {
        if (linhas != null) {
            return linhas.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public abstract Object getValueAt(int rowIndex, int columnIndex);
}
