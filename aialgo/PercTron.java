package aialgo;

public class PercTron {

    private int n;
    private double[] wt;

    public PercTron(int n) throws Exception {
        this.n = n;
        this.wt = new double[this.n];
    }

    public double output(double[] ip, double t, double h, double l) throws Exception {
        double s = 0.0;
        for (int i = 0; i < n; i++) {
            s += ip[i] * wt[i];
        }
        return s > t ? h : l;
    }

    public double error(double t, double a) throws Exception {
        return t - a;
    }

    public void adjust(double[] ip, double e, double r) throws Exception {
        for (int i = 0; i < n; i++) {
            wt[i] += ip[i] * e * r;
        }
    }

    public double[] weight() throws Exception {
        return wt;
    }

    public void train(double[][] ips, double[] op, double t, double r, double h, double l, int x) throws Exception {
        System.out.println("---------------training---------------");
        for (int i = 0; i < x; i++) {
            boolean d = true;
            for (int j = 0; j < ips.length; j++) {
                double o = output(ips[j], t, h, l);
                double e = error(op[j], o);
                if (e != 0.0) {
                    d = false;
                    adjust(ips[j], e, r);
                }
            }
            if (d) {
                break;
            }
        }
        System.out.println("---------------weight---------------");
        double[] wt = weight();
        for (int i = 0; i < wt.length; i++) {
            System.out.println(wt[i]);
        }
    }

    public void test(double[][] ips, double t, double h, double l) throws Exception {
        System.out.println("---------------testing---------------");
        System.out.println("---------------output---------------");
        for (int i = 0; i < ips.length; i++) {
            double o = output(ips[i], t, h, l);
            System.out.println(o);
        }
    }

    public void run(double[][] ips, double t, double h, double l) throws Exception {
        System.out.println("---------------running---------------");
        System.out.println("---------------output---------------");
        for (int i = 0; i < ips.length; i++) {
            double o = output(ips[i], t, h, l);
            System.out.println(o);
        }
    }

    public static void main(String[] args) throws Exception {
        double[][] ips = { { 1.0, 0.0, 0.0 }, { 1.0, 0.0, 1.0 }, { 1.0, 1.0, 0.0 }, { 1.0, 1.0, 1.0 } };
        double[] op = { 1.0, 1.0, 1.0, 0.0 };
        double t = 0.5;
        double r = 0.1;
        double h = 1.0;
        double l = 0.0;
        int x = 100;
        int n = 3;
        PercTron pt = new PercTron(n);
        pt.train(ips, op, t, r, h, l, x);
        pt.test(ips, t, h, l);
        pt.run(ips, t, h, l);
    }
}
