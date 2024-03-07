package code.classes;

public class TanH extends Activation{
    public float activate(float sum) {
        return (float) Math.tanh(sum);
    }
    public float gradient(float sum) {
        return 1 - (float) Math.pow(Math.tanh(sum), 2);
    }
}
