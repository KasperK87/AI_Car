package code.classes;

public abstract class Activation {
    public abstract float activate(float sum);
    public abstract float gradient(float sum);
}
