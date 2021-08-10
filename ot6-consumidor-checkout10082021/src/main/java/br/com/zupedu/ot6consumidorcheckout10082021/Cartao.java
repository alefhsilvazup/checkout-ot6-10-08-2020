package br.com.zupedu.ot6consumidorcheckout10082021;

public class Cartao {

    private String numero, nome, bandeira;
    private Integer cvv;

    public Cartao() {
    }

    public Cartao(String numero, String nome, String bandeira, Integer cvv) {
        this.numero = numero;
        this.nome = nome;
        this.bandeira = bandeira;
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "numero='" + numero + '\'' +
                ", nome='" + nome + '\'' +
                ", bandeira='" + bandeira + '\'' +
                ", cvv=" + cvv +
                '}';
    }

    public String getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public Integer getCvv() {
        return cvv;
    }
}