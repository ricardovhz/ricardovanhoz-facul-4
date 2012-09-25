/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import modelo.Veiculo;

/**
 *
 * @author Administrador
 */
public class TableModelVeiculo extends AbstractTableModel<Veiculo> {

    public TableModelVeiculo() {
        super(new String[]{"Id", "Descrição", "Chassi", "CodProprietario"});
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
                    return tmp.getProprietario().getNome();
            }
        }
        return null;
    }
}
