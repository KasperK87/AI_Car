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
        float[] w4 = {0.50f, 0.55f, 0.6f};

        perceptrons[0][0] = new Perceptron(w1, activations, 0.5f);
        perceptrons[0][1] = new Perceptron(w2, activations, 0.5f);
        perceptrons[1][0] = new Perceptron(w3, activations, 0.5f);
        perceptrons[1][1] = new Perceptron(w4, activations, 0.5f);
    }

    public DeepBrain(int data_points) {
        Activation activations = new Logistic();
        perceptrons = new Perceptron[2][2];

        //just for testing, p00 and p01 are never trained so changed them up
        //to give a better result for p10 and p11 which are trained
        perceptrons[0][0] = new Perceptron(new float[] {1, 0, -1, 0, 0}, new Linear(), 0.001f);
        perceptrons[0][1] = new Perceptron(new float[] {0, 1, 0, -1, 0}, new Linear(), 0.001f);
        perceptrons[1][0] = new Perceptron(3, new TanH(), 0.001f);
        perceptrons[1][1] = new Perceptron(3, new TanH(), 0.001f);

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
        //calculate total_error
        float error1 = 0.5f*(float)Math.pow(targets[0] - get_direction(inputs).x,2);
        float error2 = 0.5f*(float)Math.pow(targets[1] - get_direction(inputs).y,2);

        //for test should be 0.28937
        float total_error = error1+error2;

        //gradient of error
        float gradient_error1 = get_direction(inputs).x - targets[0];
        float gradient_error2 = get_direction(inputs).y - targets[1];

        //calculate gradient for activation function
        float[] hidden = new float[3];
        for (int i = 0; i < 2; i++) {
            hidden[i] = perceptrons[0][i].feedforward(inputs);
        }
        hidden[2] = 1; //bias

        //should be 0,18681
        float gradient_activation3 = perceptrons[1][0].gradient(perceptrons[1][0].getSums(hidden));
        
        float gradient_activation4 = perceptrons[1][1].gradient(perceptrons[1][1].getSums(hidden));


        //calculate gradient for each weight
        float gradient_weight5 = hidden[0];
        float gradient_weight6 = hidden[1];

        //calculate gradient and update hidden layer

        //should be 0,055
        float gradient_total_error_from_output_layer = gradient_activation3*gradient_error1*perceptrons[1][0].getWeights()[0];

        //should be -0,019
        float gradient_total_error_from_output_layer2 = gradient_activation4*gradient_error2*perceptrons[1][1].getWeights()[0];

        float gradient_hidden1 = perceptrons[0][0].gradient(perceptrons[0][0].getSums(inputs));

        //should be 0.0004
        float gw1 = (gradient_total_error_from_output_layer+gradient_total_error_from_output_layer2)*gradient_hidden1*inputs[0];
        
        //CODE HERE//

        //weights should be updated last
        //update output layer
        //chain rule gradient for w5 should be 0,0821
        float gw5 = gradient_error1*gradient_activation3*gradient_weight5;
        perceptrons[1][0].updateWeight(0, gw5);
        float gw6 = gradient_error1*gradient_activation3*gradient_weight6;
        perceptrons[1][0].updateWeight(1, gw6);

        //update bias 
        perceptrons[1][0].updateWeight(2, gradient_error1*gradient_activation3*1);

        //chain rule gradient for w7 and w8
        float gw7 = gradient_error2*gradient_activation4*hidden[0];
        perceptrons[1][1].updateWeight(0, gw7);
        float gw8 = gradient_error2*gradient_activation4*hidden[1];
        perceptrons[1][1].updateWeight(1, gw8);

        //update bias
        perceptrons[1][1].updateWeight(2, gradient_error2*gradient_activation4*1);

        //update input layer
        perceptrons[0][0].updateWeight(0, gw1);
    }

    public float[] getWeights(int layer, int perceptron) {
        return perceptrons[layer][perceptron].weights;
    }
    
}
