/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import modelo.Proprietario;

/**
 *
 * @author Administrador
 */
public class TableModelProprietario extends AbstractTableModel<Proprietario> {

    public TableModelProprietario() {
        super(new String[]{"Id", "Nome", "Identidade", "Endereco", "Numero", "Telefone", "Email"});
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
                    return tmp.getIdentidade();
                case 3:
                    return tmp.getEndId();
                case 4:
                    return tmp.getNumero();
                case 5:
                    return tmp.getTel();
                case 6:
                    return tmp.getEmail();
            }
        }
        return null;
    }
}
