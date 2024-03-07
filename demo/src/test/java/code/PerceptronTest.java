package code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import code.classes.Perceptron;
import code.classes.Sigmoid;
import code.classes.Sign;

public class PerceptronTest {
    @Test
    public void SignTestPositiv(){
        float[] weights = {2.0f, -1.0f};
        Perceptron p = new Perceptron(weights, new Sign());

        float[] inputs = {1.0f, -1.0f};
        float output = p.feedforward(inputs);

        assert(output == 1);
    }

    @Test
    public void SignTestNegativ(){
        float[] weights = {2.0f, -1.0f, 1.0f};
        Perceptron p = new Perceptron(weights, new Sign());

        float[] inputs = {1.0f, 3.0f, 1.0f};
        float output = p.feedforward(inputs);

        assert(output == -1);
    }

    @Test
    public void SigmoidTest(){
        //following test is based neptune.ai blog post
        //https://neptune.ai/blog/backpropagation-algorithm-in-neural-networks-guide

        float[] weights = {0.5f, 0.2f, 1.83f};
        Perceptron p = new Perceptron(weights, new Sigmoid());

        float[] inputs = {0.1f, 0.3f, 1};
        float output = p.feedforward(inputs);

        assertEquals(0.874, output, 0.001);

        double error = 0.5*Math.pow(0.03-(double)output, 2);

        assertEquals(0.356, error, 0.001);

        //train

        p.train(inputs, 0.03f);

        float weights0 = p.getWeights()[0];
        float weights1 = p.getWeights()[1];
        float weights2 = p.getWeights()[2];

        assertEquals(0.499, weights0, 0.01f);
        assertEquals(0.197, weights1, 0.01f);
        assertEquals(1.82, weights2, 0.01f);
    }

    @Test
    public void backpropagation(){
        float[] weights = {0.5f, 0.2f, 1.83f};
        Perceptron p = new Perceptron(weights, new Sigmoid());

        //confused about the error value
        float error = 0.3f - p.feedforward(new float[]{0.1f, 0.3f, 1});
        float learning_rate = 0.01f;

        float[] inputs = {0.1f, 0.3f, 1};

        float gradient = p.gradient(p.getSums(inputs));

        float[] updatedWeights = {  0.5f+0.5f*error*gradient*learning_rate, 
                                    0.2f + 0.2f*error*gradient*learning_rate, 
                                    1.83f+1.83f*error*gradient*learning_rate};

        float weights0 = updatedWeights[0];
        float weights1 = updatedWeights[1];
        float weights2 = updatedWeights[2];

        assertEquals(0.499, weights0, 0.01f);
        assertEquals(0.197, weights1, 0.01f);
        assertEquals(1.82, weights2, 0.01f);
    }
    
}
