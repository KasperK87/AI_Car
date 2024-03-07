package code.classes;

public class Logistic extends Activation{
    public float activate(float sum) {
        return (float) (1 / (1 + Math.exp(-sum)));
    }
    public float gradient(float sum) {
        return activate(sum) * (1 - activate(sum));
    }
}
