/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Multa {

    public static enum tipoPontuacao {

        LEVE,
        LEVISSIMA,
        GRAVE,
        GRAVISSIMA,
        MEDIA
    }
    private int codigo;
    private Date data;
    private int pontuacao;
    private tipoPontuacao tipo;
    private Proprietario proprietario;
    private Veiculo veiculo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public tipoPontuacao getTipo() {
        return tipo;
    }

    public void setTipo(tipoPontuacao tipo) {
        this.tipo = tipo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
