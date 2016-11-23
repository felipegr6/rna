package br.fgr.rna;

import java.util.*;

public class RedeNeural {

    private Map<Integer, List<Neuronio>> rede;

    private int numEntradas;
    private int numEscondidas;
    private int numSaidas;

    private double taxaAprendizado;

    private double pesosEntrEscond[][];
    private double pesosEscondSaidas[][];

    private double deltaSaida[];

    public RedeNeural(int numEntradas, int numEscondidas, int numSaidas, double taxaAprendizado) {
        this.numEntradas = numEntradas;
        this.numEscondidas = numEscondidas;
        this.numSaidas = numSaidas;
        this.taxaAprendizado = taxaAprendizado;
        this.rede = new HashMap<>();
        this.deltaSaida = new double[numSaidas];

        init();
    }

    private void init() {

        List<Neuronio> entradas = new ArrayList<>();
        List<Neuronio> intermediarias = new ArrayList<>();
        List<Neuronio> saidas = new ArrayList<>();

        for (int i = 0; i < numEntradas; i++)
            entradas.add(new NeuronioEntrada());

        for (int i = 0; i < numEscondidas; i++)
            intermediarias.add(new NeuronioSaida());

        for (int i = 0; i < numSaidas; i++)
            saidas.add(new NeuronioSaida());

        rede.put(1, entradas);
        rede.put(2, intermediarias);
        rede.put(3, saidas);

        Random random;

        pesosEntrEscond = new double[numEntradas][numEscondidas];
        pesosEscondSaidas = new double[numEscondidas][numSaidas];

        random = new Random(System.currentTimeMillis());

        for (int i = 0; i < numEntradas; i++) {
            for (int j = 0; j < numEscondidas; j++) {
                pesosEntrEscond[i][j] = random.nextBoolean() ? random.nextDouble() : -1.0 * random.nextDouble();
            }
        }

        for (int k = 0; k < numEscondidas; k++) {
            for (int l = 0; l < numSaidas; l++) {
                pesosEscondSaidas[k][l] = random.nextBoolean() ? random.nextDouble() : -1.0 * random.nextDouble();
            }
        }

    }

    public double[] execucao(double entrada[]) {

        double alvoNulo[] = new double[numSaidas];
        double saida[] = new double[numSaidas];
        List<Erro> saidas = execucao(entrada, alvoNulo);

        for (int i = 0; i < numSaidas; i++)
            saida[i] = saidas.get(i).getSaida();

        return saida;
    }

    public List<Erro> aprendizado(double entrada[], double alvo[]) {

        // Forward Step
        List<Erro> executado = execucao(entrada, alvo);
        double deltaSaidas[] = new double[numSaidas];
        double deltaEscondidas[] = new double[numEscondidas];

        // Backward Step

        // Lista de deltas
        for (int i = 0; i < numSaidas; i++) {
            Erro e = executado.get(i);
            deltaSaidas[i] = e.getErro() * e.getSaida() * (1.0 - e.getSaida());
        }
        // Variacao do peso
        for (int i = 0; i < numSaidas; i++) {
            for (int j = 0; j < numEscondidas; j++) {
                double variacaoPeso = taxaAprendizado * deltaSaidas[i] * rede.get(2).get(j).getSaida();
                pesosEscondSaidas[j][i] += variacaoPeso;
            }
        }

        //Lista de deltas
        for (int i = 0; i < numEscondidas; i++) {
            double fator = 0.0;
            for (int j = 0; j < numSaidas; j++) {
                fator += deltaSaidas[j] * pesosEscondSaidas[i][j];
            }
            deltaEscondidas[i] = rede.get(2).get(i).getSaida() * (1 - rede.get(2).get(i).getSaida()) * fator;
        }
        // Variacao do peso
        for (int i = 0; i < numEntradas; i++) {
            for (int j = 0; j < numEscondidas; j++) {
                double variacaoPeso = taxaAprendizado * deltaEscondidas[j] * rede.get(1).get(i).getEntrada();
                pesosEntrEscond[i][j] += variacaoPeso;
            }
        }

        return executado;

    }

    private List<Erro> execucao(double entrada[], double alvo[]) {

        List<Erro> erros = new ArrayList<>();

        for (int i = 0; i < numEntradas; i++)
            rede.get(1).get(i).setEntrada(entrada[i]);

        for (int i = 0; i < numEscondidas; i++) {
            double somatoria = 0.0;
            for (int j = 0; j < numEntradas; j++)
                somatoria += pesosEntrEscond[j][i] * rede.get(1).get(j).getSaida();
            rede.get(2).get(i).setEntrada(somatoria);
        }

        for (int i = 0; i < numSaidas; i++) {
            double somatoria = 0.0;
            for (int j = 0; j < numEscondidas; j++) {
                somatoria += pesosEscondSaidas[j][i] * rede.get(2).get(j).getSaida();
            }
            rede.get(3).get(i).setEntrada(somatoria);
            erros.add(new Erro(rede.get(3).get(i).getSaida(), alvo[i] - rede.get(3).get(i).getSaida()));
        }

        return erros;

    }

}
