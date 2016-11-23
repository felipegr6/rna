package br.fgr.rna;

public final class Functions {

    private Functions() {

    }

    public static double logistica(double entrada) {
        return 1.0 / (1.0 + Math.exp(-entrada));
    }

}
