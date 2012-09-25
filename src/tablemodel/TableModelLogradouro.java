/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import modelo.Logradouro;

/**
 *
 * @author Administrador
 */
public class TableModelLogradouro extends AbstractTableModel<Logradouro> {

    public TableModelLogradouro() {
        super(new String[]{"Código","Descrição","Bairro","Cep","Cidade"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linhas != null && linhas.size() >= rowIndex) {
            Logradouro tmp = linhas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return tmp.getCodlog();
                case 1:
                    return tmp.getDescricao();
                case 2:
                    return tmp.getBairro();
                case 3:
                    return tmp.getCep();
                case 4:
                    return tmp.getCidade();
            }
        }
        return null;
    }
}
