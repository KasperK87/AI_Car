package code.classes;

import processing.core.PVector;

public class DeepBrain extends NeuralNet {

    private Perceptron[][] perceptrons;
    
    //used for testing
    public DeepBrain() {
        Activation activations = new Logistic();
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
        float[] hidden = new float[3];
        float[] outputs = new float[perceptrons.length];

        for (int i = 0; i < 2; i++) {
            hidden[i] = perceptrons[0][i].feedforward(inputs);
        }
        hidden[2] = 1; //bias

        //output layer
        for (int i = 0; i < 2; i++) {
            outputs[i] = perceptrons[1][i].feedforward(hidden);
        }
        return new PVector(outputs[0], outputs[1]);
    }
    
}
