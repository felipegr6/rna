package br.fgr.rna;

public class NeuronioSaida extends Neuronio {

    @Override
    public double getSaida() {
        return Functions.logistica(getEntrada());
    }

}
