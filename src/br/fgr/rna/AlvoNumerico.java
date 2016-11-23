package br.fgr.rna;

public enum AlvoNumerico implements Listagem {
    ZERO {
        @Override
        public double[] getListagem() {
            return new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }, UM {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }, DOIS {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }, TRES {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }, QUATRO {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }, CINCO {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        }
    }, SEIS {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
        }
    }, SETE {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
        }
    }, OITO {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
        }
    }, NOVE {
        @Override
        public double[] getListagem() {
            return new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        }
    }, INVALIDO {
        @Override
        public double[] getListagem() {
            return new double[0];
        }
    };

    AlvoNumerico() {

    }
    public static AlvoNumerico getAlvo(int numero) {
        switch (numero) {
            case 0: return ZERO;
            case 1: return UM;
            case 2: return DOIS;
            case 3: return TRES;
            case 4: return QUATRO;
            case 5: return CINCO;
            case 6: return SEIS;
            case 7: return SETE;
            case 8: return OITO;
            case 9: return NOVE;
            default: return INVALIDO;
        }
    }
}
