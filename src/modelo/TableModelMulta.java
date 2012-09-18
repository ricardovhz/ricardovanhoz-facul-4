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
public class TableModelMulta extends AbstractTableModel {

    private List<Multa> linhas;
    String[] columns = new String[]{"Proprietario", "Veiculo", "Data", "Pontuação","Tipo"};

    public List<Multa> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Multa> linhas) {
        this.linhas = linhas;
        fireTableDataChanged();
    }

    public TableModelMulta() {
        super();
        linhas = new ArrayList<Multa>();
    }

    public TableModelMulta(List linhas) {
        super();
        this.linhas = linhas;
    }

    public void add(Multa multa) {
        if (multa != null) {
            linhas.add(multa);
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
            Multa tmp = linhas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return tmp.getProprietario().getNome();
                case 1:
                    return tmp.getVeiculo().getDescricao();
                case 2:
                    return tmp.getData();
                case 3:
                    return tmp.getPontuacao();
                case 4:
                    return tmp.getTipo().name();
            }
        }
        return null;
    }
}
