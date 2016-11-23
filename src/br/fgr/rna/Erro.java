package br.fgr.rna;

import java.util.List;

public class Erro {

    private double saida;
    private double erro;

    public Erro(double saida, double erro) {
        this.saida = saida;
        this.erro = erro;
    }

    public double getSaida() {
        return saida;
    }

    public double getErro() {
        return erro;
    }

    public static double erroRMS(List<Erro> erros, int numTreinamentos) {
        double erroQuadratico = 0.0;
        for (Erro e : erros) {
            erroQuadratico += Math.pow(e.getErro(), 2.0);
        }
        return erroQuadratico / (2.0 * numTreinamentos);
    }

    @Override
    public String toString() {
        return "Erro{" +
                "saida=" + saida +
                ", erro=" + erro +
                '}';
    }
}
