package br.fgr.rna;

public class NeuronioEntrada extends Neuronio {

    @Override
    public double getSaida() {
        return getEntrada() * 1.0;
    }
}
