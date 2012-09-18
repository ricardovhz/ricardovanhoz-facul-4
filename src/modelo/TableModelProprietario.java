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
public class TableModelProprietario extends AbstractTableModel {

    private List<Proprietario> linhas;
    String[] columns = new String[]{"Id", "Nome", "Endereco", "Numero", "Telefone", "Email"};

    public List<Proprietario> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Proprietario> linhas) {
        this.linhas = linhas;
        fireTableDataChanged();
    }

    public TableModelProprietario() {
        super();
        linhas = new ArrayList<Proprietario>();
    }

    public TableModelProprietario(List linhas) {
        super();
        this.linhas = linhas;
    }

    public void add(Proprietario proprietario) {
        if (proprietario != null) {
            linhas.add(proprietario);
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
            Proprietario tmp = linhas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return tmp.getCodigo();
                case 1:
                    return tmp.getNome();
                case 2:
                    return tmp.getEndId();
                case 3:
                    return tmp.getNumero();
                case 4:
                    return tmp.getTel();
                case 5:
                    return tmp.getEmail();
            }
        }
        return null;
    }
}
