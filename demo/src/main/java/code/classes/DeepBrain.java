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

        perceptrons[0][0] = new Perceptron(w1, activations, 0.5f);
        perceptrons[0][1] = new Perceptron(w2, activations, 0.5f);
        perceptrons[1][0] = new Perceptron(w3, activations, 0.5f);
        perceptrons[1][1] = new Perceptron(w4, activations, 0.5f);
    }

    public DeepBrain(int data_points) {
        Activation activations = new Logistic();
        perceptrons = new Perceptron[2][2];

        perceptrons[0][0] = new Perceptron(5, activations, 0.01f);
        perceptrons[0][1] = new Perceptron(5, activations, 0.01f);
        perceptrons[1][0] = new Perceptron(3,activations, 0.01f);
        perceptrons[1][1] = new Perceptron(3,activations, 0.01f);

        train(data_points);
    }

    public void train(int data_points) {
        TrainingData training_data = new TrainingData(data_points);

        for (int i = 0; i < data_points; i++) {
            float[] inputs = {  training_data.training_data[i][0], 
                                training_data.training_data[i][1],
                                training_data.training_data[i][2],
                                training_data.training_data[i][3]
                                ,1f}; //bias

            float[] targets = {training_data.training_data[i][4], training_data.training_data[i][5]};

            backpropagate(inputs, targets);
        }
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

    public void backpropagate(float[] inputs, float[] targets) {
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

        //calculate output layer errors
        float[] output_errors = new float[2];
        for (int i = 0; i < 2; i++) {
            output_errors[i] = targets[i] - outputs[i];
        }

        //calculate hidden layer errors
        float[] hidden_errors = new float[3];
        for (int i = 0; i < 3; i++) {
            float sum = 0;
            for (int j = 0; j < 2; j++) {
                sum += perceptrons[1][j].weights[i] * output_errors[j];
            }
            hidden_errors[i] = sum;
        }

        //update output layer weights
        for (int i = 0; i < 2; i++) {
            float delta = output_errors[i] * perceptrons[1][i].gradient(perceptrons[1][i].getSums(hidden));
            for (int j = 0; j < 3; j++) {
                perceptrons[1][i].weights[j] += perceptrons[1][i].weights[j]*perceptrons[1][i].learning_rate * delta;
            }
        }

        //update hidden layer weights
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                float delta = hidden_errors[j] * perceptrons[0][i].gradient(perceptrons[0][i].getSums(inputs)) * inputs[j];
                perceptrons[0][i].weights[j] += perceptrons[0][i].learning_rate * delta;
            }
        }
    }

    public float[] getWeights(int layer, int perceptron) {
        return perceptrons[layer][perceptron].weights;
    }
    
}
