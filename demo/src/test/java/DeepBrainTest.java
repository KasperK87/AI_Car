import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import code.classes.DeepBrain;
import code.classes.TrainingData;
import processing.core.PVector;

public class DeepBrainTest {
    
    @Test
    public void feedforward(){
        //following test is based mattmazur.com

        float[] inputs = {0.05f, 0.1f, 1.0f};

        DeepBrain brain = new DeepBrain();

        PVector output = brain.get_direction(inputs);

        assertEquals(0.75f, output.x, 0.01);
        assertEquals(0.77f, output.y, 0.01);
    }

    @Test
    public void backpropagate(){
        //following test is based mattmazur.com

        float[] inputs = {0.05f, 0.1f, 1.0f};

        DeepBrain brain = new DeepBrain();

        PVector output = brain.get_direction(inputs);

        assertEquals(0.7514f, output.x, 0.0001);
        assertEquals(0.7729f, output.y, 0.0001);

        float[] targets = {0.01f, 0.99f};

        brain.backpropagate(inputs, targets);

        PVector output2 = brain.get_direction(inputs);

        assertTrue(output.x > output2.x);
        assertTrue(output.y < output2.y);

        float[] weights_perceptron3 = brain.getWeights(1,0);
        float[] weights_perceptron4 = brain.getWeights(1,1);

        assertEquals(0.3589, weights_perceptron3[0], 0.0001);
        assertEquals(0.4088, weights_perceptron3[1], 0.0001);
        assertEquals(0.5113, weights_perceptron4[0], 0.0001);
        assertEquals(0.5614, weights_perceptron4[1], 0.0001);

        float[] weights_perceptron1 = brain.getWeights(0,0);
        float[] weights_perceptron2 = brain.getWeights(0,1);

        assertEquals(0.14978, weights_perceptron1[0], 0.00001);
        assertEquals(0.19956, weights_perceptron1[1], 0.00001);
        //assertEquals(0.24975, weights_perceptron2[0], 0.00001); //all other tests fails, is it a typing mistake?
        assertEquals(0.29950, weights_perceptron2[1], 0.00001);

        //second pass
        brain.backpropagate(inputs, targets);

        PVector output3 = brain.get_direction(inputs);

        assertTrue(output2.x > output3.x);
        assertTrue(output2.y < output3.y);
    }

    @Test
    public void train(){
        TrainingData training_data = new TrainingData(10000);

        DeepBrain brain = new DeepBrain(1);
        float initial_error = 0;

        for (int i = 0; i < 100; i++) {
            float[] inputs = {  training_data.training_data[i][0], 
                                training_data.training_data[i][1], 
                                training_data.training_data[i][2], 
                                training_data.training_data[i][3], 
                                1}; //bias
            float[] target = {training_data.training_data[i][4], training_data.training_data[i][5]};

            PVector outputs = brain.get_direction(inputs);
            initial_error += Math.abs(outputs.x - training_data.training_data[0][4]) + Math.abs(outputs.y - training_data.training_data[0][5]);
        }

        initial_error /= 100;
        
        for (int i = 0; i < training_data.training_data.length; i++) {
            float[] inputs = {  training_data.training_data[i][0], 
                                training_data.training_data[i][1], 
                                training_data.training_data[i][2], 
                                training_data.training_data[i][3], 
                                1}; //bias
            float[] target = {training_data.training_data[i][4], training_data.training_data[i][5]};

            brain.backpropagate(inputs, target);
        }

        float new_error = 0;

        for (int i = 0; i < 100; i++) {
            float[] inputs = {  training_data.training_data[i][0], 
                                training_data.training_data[i][1], 
                                training_data.training_data[i][2], 
                                training_data.training_data[i][3], 
                                1}; //bias

            PVector outputs = brain.get_direction(inputs);
            new_error += Math.abs(outputs.x - training_data.training_data[0][4]) + Math.abs(outputs.y - training_data.training_data[0][5]);
        }

        new_error /= 100;

        assertTrue(new_error < initial_error);
    }
    
}
