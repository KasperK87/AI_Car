package code.classes;

public class Perceptron {
        
    float[] weights;
    float learning_rate = 0.001f;
    
    public Perceptron(int n) {
        weights = new float[n];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) Math.random() * 2 - 1;
        }
    }
    
    public int feedforward(float[] inputs) {
        float sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return activate(sum);
    }
    
    public int activate(float sum) {
        if (sum > 0) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public void train(float[] inputs, int target) {
        int guess = feedforward(inputs);
        float error = target - guess;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learning_rate;
        }
    }
}
