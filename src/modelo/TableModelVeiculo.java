/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrador
 */
public class TableModelVeiculo extends AbstractTableModel {

    private List<Veiculo> linhas;
    String[] columns = new String[]{"Id", "Descrição", "Chassi", "CodProprietario"};

    public List<Veiculo> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Veiculo> linhas) {
        this.linhas = linhas;
        fireTableDataChanged();
    }

    public TableModelVeiculo() {
        super();
        linhas = new ArrayList<Veiculo>();
    }

    public TableModelVeiculo(List linhas) {
        super();
        this.linhas = linhas;
    }

    public void add(Veiculo veiculo) {
        if (veiculo != null) {
            linhas.add(veiculo);
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linhas != null && linhas.size() >= rowIndex) {
            Veiculo tmp = linhas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return tmp.getCodigo();
                case 1:
                    return tmp.getDescricao();
                case 2:
                    return tmp.getChassi();
                case 3:
                    return tmp.getCodpro();
            }
        }
        return null;
    }
}
