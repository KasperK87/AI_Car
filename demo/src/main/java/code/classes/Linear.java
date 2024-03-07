package code.classes;

public class Linear extends Activation{
    public float activate(float sum) {
        return sum;
    }
    public float gradient(float sum) {
        return 1;
    }
}
