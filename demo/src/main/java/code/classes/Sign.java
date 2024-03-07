package code.classes;

public class Sign extends Activation{
    public float activate(float sum) {
        return sum > 0 ? 1 : -1;
    }
    public float gradient(float sum) {
        return 0;
    }
}
