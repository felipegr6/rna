package br.fgr.rna;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RedeNeural rna = new RedeNeural(35, 3, 10, 0.5);
        List<Erro> erroTotal = new ArrayList<>();

        int count = 1;
        boolean stop = false;

        while (!stop) {
            for (int i = 1; i <= 3; i++) {
                erroTotal.clear();
                for (int j = 0; j < 10; j++) {
                    List<Erro> erro = rna.aprendizado(DadosTreinamento.getDado(i, j), AlvoNumerico.getAlvo(j).getListagem());
                    erroTotal.addAll(erro);
                }
                double erroRMS = Erro.erroRMS(erroTotal, 1);
                System.out.println(String.format("%s %s", String.valueOf(count), new DecimalFormat("0.0000")
                        .format(erroRMS).replace(".", ",")));
                if (erroRMS < 0.1) {
                    stop = true;
                    break;
                }
                count++;
            }
        }

        for (int d = 0; d < 10; d++) {
            double[] x = rna.execucao(DadosTreinamento.getDado(1, d));
            for (int i = 0; i < 10; i++)
                System.out.print(new DecimalFormat("0").format(x[i]) + " ");
            System.out.println("");
        }

    }
}
