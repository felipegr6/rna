package br.fgr.rna;

public abstract class Neuronio {

    private double entrada;

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public double getEntrada() {
        return entrada;
    }

    public abstract double getSaida();

}
