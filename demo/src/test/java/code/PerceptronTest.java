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
        float[] weights = {0.5f, 0.2f, 1.83f};
        Perceptron p = new Perceptron(weights, new Sigmoid());

        float[] inputs = {0.1f, 0.3f, 1};
        float output = p.feedforward(inputs);

        assertEquals(0.874, output, 0.001);
    }
    
}
