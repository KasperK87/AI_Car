package code.classes;

import processing.core.PVector;

public abstract class NeuralNet {
    public abstract void train(int data_points);
    public abstract PVector get_direction(float[] inputs);
}
