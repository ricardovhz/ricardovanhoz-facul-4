/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 808012
 */
public class Veiculo {

    int codigo;
    String descricao;
    double chassi;
    int codpro;

    public Veiculo() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getChassi() {
        return chassi;
    }

    public void setChassi(double chassi) {
        this.chassi = chassi;
    }

    public int getCodpro() {
        return codpro;
    }

    public void setCodpro(int codpro) {
        this.codpro = codpro;
    }
}
