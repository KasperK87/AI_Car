package code.classes;

import processing.core.PVector;

public class DeepBrain extends NeuralNet {

    private Perceptron[][] perceptrons;
    
    //used for testing
    public DeepBrain() {
        Activation activations = new Sigmoid();
        perceptrons = new Perceptron[2][2];

        float[] w1 = {0.15f, 0.2f, 0.35f};
        float[] w2 = {0.2f, 0.3f, 0.35f};
        float[] w3 = {0.4f, 0.45f, 0.6f};
        float[] w4 = {0.45f, 0.55f, 0.6f};

        perceptrons[0][0] = new Perceptron(w1, activations);
        perceptrons[0][1] = new Perceptron(w2, activations);
        perceptrons[1][0] = new Perceptron(w3, activations);
        perceptrons[1][1] = new Perceptron(w4, activations);
    }

    public void train(int data_points) {
        
    }

    public PVector get_direction(float[] inputs) {
        return new PVector();
    }
    
}
