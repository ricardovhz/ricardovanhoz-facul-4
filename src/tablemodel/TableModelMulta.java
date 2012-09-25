/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import modelo.Multa;

/**
 *
 * @author Administrador
 */
public class TableModelMulta extends AbstractTableModel<Multa> {

    public TableModelMulta() {
        super(new String[]{"Proprietario", "Veiculo", "Data", "Pontuação","Tipo"});
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
