package code.classes;

import processing.core.PVector;

public class Brain {

    Perceptron[] perceptrons;

    public Brain() {
        perceptrons = new Perceptron[2];
        for (int i = 0; i < perceptrons.length; i++) {
            perceptrons[i] = new Perceptron(4);
        }
    }

    public PVector get_direction(float[] inputs) {
        float[] outputs = new float[perceptrons.length];
        for (int i = 0; i < perceptrons.length; i++) {
            outputs[i] = perceptrons[i].feedforward(inputs);
        }
        return new PVector(outputs[0], outputs[1]);
    }
    
}
