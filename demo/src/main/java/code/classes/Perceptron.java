package code.classes;

public class Perceptron {
    Activation activation;
        
    float[] weights;
    float learning_rate = 0.01f;
    
    public Perceptron(int n) {
        this.activation = new TanH();

        weights = new float[n];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) Math.random() * 2 - 1;
        }
    }

    public Perceptron(int n, Activation activation) {
        this.activation = activation;

        weights = new float[n];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) Math.random() * 2 - 1;
        }
    }

    public Perceptron(float[] weights, Activation activation) {
        this.activation = activation;

        this.weights = weights;
    }
    
    public float feedforward(float[] inputs) {
        float sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return activate(sum);
    }
    
    public float activate(float sum) {
        return activation.activate(sum);
    }
    
    public float activateSign(float sum){
        if (sum > 0){
            return 1;
        } else {
            return -1;
        }
    }

    public void train(float[] inputs, float target) {
        float guess = feedforward(inputs);
        float error = target - guess;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learning_rate;
        }
    }

    public float[] getWeights(){
        return weights;
    }
}
