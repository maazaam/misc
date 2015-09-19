package aialgo;

public class BackProp {

    private int ni;
    private int nh;
    private int no;
    private double[] ai;
    private double[] ah;
    private double[] ao;
    private double[][] wi;
    private double[][] wo;
    private double[][] ci;
    private double[][] co;

    public BackProp(int ni, int nh, int no) throws Exception {
        this.ni = ni + 1;
        this.nh = nh;
        this.no = no;
        this.ai = new double[this.ni];
        this.ah = new double[this.nh];
        this.ao = new double[this.no];
        this.wi = new double[this.ni][this.nh];
        this.wo = new double[this.nh][this.no];
        this.ci = new double[this.ni][this.nh];
        this.co = new double[this.nh][this.no];
        for (int i = 0; i < this.ni; i++) {
            this.ai[i] = 1.0;
        }
        for (int j = 0; j < this.nh; j++) {
            this.ah[j] = 1.0;
        }
        for (int k = 0; k < this.no; k++) {
            this.ao[k] = 1.0;
        }
        for (int i = 0; i < this.ni; i++) {
            for (int j = 0; j < this.nh; j++) {
                this.wi[i][j] = random(0.0, 1.0);
            }
        }
        for (int j = 0; j < this.nh; j++) {
            for (int k = 0; k < this.no; k++) {
                this.wo[j][k] = random(0.0, 1.0);
            }
        }
    }

    private double random(double a, double b) throws Exception {
        return (b - a) * Math.random() + a;
    }

    private double activate(double x) throws Exception {
        return Math.tanh(x);
    }

    private double derivate(double y) throws Exception {
        return 1.0 - Math.pow(y, 2.0);
    }

    public double[] output(double[] ip) throws Exception {
        for (int i = 0; i < ni - 1; i++) {
            ai[i] = ip[i];
        }
        for (int j = 0; j < nh; j++) {
            double s = 0.0;
            for (int i = 0; i < ni; i++) {
                s += ai[i] * wi[i][j];
            }
            ah[j] = activate(s);
        }
        for (int k = 0; k < no; k++) {
            double s = 0.0;
            for (int j = 0; j < nh; j++) {
                s += ah[j] * wo[j][k];
            }
            ao[k] = activate(s);
        }
        return ao;
    }

    public double error(double[] to, double[] ao) throws Exception {
        double e = 0.0;
        for (int k = 0; k < no; k++) {
            e += Math.pow(to[k] - ao[k], 2.0);
        }
        return e / 2.0;
    }

    public void adjust(double[] to, double r, double m) throws Exception {
        double[] od = new double[no];
        for (int k = 0; k < no; k++) {
            double e = to[k] - ao[k];
            od[k] = derivate(ao[k]) * e;
        }
        double[] hd = new double[nh];
        for (int j = 0; j < nh; j++) {
            double e = 0.0;
            for (int k = 0; k < no; k++) {
                e += od[k] * wo[j][k];
            }
            hd[j] = derivate(ah[j]) * e;
        }
        for (int j = 0; j < nh; j++) {
            for (int k = 0; k < no; k++) {
                double c = od[k] * ah[j];
                wo[j][k] += r * c + m * co[j][k];
                co[j][k] = c;
            }
        }
        for (int i = 0; i < ni; i++) {
            for (int j = 0; j < nh; j++) {
                double c = hd[j] * ai[i];
                wi[i][j] += r * c + m * ci[i][j];
                ci[i][j] = c;
            }
        }
    }

    public double[][] iweight() throws Exception {
        return wi;
    }

    public double[][] oweight() throws Exception {
        return wo;
    }

    public void show(double[][] ar) throws Exception {
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                System.out.print(ar[i][j]);
                if (j < ar[i].length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.print("\n");
                }
            }
        }
    }

    public void train(double[][] ips, double[][] ops, double r, double m, int x) throws Exception {
        System.out.println("---------------training---------------");
        for (int i = 0; i < x; i++) {
            boolean d = true;
            for (int j = 0; j < ips.length; j++) {
                double[] op = output(ips[j]);
                double e = error(ops[j], op);
                if (e != 0.0) {
                    d = false;
                    adjust(ops[j], r, m);
                }
            }
            if (d) {
                break;
            }
        }
        System.out.println("---------------iweight---------------");
        double[][] wi = iweight();
        show(wi);
        System.out.println("---------------oweight---------------");
        double[][] wo = oweight();
        show(wo);
    }

    public void test(double[][] ips) throws Exception {
        System.out.println("---------------testing---------------");
        System.out.println("---------------output---------------");
        double[][] ops = new double[ips.length][no];
        for (int i = 0; i < ips.length; i++) {
            double[] op = output(ips[i]);
            for (int j = 0; j < op.length; j++) {
                ops[i][j] = op[j];
            }
        }
        show(ops);
    }

    public void run(double[][] ips) throws Exception {
        System.out.println("---------------running---------------");
        System.out.println("---------------output---------------");
        double[][] ops = new double[ips.length][no];
        for (int i = 0; i < ips.length; i++) {
            double[] op = output(ips[i]);
            for (int j = 0; j < op.length; j++) {
                ops[i][j] = op[j];
            }
        }
        show(ops);
    }

    public static void main(String[] args) throws Exception {
        double[][] ips = { { -1.0, -1.0 }, { -1.0, 1.0 }, { 1.0, -1.0 }, { 1.0, 1.0 } };
        double[][] ops = { { -1.0 }, { 1.0 }, { 1.0 }, { -1.0 } };
        double r = 0.5;
        double m = 0.1;
        int x = 100000;
        int i = 2;
        int h = 2;
        int o = 1;
        BackProp bp = new BackProp(i, h, o);
        bp.train(ips, ops, r, m, x);
        bp.test(ips);
        bp.run(ips);
    }
}
